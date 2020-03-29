package in.co.najah.najahhr.service;

import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.models.*;
import rx.Single;

import java.util.List;

public interface IndustryService {

    Single<ResponseModel<List<IndustriesModel>>> getAllIndustries();

    Single<ResponseModel<Industries>> saveIndustry(IndustryModel industryModel);

    Single<ResponseModel<SingleIndustry>> getByIdentifier(String identifier);

    Single<ResponseModel<List<DivisionModel>>> getDivisionByIndustriesUrl(String url);

    Single<ResponseModel<String>>  saveDivision(DivisionModel divisionModel);
}
