package in.co.najah.najahhr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
public class DesignationAuthorityMapping implements Serializable {
	private static final long serialVersionUID = -984353118984797667L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "designation_id_fk")
	private Designation designation;

	@ManyToOne
	@JoinColumn(name = "authority_id_fk")
	private Authority authority;
}
