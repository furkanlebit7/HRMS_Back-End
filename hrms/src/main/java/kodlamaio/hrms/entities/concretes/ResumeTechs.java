package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resume_techs")
public class ResumeTechs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	//@Column(name="resume_id")
	//private int resumeId;
	
	//@Column(name="tech_id")
	//private int techId;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@ManyToOne(targetEntity = Resume.class)
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	@ManyToOne()
	@JoinColumn(name="tech_id")
	private Techs techs;
}
