package in.co.najah.najahhr.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class AuditModel {

    private String createdOn;

    private String updatedOn;

    private String createdBy;

    private String updatedBy;
}
