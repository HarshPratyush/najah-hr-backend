package in.co.najah.najahhr.entity;



import in.co.najah.najahhr.entity.audit.Audit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true,exclude={"divisionSet"})
@Data
@Entity
@Table(name = "mst_industry")
public class Industries extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industry_id")
    private long industryId;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "url",nullable = false,unique = true)
    private String url;

    @Column(name = "description",nullable = false,length = 1000)
    private String description;

    @Column(name="attachment_id",nullable = false,unique = true)
    private long attachmentId;

    @OneToMany(mappedBy = "industries")
    Set<Division> divisionSet;
}
