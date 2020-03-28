package in.co.najah.najahhr.entity;

import in.co.najah.najahhr.entity.audit.Audit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Jobs extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private String emailId;

    @Column(nullable = false)
    private long openings;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Division division;


    @Column(name = "subject",nullable = false,length = 100)
    private String subject;


    @Column(name = "requirement",nullable = false,length = 1000)
    private String requirement;


    @Column(nullable = false)
    private boolean isArchived=false;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String companyName;
}
