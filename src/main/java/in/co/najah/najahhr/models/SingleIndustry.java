package in.co.najah.najahhr.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SingleIndustry extends  IndustriesModel{

    private List<String> divisions=new ArrayList<>();

    private List<JobsModel> availablePost=new ArrayList<>();
}
