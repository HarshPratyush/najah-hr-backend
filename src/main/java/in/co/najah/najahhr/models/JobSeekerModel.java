package in.co.najah.najahhr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerModel {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private  String emailId;

    @NotNull
    @NotEmpty
    private String subject;

    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    private long divisionId;

    @NotNull
    @NotEmpty
    private String dob;

    @NotNull
    @NotEmpty
    private String resume;

    @NotNull
    @NotEmpty
    private String fileExt;

}
