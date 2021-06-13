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

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobTitle;

@CrossOrigin
@RestController
@RequestMapping("/api/jobTitle")
public class JobTitlesController {
	
	private JobTitleService jobTitleService;

	@Autowired
	public JobTitlesController(JobTitleService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobTitle>> getAll(){
		return this.jobTitleService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobTitle jobTitle) {
		return this.jobTitleService.add(jobTitle);
	}
	
	
	
	/*
	requestBody = gelen requestin(Product)ın bi body'si var demek. execute edince Productın entitylerini de JSON layıp bilgi beraberinde gönderir.
	gelen request bilgilerinin Product entity leri ile eşleştirir yerine koyar.
	*/
}
