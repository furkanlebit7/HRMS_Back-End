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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})
@Table(name="resume_experience")
public class ResumeExperience {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		@Column(name="id")
		private int id;
		
		//@Column(name="resume_id")
		//private int resumeId;
		
		@Column(name="workplace_name")
		@NotNull
		@NotBlank
		private String workplaceName;
		
		//@Column(name="position_id")
		//private int positionId;
		
		@Column(name="started_date")
		@NotNull
		@NotBlank
		private LocalDate startedDate;
	
		@Column(name="end_date")
		@NotNull
		@NotBlank
		private LocalDate endDate;
		
		@NotNull
		@Column(name="is_going")
		private boolean isGoing;
		
		@ManyToOne()
		@JoinColumn(name="position_id")
		private JobTitle jobTitle;
		
		@JsonProperty(access=Access.WRITE_ONLY)
		@ManyToOne()
		@JoinColumn(name="resume_id")
		private Resume resume;
}
