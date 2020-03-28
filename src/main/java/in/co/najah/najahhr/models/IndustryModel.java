package in.co.najah.najahhr.models;


import in.co.najah.najahhr.util.ValidImage;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class IndustryModel {

    private String industryId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private String url;

    @NotNull
    @NotEmpty
    @ValidImage
    private String image;

    private String fileExt;
}
