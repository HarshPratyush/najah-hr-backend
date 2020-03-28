package in.co.najah.najahhr.entity;


import in.co.najah.najahhr.entity.audit.Audit;
import in.co.najah.najahhr.enums.AttachmentFor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Table
@Entity
public class Attachment extends Audit {

    @Id
    @Column(name="attachment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attachmentId;

    @Column(name = "attachment_path",nullable = false,length = 500)
    private String attachmentPath;

    @Column(name = "attachment_type" , nullable = false)
    private String attachmentType;

    @Enumerated(EnumType.STRING)
    @Column(name ="attachment_for",length = 10)
    private AttachmentFor attachmentFor;
}
