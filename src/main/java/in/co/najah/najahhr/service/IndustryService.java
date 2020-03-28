package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.models.IndustriesModel;
import in.co.najah.najahhr.models.IndustryModel;
import in.co.najah.najahhr.models.ResponseModel;
import in.co.najah.najahhr.models.SingleIndustry;
import rx.Single;

import java.util.List;

public interface IndustryService {

    Single<ResponseModel<List<IndustriesModel>>> getAllIndustries();

    Single<ResponseModel<Industries>> saveIndustry(IndustryModel industryModel);

    Single<ResponseModel<SingleIndustry>> getByIdentifier(String identifier);
}
