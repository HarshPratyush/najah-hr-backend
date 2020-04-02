package in.co.najah.najahhr.models;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class JobsFullModel extends AuditModel{

    private long id;

    private String name;

    private String emailId;

    private long openings;

    private String subject;

    private String requirement;

    private boolean isArchived=false;

    private String location;

    private String companyName;

    private Long divisionId;

}
