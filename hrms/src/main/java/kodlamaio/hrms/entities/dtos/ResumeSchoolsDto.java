package kodlamaio.hrms.entities.dtos;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeSchoolsDto {
	
	@JsonIgnore
	private int id;
	private int resumeId;
	private String degree;
	private String department;
	private LocalDate startedDate;
	private LocalDate endDate;
	private boolean isGoing;
	private String schoolName;
	
}
