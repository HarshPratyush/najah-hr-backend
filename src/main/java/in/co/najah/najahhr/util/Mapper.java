package in.co.najah.najahhr.util;

import in.co.najah.najahhr.entity.*;
import in.co.najah.najahhr.models.*;

import java.time.LocalDate;

public class Mapper {

    public static IndustriesModel map(Industries  industries){
        IndustriesModel  industriesModel = new IndustriesModel();
        industriesModel.setUrl(industries.getUrl());
        industriesModel.setAttachmentId(industries.getAttachmentId());
        industriesModel.setName(industries.getName());
        industriesModel.setDescription(industries.getDescription());
        industriesModel.setIndustryId(industries.getIndustryId());
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
        job.setDivision(new Division(jobModel.getDivisionId()));
        job.setEmailId(jobModel.getEmailId());
        job.setLocation(jobModel.getLocation());
        job.setOpenings(jobModel.getOpenings());
        job.setRequirement(jobModel.getRequirement());
        job.setName(jobModel.getName());
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

    public static DivisionModel mapDivisionToModel(Division division) {
        DivisionModel divisionModel = new DivisionModel();
        divisionModel.setDivisionName(division.getDivisionName());
        divisionModel.setId(division.getId());
        return divisionModel;
    }

    public static JobsFullModel mapToJobsFullModel(Jobs jobs) {
        JobsFullModel jobsFullModel = new JobsFullModel();
        jobsFullModel.setId(jobs.getId());
        jobsFullModel.setSubject(jobs.getSubject());
        jobsFullModel.setCompanyName(jobs.getCompanyName());
        jobsFullModel.setArchived(jobs.isArchived());
        jobsFullModel.setDivisionId(jobs.getDivision().getId());
        jobsFullModel.setEmailId(jobs.getEmailId());
        jobsFullModel.setLocation(jobs.getLocation());
        jobsFullModel.setOpenings(jobs.getOpenings());
        jobsFullModel.setRequirement(jobs.getRequirement());
        jobsFullModel.setName(jobs.getName());
        jobsFullModel.setCompanyName(jobs.getCompanyName());
        jobsFullModel.setCreatedBy(jobs.getCreatedBy());
        jobsFullModel.setCreatedOn(jobs.getCreatedOn().toString());
        jobsFullModel.setUpdatedBy(jobs.getUpdatedBy());
        jobsFullModel.setUpdatedOn(jobs.getUpdatedOn().toString());
        return jobsFullModel;
    }

    public static JobSeekerModel map(JobSeeker jobSeeker) {
        JobSeekerModel jobSeekerModel = new JobSeekerModel();
        jobSeekerModel.setName(jobSeeker.getName());
        jobSeekerModel.setEmailId(jobSeeker.getEmailId());
        jobSeekerModel.setSubject(jobSeeker.getSubject());
        jobSeekerModel.setMessage(jobSeeker.getMessage());
        jobSeekerModel.setDivisionId(jobSeeker.getDivision().getId());
        jobSeekerModel.setDob(jobSeeker.getDob().toString());
        jobSeekerModel.setResume(jobSeeker.getResume().getAttachmentPath());
        jobSeekerModel.setFileExt(jobSeeker.getResume().getAttachmentType());
        jobSeekerModel.setResumeId(jobSeeker.getResume().getAttachmentId());
        return jobSeekerModel;
    }
}
