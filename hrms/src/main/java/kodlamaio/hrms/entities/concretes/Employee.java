package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class Employee extends User{

	@Column(name = "first_name")
	private String firtsName;
	
	@Column(name = "last_name")
	private String lastName;
}
