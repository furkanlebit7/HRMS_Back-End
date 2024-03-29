package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","resume"})
@Table(name="resume_schools")
public class ResumeSchools {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="school_name")
	private String schoolName;
	
	//@Column(name="resume_id")
	//private int resumeId;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="department")
	private String department;
	
	@Column(name="started_date")
	private LocalDate startedDate;
	
	@Column(name="is_going")
	private boolean isGoing;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@ManyToOne(targetEntity = Resume.class)
	@JoinColumn(name="resume_id")
	private Resume resume;
}
