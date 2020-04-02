package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.models.*;
import rx.Single;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface JobsService {
    public Single<ResponseModel<Jobs>> saveJob(JobsModelExtended job);

    Single<ResponseModel<JobSeeker>> saveJobSeeker(JobSeekerModel jobSeekerModel);

    Single<ResponseModel<Map<String,List<JobsModel>>>> findAllJobsByIndustries();

    Attachment getAttachment(Long attachmentId) throws IOException;

    Single<ResponseModel<List<JobSeekerModel>>> findAllJobSeekers();

    Single<ResponseModel<JobsFullModel>> archiveJob(Long jobId);

    Single<ResponseModel<List<JobsFullModel>>> findAllJobs();
}
