package in.co.najah.najahhr.rest;


import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class JobsController {

    @Autowired
   private JobsService jobsService;



    @PostMapping("anonymous/jobs")
    public Single<ResponseModel<Jobs>> saveJob(@Valid @RequestBody JobsModelExtended jobsModelExtended){
        return jobsService.saveJob(jobsModelExtended);
    }

    @PostMapping(path = "anonymous/jobs/jobSeeker")
    public Single<ResponseModel<JobSeeker>> saveJobSeeker(@Valid @RequestBody JobSeekerModel jobSeekerModel){
        return jobsService.saveJobSeeker(jobSeekerModel);
    }

    @GetMapping("anonymous/jobs")
    public Single<ResponseModel<Map<String,List<JobsModel>>>> findAllJobsByIndustries(){
        return jobsService.findAllJobsByIndustries();
    }

    @GetMapping(path = "anonymous/downloadAttachment/{attachmentId}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable("attachmentId") Long attachmentId, HttpServletRequest request,
                                                       HttpServletResponse response) throws IOException {
        Attachment attachment = jobsService.getAttachment(attachmentId);
        if (attachment!=null)
        {
            response.setContentType(attachment.getAttachmentType());
            response.addHeader("Content-Disposition", "inline; filename="+attachment.getAttachmentName());

            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.setContentType(MediaType.asMediaType(MimeType.valueOf(attachment.getAttachmentType())));
            respHeaders.add("Content-Disposition", "inline; filename=" + attachment.getAttachmentName());
//                InputStreamResource isr = new InputStreamResource(new FileInputStream(new ByteArrayResource(attachment.getAttachmentPath()).getFile()));
            return new ResponseEntity<ByteArrayResource>(new ByteArrayResource(attachment.getAttachmentPath()), respHeaders, HttpStatus.OK);
        }
        return  ResponseEntity.badRequest().body(null);
    }


    @PreAuthorize("hasAuthority('resume')")
    @GetMapping(path = "jobs/jobSeeker")
    public Single<ResponseModel<List<JobSeekerModel>>> findAllJobSeeker(){
        return jobsService.findAllJobSeekers();
    }

    @PreAuthorize("hasAuthority('jobs')")
    @PutMapping(path = "jobs/archiveJob/{jobId}")
    public Single<ResponseModel<JobsFullModel>> archiveJob(@PathVariable(name = "jobId") String jobId ){
        return jobsService.archiveJob(Long.valueOf(jobId));
    }

    @PreAuthorize("hasAuthority('jobs')")
    @GetMapping(path="jobs/all")
    public Single<ResponseModel<List<JobsFullModel>>> findAllJobs(){
        return jobsService.findAllJobs();
    }

}
