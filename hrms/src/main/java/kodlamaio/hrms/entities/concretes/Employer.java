package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisement"})
@Table(name="employers")
public class Employer extends User{
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_adress")
	private String webAdress;

	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="is_activated")
	private boolean isActivated;
	
	@Column(name="company_logo")
	private String companyLogo;
	
	
	@OneToMany(mappedBy="employer")
	private List<JobAdvertisement> jobAdvertisement;
}
