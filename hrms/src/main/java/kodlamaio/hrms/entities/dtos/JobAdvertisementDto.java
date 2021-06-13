package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JobAdvertisementDto {
	
	@JsonIgnore
	private int id;
	
	private int employerId;
	private int cityId;
	private int jobTitleId;
	private String description;
	private int numberOfPosition;
	private LocalDate lastDate;
	private LocalDate createdDate = LocalDate.now();
	private int minSalary;
	private int maxSalary;
	private boolean isActive=false;
	private String jobFeature;
	private String jobType;
	
}
