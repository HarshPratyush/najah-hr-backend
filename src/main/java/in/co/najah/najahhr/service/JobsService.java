package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.models.*;
import org.springframework.data.repository.query.Param;
import rx.Single;

import java.util.List;

public interface JobsService {
    public Single<ResponseModel<Jobs>> saveJob(JobsModelExtended job);

    Single<ResponseModel<JobSeeker>> saveJobSeeker(JobSeekerModel jobSeekerModel);
}
