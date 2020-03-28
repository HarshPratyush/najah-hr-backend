package in.co.najah.najahhr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsModel {

    private long positions;
    private String division;
    private String location;

}
