package in.co.najah.najahhr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Authority implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5669097464796515240L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String authority;

	private String description;

	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
	List<UserAuthorityMapping> userAuthorityMappings;

	public Authority(String authority, String description) {
		this.authority = authority;
		this.description = description;
	}

	public Authority() {
	}
}
