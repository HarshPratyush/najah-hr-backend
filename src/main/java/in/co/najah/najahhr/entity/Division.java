package in.co.najah.najahhr.entity;


import in.co.najah.najahhr.entity.audit.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true,exclude = {"jobsSet"})
@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Division extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "division_name",nullable = false,length = 100)
    private String divisionName;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Industries industries;

    @OneToMany(mappedBy = "division")
    private Set<Jobs> jobsSet ;

    public Division (Long id){
        this.id =id;
    }
}
