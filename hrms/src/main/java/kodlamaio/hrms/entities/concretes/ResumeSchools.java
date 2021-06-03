package kodlamaio.hrms.entities.concretes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})
@Table(name="resume_schools")
public class ResumeSchools {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	//@Column(name="resume_id")
	//private int resumeId;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="department")
	@NotNull
	@NotBlank
	private String department;
	
	@Column(name="started_date")
	@NotNull
	@NotBlank
	private Date startedDate;
	
	@Column(name="is_going")
	@NotNull
	private boolean isGoing;
	
	@Column(name="end_date")
	private Date endDate;
	
	@ManyToOne(targetEntity = Resume.class)
	@JoinColumn(name="resume_id")
	private Resume resume;
}
