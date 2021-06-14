package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerActivationByEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployerActivationByEmployee;

@RestController
@RequestMapping("/api/EmployerActivationByEmployee")
public class EmployerActivationByEmployeeController {
	
	private EmployerActivationByEmployeeService employerActivationByEmployeeService;

	@Autowired
	public EmployerActivationByEmployeeController(
			EmployerActivationByEmployeeService employerActivationByEmployeeService) {
		super();
		this.employerActivationByEmployeeService = employerActivationByEmployeeService;
	}
	
	@PostMapping("/verifyEmployer")
	Result verifyEmployer(@RequestBody EmployerActivationByEmployee eabe) {
		return this.employerActivationByEmployeeService.verifyEmployer(eabe);
	}
	
	
	
}
