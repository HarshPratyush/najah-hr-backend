package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.enums.AttachmentFor;
import in.co.najah.najahhr.exceptions.AlreadyExists;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.repository.AttachmentRepository;
import in.co.najah.najahhr.repository.DivisionRepository;
import in.co.najah.najahhr.repository.JobSeekerRepository;
import in.co.najah.najahhr.repository.JobsRepository;
import in.co.najah.najahhr.util.Mapper;
import in.co.najah.najahhr.util.Utility;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import java.io.IOException;

@Log
@Service
public class JobsServiceImpl implements JobsService{

    @Autowired
    private JobsRepository jobsRepository;
    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    @Transactional
    public Single<ResponseModel<Jobs>> saveJob(JobsModelExtended job) {
        return Single.create(singleSubscriber -> {
            if(jobsRepository.findByEmailId(job.getEmailId()).isPresent()){
                singleSubscriber.onError(new AlreadyExists("Email already exists"));
            }else if(!divisionRepository.findById(job.getDivision()).isPresent()){
                singleSubscriber.onError(new AlreadyExists("Division doesn't exist"));
            }else{
                Jobs jobEntity = Mapper.mapJob(job);


                jobEntity= jobsRepository.save(jobEntity);
                singleSubscriber.onSuccess(new ResponseModel<Jobs>(jobEntity));
            }
        });
    }

    @Override
    @Transactional
    public Single<ResponseModel<JobSeeker>> saveJobSeeker(JobSeekerModel jobSeekerModel) {
        return Single.create(singleSubscriber -> {
            JobSeeker jobSeeker = Mapper.mapJobSeeker(jobSeekerModel);

            Attachment attachment = new Attachment();
            attachment.setAttachmentType(Utility.getMimeType(jobSeekerModel.getResume()));
            attachment.setAttachmentFor(AttachmentFor.JOBSEEKER);
            try {
                attachment.setAttachmentPath(Utility.writeBase64ToFile(jobSeekerModel.getResume(),jobSeekerModel.getName(),jobSeekerModel.getFileExt()));
            } catch (IOException e) {
                singleSubscriber.onError(new IOException("Exception"));
            }
            jobSeeker.setResume(attachment);
            jobSeeker = jobSeekerRepository.save(jobSeeker);
            singleSubscriber.onSuccess(new ResponseModel<JobSeeker>(jobSeeker));
        });
    }
}
