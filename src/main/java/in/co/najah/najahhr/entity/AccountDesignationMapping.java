package in.co.najah.najahhr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class AccountDesignationMapping implements Serializable{

	private static final long serialVersionUID = 7185471006748344141L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "acc_id_fk")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "designation_id_fk")
	private Designation designation;
}
