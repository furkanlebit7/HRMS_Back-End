package kodlamaio.hrms.entities.concretes;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resumes")
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="candidate_id")
	private int candidateId;
	
	@Column(name="candidate_photo")
	private String candidatePhoto;
	
	@Column(name="candidate_github")
	private String candidateGithub;
	
	@Column(name="candidate_linkedin")
	private String candidateLinkedin;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_active")
	private boolean is_Active;
	
	
	@OneToMany(mappedBy = "resume",fetch = FetchType.LAZY)
	private List<ResumeLanguage> resumeLanguages;
	
	@OneToMany(mappedBy = "resume",fetch = FetchType.LAZY)
	private List<ResumeExperience> resumeExperiences;
	
	@OneToMany(mappedBy = "resume",fetch = FetchType.LAZY)
	private List<ResumeTechs> resumeTechs;
	
	@OneToMany(mappedBy="resume",fetch = FetchType.LAZY)
	private List<ResumeSchools> resumeSchools;

}
