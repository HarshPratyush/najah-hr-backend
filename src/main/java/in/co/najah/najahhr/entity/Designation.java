package in.co.najah.najahhr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Designation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4165334309541345226L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String code;

	@JsonIgnore
	@OneToMany(mappedBy = "designation", fetch = FetchType.LAZY)
	List<DesignationAuthorityMapping> designationAuthorityMapping;

	@JsonIgnore
	@OneToMany(mappedBy = "designation", fetch = FetchType.LAZY)
	List<AccountDesignationMapping> accountDesignationMappings;

	
	public Designation() {
		super();
	}
	
	public Designation(Integer id) {
		this.id = id;
	}

	
}
