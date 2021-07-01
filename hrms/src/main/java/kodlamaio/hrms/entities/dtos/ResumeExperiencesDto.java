package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeExperiencesDto {
	
	@JsonIgnore
	private int id;
	private int resumeId;
	private String workplaceName;
	private int jobTitleId;
	private LocalDate startedDate;
	private LocalDate endDate;
	private boolean isGoing;
}
