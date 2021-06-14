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

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

@CrossOrigin
@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementController {
		
	private JobAdvertisementService jobAdvertisementService;
	@Autowired
	public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService=jobAdvertisementService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.jobAdvertisementService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisementDto jobAdvertisement){
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("/getAllActive")
	public DataResult<List<JobAdvertisement>>  getAllActive(){
		return this.jobAdvertisementService.findAllByIsActive();
	}
	
	@GetMapping("/getAllActiveSorted")
	public DataResult<List<JobAdvertisement>> getAllActiveSorted(){
		return this.jobAdvertisementService.findAllByIsActiveOrderByCreatedDateDesc();
	}
	
	@GetMapping("/getEmployerAdvertisement")
	public DataResult<List<JobAdvertisement>> getAllEmployerAdvertisement(@RequestParam int employerId){
		return this.jobAdvertisementService.findAllByIsActiveAndEmployerId(employerId);
	}
	
	@GetMapping("/setAdvertisementDisabled")
	public DataResult<JobAdvertisement> setAdvertisementDisabled(@RequestParam int id){
		return this.jobAdvertisementService.setJobAdvertisementDisabled(id);
	}
	
	@GetMapping("/setAdvertisementActive")
	DataResult<JobAdvertisement> setJobAdvertisementActive(@RequestParam int id){
		return this.jobAdvertisementService.setJobAdvertisementActive(id);
	}
	
	
	
	
	
}	
















