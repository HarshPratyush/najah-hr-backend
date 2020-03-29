package in.co.najah.najahhr.rest;


import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.service.JobsService;
import in.co.najah.najahhr.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RequestMapping("jobs")
@RestController
public class JobsController {

    @Autowired
   private JobsService jobsService;

    private final static Path rootPath = Paths.get(Constants.ROOT_DIRC);


    @PostMapping
    public Single<ResponseModel<Jobs>> saveJob(@Valid @RequestBody JobsModelExtended jobsModelExtended){
        return jobsService.saveJob(jobsModelExtended);
    }

    @PostMapping(path = "/jobSeeker")
    public Single<ResponseModel<JobSeeker>> saveJobSeeker(@Valid @RequestBody JobSeekerModel jobSeekerModel){
        return jobsService.saveJobSeeker(jobSeekerModel);
    }

    @GetMapping
    public Single<ResponseModel<Map<String,List<JobsModel>>>> findAllJobs(){
        return jobsService.findAllJobs();
    }

    @GetMapping(path = "downloadAttachment/{attachmentId}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("attachmentId") Long attachmentId, HttpServletRequest    request,
                                                       HttpServletResponse response) throws IOException {
        Attachment attachment = jobsService.getAttachment(attachmentId);
        Path file = rootPath.resolve(attachment.getAttachmentPath()).toAbsolutePath();
        if (Files.exists(file))
        {
            response.setContentType(attachment.getAttachmentType());
            response.addHeader("Content-Disposition", "inline; filename="+file.getFileName());
            try
            {

                HttpHeaders respHeaders = new HttpHeaders();
                respHeaders.setContentType(MediaType.asMediaType(MimeType.valueOf(attachment.getAttachmentType())));
                respHeaders.add("Content-Disposition", "inline; filename=" + file.getFileName());
                InputStreamResource isr = new InputStreamResource(new FileInputStream(file.toFile()));
                return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return  ResponseEntity.badRequest().body(null);
    }
}
