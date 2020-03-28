package in.co.najah.najahhr.rest;


import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.service.IndustryService;
import in.co.najah.najahhr.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("jobs")
@RestController
public class JobsController {

    @Autowired
   private JobsService jobsService;

    @PostMapping
    public Single<ResponseModel<Jobs>> saveJob(@Valid @RequestBody JobsModelExtended jobsModelExtended){
        return jobsService.saveJob(jobsModelExtended);
    }

    @PostMapping(path = "/jobSeeker")
    public Single<ResponseModel<JobSeeker>> saveJobSeeker(@Valid @RequestBody JobSeekerModel jobSeekerModel){
        return jobsService.saveJobSeeker(jobSeekerModel);
    }
}
