package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ResumeLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.ResumeLanguage;

@CrossOrigin
@RestController
@RequestMapping("/api/resumeLanguage")
public class ResumeLanguageController {
	
	private ResumeLanguageService resumeLanguageService;

	@Autowired
	public ResumeLanguageController(ResumeLanguageService resumeLanguageService) {
		super();
		this.resumeLanguageService = resumeLanguageService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ResumeLanguage>> getAll(){
		return this.resumeLanguageService.getAll();
	}
	
	
}
