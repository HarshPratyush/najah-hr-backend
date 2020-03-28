package in.co.najah.najahhr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames="user_name"))
public class Account implements Serializable{
	private static final long serialVersionUID = 1416515536173441248L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name")
	private String userName;

	@JsonIgnore
	private String password;

	@Column(name = "enabled", columnDefinition = "boolean DEFAULT true")
	private boolean enabled = true;

	@Column(name = "credentialexpired", columnDefinition = "boolean DEFAULT false")
	private boolean credentialexpired = false;

	@Column(name = "expired", columnDefinition = "boolean DEFAULT false")
	private boolean expired = false;

	@Column(name = "locked", columnDefinition = "boolean DEFAULT false")
	private boolean locked = false;

	@Email
	@NotNull
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<AccountDesignationMapping> accountDesignationMapping;

	@JsonIgnore
	@Column(name = "invalid_attempts", columnDefinition = "smallint default '0'")
	private short invalidAttempts;

	@OneToOne(mappedBy = "account")
	private MstUser mstUser;

	public Account() {
		super();
	}

	public Account(Integer id) {
		super();
		this.id = id;
	}
	
}
