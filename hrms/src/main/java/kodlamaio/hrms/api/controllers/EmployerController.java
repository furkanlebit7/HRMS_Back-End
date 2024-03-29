package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

@CrossOrigin
@RestController
@RequestMapping("/api/employers")
public class EmployerController {
	
	
	private EmployerService employerService;
	
	@Autowired
	public EmployerController(EmployerService employerService) {
		this.employerService=employerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employer>> getAll(){
		return this.employerService.getAll();
	}
	
	@PostMapping("/add")
	public DataResult<Employer> add(@RequestBody Employer employer){
		return this.employerService.add(employer);
	}
	
	@PutMapping("/uploadImage")
	public Result saveImage(@RequestBody MultipartFile file,@RequestParam int employerId) {
		return this.employerService.uploadImage(file, employerId);	
	}
	@PutMapping("/uploadBanner")
	public Result saveImageBanner(@RequestBody MultipartFile file,@RequestParam int employerId) {
		return this.employerService.uploadBanner(file, employerId);	
	}
	
	@GetMapping("/verifyChecker")
	Result verifyChecker(Integer employerId) {
		return this.employerService.verifyChecker(employerId);
	}
	
	@GetMapping("/getThree")
	public DataResult<List<Employer>> getThree(){
		return this.employerService.getEmployerThree();
	}
	
	@PostMapping("/updateEmployer")
	public Result updateEmployer(@RequestBody Employer employer) {
		return this.employerService.updateEmployer(employer);
	}
	
	@PostMapping("updateNameEmail")
	public Result updateNameEmail(@RequestParam int employerId,@RequestParam String companyName,@RequestParam String companyEmail) {
		return this.employerService.updateNameEmail(employerId, companyName, companyEmail);
	}
	
}
