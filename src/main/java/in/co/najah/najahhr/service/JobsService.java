package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.models.*;
import org.springframework.data.repository.query.Param;
import rx.Single;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public interface JobsService {
    public Single<ResponseModel<Jobs>> saveJob(JobsModelExtended job);

    Single<ResponseModel<JobSeeker>> saveJobSeeker(JobSeekerModel jobSeekerModel);

    Single<ResponseModel<Map<String,List<JobsModel>>>> findAllJobs();

    Attachment getAttachment(Long attachmentId) throws IOException;
}
