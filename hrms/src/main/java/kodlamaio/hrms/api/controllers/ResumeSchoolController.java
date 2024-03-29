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

import kodlamaio.hrms.business.abstracts.ResumeSchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeSchools;
import kodlamaio.hrms.entities.dtos.ResumeSchoolsDto;

@CrossOrigin
@RestController
@RequestMapping("/api/resumeSchool")
public class ResumeSchoolController {

	private ResumeSchoolService resumeSchoolService;

	@Autowired
	public ResumeSchoolController(ResumeSchoolService resumeSchoolService) {
		super();
		this.resumeSchoolService = resumeSchoolService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ResumeSchools>> getAll(){
		return this.resumeSchoolService.getAll();
	}
	
	@GetMapping("/findByResumeId")
	public DataResult<List<ResumeSchools>> findByResumeId(@RequestParam int id){
		return this.resumeSchoolService.findByResumeId(id);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody ResumeSchoolsDto school) {
		return this.resumeSchoolService.add(school);
	}
	
	@GetMapping("/removeById")
	Result removeById(@RequestParam int id) {
		return this.resumeSchoolService.removeById(id);
	}
}
