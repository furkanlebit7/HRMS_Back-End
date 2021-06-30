package kodlamaio.hrms.entities.concretes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Entity
@Data
@Table(name="candidates")
public class Candidate extends User{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "identity_number")
	private String identityNumber;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "photo")
	private String photo;
}
