package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="job_advertisements")
public class JobAdvertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	int id;
	
	//@Column(name="job_titles_id")
	//int jobTitleId;
	

	//@Column(name="employer_id")
	//private int employerId;
	

	//@Column(name="city_id")
	//int cityId;
	
	@Column(name="description")
	String description;
	
	@Column(name="number_of_position")
	int numberOfPosition;
	
	@Column(name="min_salary")
	int minSalary;
	
	@Column(name="max_salary")
	int maxSalary;
	
	@Column(name="last_date")
	private LocalDate lastDate;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="job_feature")
	private String jobFeature;
	
	@Column(name="job_type")
	private String jobType;
	
	@ManyToOne()
	@JoinColumn(name="job_title_id")
	private JobTitle jobTitle;
	
	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;
	
}
