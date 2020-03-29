package in.co.najah.najahhr.rest;


import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.models.*;
import in.co.najah.najahhr.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RequestMapping("industry")
@RestController
public class IndustryController {

    @Autowired
   private IndustryService industryService;

    @GetMapping
    public Single<ResponseModel<List<IndustriesModel>>> getAllIndustry(){
        return industryService.getAllIndustries();
    }

    @PostMapping
    public Single<ResponseModel<Industries>> saveIndustry(@Valid @RequestBody IndustryModel industryModel){
        return industryService.saveIndustry(industryModel);
    }

    @GetMapping("{identifier}"  )
    public Single<ResponseModel<SingleIndustry>> getIndustry(@PathVariable("identifier") String identifier){
       return industryService.getByIdentifier(identifier);
    }

    @GetMapping("division/{industriesUrl}"  )
    public Single<ResponseModel<List<DivisionModel>>> getDivision(@PathVariable("industriesUrl") String url){
        return industryService.getDivisionByIndustriesUrl(url);
    }

    @PostMapping("division")
    public Single<ResponseModel<String>> saveDivision(@Valid @RequestBody DivisionModel divisionModel){
        return industryService.saveDivision(divisionModel);
    }
}
