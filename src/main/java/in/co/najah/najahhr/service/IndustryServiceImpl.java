package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.enums.AttachmentFor;
import in.co.najah.najahhr.exceptions.AlreadyExists;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.repository.AttachmentRepository;
import in.co.najah.najahhr.repository.IndustriesRepository;
import in.co.najah.najahhr.repository.JobsRepository;
import in.co.najah.najahhr.util.Mapper;
import in.co.najah.najahhr.util.Utility;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Log
@Service
public class IndustryServiceImpl implements IndustryService{

    @Autowired
    private IndustriesRepository industriesRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Override
    public Single<ResponseModel<List<IndustriesModel>>> getAllIndustries() {
        return Single.create(singleSubscriber -> {
            ResponseModel<List<IndustriesModel>> responseModel = new ResponseModel<>();
            List<IndustriesModel> industries= industriesRepository.findAll().stream().map(Mapper::mapJob).collect(Collectors.toList());
            responseModel.setData(industries);
            singleSubscriber.onSuccess(responseModel);
        });
    }

    @Override
    @Transactional
    public Single<ResponseModel<Industries>> saveIndustry(IndustryModel industryModel) {

        return Single.create(singleSubscriber -> {

            if(industriesRepository.findByNameOrUrl(industryModel.getName(),industryModel.getUrl()).isPresent()){
                singleSubscriber.onError(new AlreadyExists("Name already exist"));
            }else
            {
                Industries industries = new Industries();
                industries.setDescription(industryModel.getDescription());
                industries.setName(industryModel.getName());
                industries.setDescription(industryModel.getDescription());

                Attachment attachment = new Attachment();
                attachment.setAttachmentType(Utility.getMimeType(industryModel.getImage()));
                attachment.setAttachmentFor(AttachmentFor.INDUSTRY);
                try {
                    attachment.setAttachmentPath(Utility.writeBase64ToFile(industryModel.getImage(),industryModel.getUrl(),industryModel.getFileExt()));
                } catch (IOException e) {
                   singleSubscriber.onError(new IOException("Exception"));
                }

                attachmentRepository.save(attachment);

                industries.setAttachmentId(attachment.getAttachmentId());
                industries.setUrl(industryModel.getUrl());
                industriesRepository.save(industries);

                ResponseModel<Industries> responseModel = new ResponseModel<>();
                responseModel.setData(industries);
                singleSubscriber.onSuccess(responseModel);

            }
        });
    }

    @Override
    public Single<ResponseModel<SingleIndustry>> getByIdentifier(String identifier) {
        return Single.create(singleSubscriber -> {
             Optional<Industries> industries = industriesRepository.findByUrl(identifier);
             ResponseModel<SingleIndustry> singleIndustryResponseModel = new ResponseModel<>();
             if(industries.isPresent()){
                 SingleIndustry singleIndustry=industries.map(Mapper::mapForSingleIndustry).get();
                 singleIndustry.setDivisions(industries.get().getDivisionSet().stream().map(Division::getDivisionName).collect(Collectors.toList()));

                 List<Object[]> jobs=jobsRepository.findJobsByIndustry(industries.get());
                 List<JobsModel> jobsModels = new ArrayList<>();
                 jobs.forEach(job ->{
                     jobsModels.add(new JobsModel(Long.parseLong(job[0].toString()),job[1].toString(),job[2].toString()));
                 });
                 singleIndustry.setAvailablePost(jobsModels);

                 singleIndustryResponseModel.setData(singleIndustry);
             }
             singleSubscriber.onSuccess(singleIndustryResponseModel);

        });
    }
}
