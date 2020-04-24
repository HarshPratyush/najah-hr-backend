package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Attachment;
import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.enums.AttachmentFor;
import in.co.najah.najahhr.exceptions.AlreadyExists;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.repository.AttachmentRepository;
import in.co.najah.najahhr.repository.DivisionRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustriesRepository industriesRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private DivisionRepository divisionRepository;


    @Override
    public Single<ResponseModel<List<IndustriesModel>>> getAllIndustries() {
        log.info("********** Inside get all industries *************");
        return Single.create(singleSubscriber -> {
            ResponseModel<List<IndustriesModel>> responseModel = new ResponseModel<>();
            List<IndustriesModel> industries = industriesRepository.findAll().stream().map(Mapper::map).collect(Collectors.toList());
            responseModel.setData(industries);
            singleSubscriber.onSuccess(responseModel);
        });
    }

    @Override
    @Transactional
    public Single<ResponseModel<Industries>> saveIndustry(IndustryModel industryModel) {

        return Single.create(singleSubscriber -> {

            if (industriesRepository.findByNameOrUrl(industryModel.getName(), industryModel.getUrl()).isPresent()) {
                singleSubscriber.onError(new AlreadyExists("Name already exist"));
            } else {
                Industries industries = new Industries();
                industries.setDescription(industryModel.getDescription());
                industries.setName(industryModel.getName());
                industries.setDescription(industryModel.getDescription());

                Attachment attachment = new Attachment();
                attachment.setAttachmentType(industryModel.getFileType());
                attachment.setAttachmentFor(AttachmentFor.INDUSTRY);
                try {
                    attachment.setAttachmentPath(Utility.writeBase64ToFile(industryModel.getImage(), industryModel.getUrl(), industryModel.getFileExt()));
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
            if (industries.isPresent()) {
                SingleIndustry singleIndustry = industries.map(Mapper::mapForSingleIndustry).get();
                singleIndustry.setDivisions(industries.get().getDivisionSet().stream().map(Division::getDivisionName).collect(Collectors.toList()));

                List<Object[]> jobs = jobsRepository.findJobsByIndustry(industries.get());
                List<JobsModel> jobsModels = new ArrayList<>();
                jobs.forEach(job -> {
                    jobsModels.add(new JobsModel(Long.parseLong(job[0].toString()), job[1].toString(), job[2].toString()));
                });
                singleIndustry.setAvailablePost(jobsModels);

                singleIndustryResponseModel.setData(singleIndustry);
            }
            singleSubscriber.onSuccess(singleIndustryResponseModel);

        });
    }

    @Override
    public Single<ResponseModel<List<DivisionModel>>> getDivisionByIndustriesUrl(String url) {
        return Single.create(singleSubscriber -> {
            ResponseModel<List<DivisionModel>> responseModel = new ResponseModel<>();
            List<Division> divisionList = divisionRepository.findByIndustriesUrl(url);
            List<DivisionModel> divisionModelList = new ArrayList<>();
            divisionList.forEach(division -> {
                divisionModelList.add(Mapper.map(division));
            });
            responseModel.setData(divisionModelList);
            singleSubscriber.onSuccess(responseModel);
        });
    }

    @Override
    public Single<ResponseModel<String>> saveDivision(DivisionModel divisionModel) {

        return Single.create(singleSubscriber -> {

            Industries industries = new Industries();
            industries.setIndustryId(divisionModel.getIndustryId());

            if (!industriesRepository.findById(divisionModel.getIndustryId()).isPresent()) {
                singleSubscriber.onError(new AlreadyExists("Industry Not available"));
            }
            else if(divisionRepository.findByDivisionNameAndIndustries(divisionModel.getDivisionName(),industries).isPresent()){
                singleSubscriber.onError(new AlreadyExists("Division Already Exist"));

            }
            else {

                Division division = new Division();
                division.setDivisionName(divisionModel.getDivisionName());
                division.setIndustries(industries);

                divisionRepository.save(division);

                ResponseModel<String> responseModel = new ResponseModel<>();
                responseModel.setData("Success");
                singleSubscriber.onSuccess(responseModel);

            }
        });
    }
}
