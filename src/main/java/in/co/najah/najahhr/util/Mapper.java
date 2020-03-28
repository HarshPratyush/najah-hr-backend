package in.co.najah.najahhr.util;

import in.co.najah.najahhr.entity.*;
import in.co.najah.najahhr.models.IndustriesModel;
import in.co.najah.najahhr.models.JobSeekerModel;
import in.co.najah.najahhr.models.JobsModelExtended;
import in.co.najah.najahhr.models.SingleIndustry;

import java.time.LocalDate;

public class Mapper {

    public static IndustriesModel mapJob(Industries  industries){
        IndustriesModel  industriesModel = new IndustriesModel();
        industriesModel.setUrl(industries.getUrl());
        industriesModel.setAttachmentId(industries.getAttachmentId());
        industriesModel.setName(industries.getName());
        industriesModel.setDescription(industries.getDescription());
        return industriesModel;
    }

    public static SingleIndustry mapForSingleIndustry(Industries industries){
        SingleIndustry  industriesModel = new SingleIndustry();
        industriesModel.setUrl(industries.getUrl());
        industriesModel.setAttachmentId(industries.getAttachmentId());
        industriesModel.setName(industries.getName());
        industriesModel.setDescription(industries.getDescription());
        return industriesModel;
    }

    public static Jobs mapJob(JobsModelExtended jobModel){
        Jobs job = new Jobs();
        job.setSubject(jobModel.getSubject());
        job.setCompanyName(jobModel.getCompanyName());
        job.setArchived(jobModel.isArchived());
        job.setDivision(new Division(jobModel.getDivision()));
        job.setEmailId(jobModel.getEmailId());
        job.setLocation(jobModel.getLocation());
        job.setOpenings(jobModel.getOpenings());
        job.setRequirement(jobModel.getRequirement());
        job.setName(jobModel.getName());
        job.setRequirement(jobModel.getRequirement());
        return job;
    }

    public static JobSeeker mapJobSeeker(JobSeekerModel jobSeekerModel) {
        JobSeeker jobSeeker = new JobSeeker();
//        jobSeeker.setAttachment(new Attachment(jobSeekerModel.getAttachment()));
        jobSeeker.setDob(LocalDate.parse(jobSeekerModel.getDob()));
        jobSeeker.setDivision(new Division(jobSeekerModel.getDivisionId()));
        jobSeeker.setName(jobSeekerModel.getName());
        jobSeeker.setMessage(jobSeekerModel.getMessage());
        jobSeeker.setSubject(jobSeekerModel.getSubject());
        jobSeeker.setEmailId(jobSeekerModel.getEmailId());
        return jobSeeker;
    }
}
