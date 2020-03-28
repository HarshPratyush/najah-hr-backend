package in.co.najah.najahhr.models;

import in.co.najah.najahhr.entity.Division;
import in.co.najah.najahhr.entity.audit.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsModelExtended {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String emailId;

    @NotNull
    private long openings;

    @NotNull
    private long division;

    private String subject;

    @NotNull
    @NotEmpty
    private String requirement;

    private boolean isArchived=false;

    @NotNull
    @NotEmpty
    private String location;

    @NotNull
    @NotEmpty
    private String companyName;
}
