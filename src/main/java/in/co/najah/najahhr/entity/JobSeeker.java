package in.co.najah.najahhr.entity;

import in.co.najah.najahhr.entity.audit.Audit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class JobSeeker extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private String emailId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Division division;

    @Column(nullable = false)
    private LocalDate dob;

    @JoinColumn(name = "attachment_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment resume;
}
