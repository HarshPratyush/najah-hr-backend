package in.co.najah.najahhr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mst_user")
public class MstUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188586041470160938L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "acc_id_fk",unique=true)
	@JsonIgnore
	private Account account;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "contact_number")
	private String contactNo;

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<UserAuthorityMapping> userAuthorityMappings;
}
