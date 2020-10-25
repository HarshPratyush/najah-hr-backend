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
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    public JavaMailSender emailSender;


    @Override
    @Transactional
    public Single<ResponseModel<Jobs>> saveJob(JobsModelExtended job) {
        return Single.create(singleSubscriber -> {
            if(!divisionRepository.findById(job.getDivisionId()).isPresent()){
                singleSubscriber.onError(new AlreadyExists("Division doesn't exist"));
            }else{
                Jobs jobEntity = Mapper.map(job);


                jobEntity= jobsRepository.save(jobEntity);
                singleSubscriber.onSuccess(new ResponseModel<Jobs>(jobEntity));
            }
        });
    }

    @Override
    @Transactional
    public Single<ResponseModel<JobSeeker>> saveJobSeeker(JobSeekerModel jobSeekerModel) {
        return Single.create(singleSubscriber -> {
            JobSeeker jobSeeker = Mapper.map(jobSeekerModel);

            Attachment attachment = new Attachment();
            attachment.setAttachmentType(jobSeekerModel.getFileType());
            attachment.setAttachmentFor(AttachmentFor.JOBSEEKER);
            attachment.setAttachmentName(jobSeekerModel.getName().concat(".").concat(jobSeekerModel.getFileExt()));

            try {
                attachment.setAttachmentPath(Utility.writeBase64ToFile(jobSeekerModel.getResume(),jobSeekerModel.getName(),jobSeekerModel.getFileExt()));
            } catch (IOException e) {
                singleSubscriber.onError(new IOException("Exception"));
            }
            jobSeeker.setResume(attachment);
            jobSeeker = jobSeekerRepository.save(jobSeeker);
            try {
                sendMessageWithAttachment("info@najahhr.com","Resume By "+jobSeeker.getName(),"New Resume Received",attachment.getAttachmentName(),attachment.getAttachmentPath());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            singleSubscriber.onSuccess(new ResponseModel<JobSeeker>(jobSeeker));
        });
    }

    private void sendMessageWithAttachment(
            String to, String subject, String text, String fileName,byte[] pathToAttachment) throws MessagingException {
        // ...

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment(fileName,new ByteArrayResource(pathToAttachment));

        emailSender.send(message);
        // ...
    }

    @Override
    public Single<ResponseModel<Map<String,List<JobsModel>>>> findAllJobsByIndustries() {
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

    @Override
    public Single<ResponseModel<List<JobSeekerModel>>> findAllJobSeekers() {
        return Single.create(singleSubscriber -> {
            List<JobSeeker> jobSeekerList= jobSeekerRepository.findAll();

            List<JobSeekerModel> jobSeekerModelList = new ArrayList<>();

            for(JobSeeker jobSeeker:jobSeekerList){
                JobSeekerModel jobSeekerModel = new JobSeekerModel();
                jobSeekerModel = Mapper.map(jobSeeker);
                jobSeekerModelList.add(jobSeekerModel);
            }
            singleSubscriber.onSuccess(new ResponseModel<List<JobSeekerModel>>(jobSeekerModelList));
        });
    }

    @Override
    public Single<ResponseModel<JobsFullModel>> archiveJob(Long jobId) {
        return Single.create(singleSubscriber -> {
            Optional<Jobs> jobsOptional = jobsRepository.findById(jobId);
            if(!jobsOptional.isPresent()){
                singleSubscriber.onError(new NotFound());
            }else{
                jobsOptional.get().setArchived(true);
                jobsRepository.save(jobsOptional.get());
                singleSubscriber.onSuccess(new ResponseModel<>(Mapper.map(jobsOptional.get())));
            }
        });
    }

    @Override
    public Single<ResponseModel<List<JobsFullModel>>> findAllJobs() {
        return Single.create(singleSubscriber -> {
            List<Jobs> jobsList = jobsRepository.findAll();
            List<JobsFullModel> jobsFullModelList = new ArrayList<>();
            jobsList.forEach(jobs  ->{
                JobsFullModel jobsFullModel = new JobsFullModel();
                jobsFullModel = Mapper.map(jobs);
                jobsFullModelList.add(jobsFullModel);
            });
            singleSubscriber.onSuccess(new ResponseModel<List<JobsFullModel>>(jobsFullModelList));
        });
    }


}
