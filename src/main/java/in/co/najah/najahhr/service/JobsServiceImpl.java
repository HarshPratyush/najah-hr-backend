package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.JobSeeker;
import in.co.najah.najahhr.entity.Jobs;
import in.co.najah.najahhr.enums.AttachmentFor;
import in.co.najah.najahhr.exceptions.AlreadyExists;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.repository.AttachmentRepository;
import in.co.najah.najahhr.repository.DivisionRepository;
import in.co.najah.najahhr.repository.JobSeekerRepository;
import in.co.najah.najahhr.repository.JobsRepository;
import in.co.najah.najahhr.util.Constants;
import in.co.najah.najahhr.util.Mapper;
import in.co.najah.najahhr.util.Utility;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
            }else if(!divisionRepository.findById(job.getDivisionId()).isPresent()){
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

    @Override
    public Single<ResponseModel<Map<String,List<JobsModel>>>> findAllJobs() {
        return Single.create(singleSubscriber -> {
            List<Object[]> data= jobsRepository.findAllJobs();

            Map<String,List<JobsModel>> jobsModelMap = new HashMap<>();

            for(Object[] job:data){
                List<JobsModel> jobsModels;
                if(jobsModelMap.containsKey(job[3].toString())){
                    jobsModels = jobsModelMap.get(job[3].toString());
                }else
                {
                    jobsModels=new ArrayList<>();
                    jobsModelMap.put(job[3].toString(),jobsModels);
                }
                jobsModels.add(new JobsModel(Long.parseLong(job[0].toString()),job[1].toString(),job[2].toString()));
            }

            singleSubscriber.onSuccess(new ResponseModel<Map<String,List<JobsModel>>>(jobsModelMap));
        });
    }

    @Override
    public Attachment getAttachment(Long attachmentId) throws IOException {
    return attachmentRepository.findById(attachmentId).orElse(null);

    }
}
