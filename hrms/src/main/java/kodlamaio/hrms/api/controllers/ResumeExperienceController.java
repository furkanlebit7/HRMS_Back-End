package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ResumeExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeExperience;
import kodlamaio.hrms.entities.dtos.ResumeExperiencesDto;
import kodlamaio.hrms.entities.dtos.ResumeSchoolsDto;

@CrossOrigin
@RestController
@RequestMapping("/api/resumeExperience")
public class ResumeExperienceController {

	private ResumeExperienceService resumeExperienceService;

	@Autowired
	public ResumeExperienceController(ResumeExperienceService resumeExperienceService) {
		super();
		this.resumeExperienceService = resumeExperienceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ResumeExperience>> getAll(){
		return this.resumeExperienceService.getAll();
	}
	
	@GetMapping("/getByCandidateId")
		public DataResult<List<ResumeExperience>> findAllByResumeId(@RequestParam int id){
			return this.resumeExperienceService.findAllByResumeId(id);
		}
		
	@GetMapping("/removeById")
	Result removeById(@RequestParam int id) {
		return this.resumeExperienceService.removeById(id);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody ResumeExperiencesDto school) {
		return this.resumeExperienceService.add(school);
	}
}
