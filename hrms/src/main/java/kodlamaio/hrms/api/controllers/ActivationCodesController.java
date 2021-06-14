package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ActivationCodes;

@RestController
@RequestMapping("/api/verify")
public class ActivationCodesController {
	private ActivationCodeService activationCodeService;
	
	@Autowired
	public ActivationCodesController(ActivationCodeService activationCodeService) {
		this.activationCodeService=activationCodeService;
	}
	
	@PostMapping("/update/{activationCode}/{id}")
	Result verify(@RequestParam String verificationCode,@RequestParam Integer id) {
		return activationCodeService.verify(verificationCode, id);
	}
	
	@GetMapping("/getOneByUserId")
	DataResult<ActivationCodes> findOneById(@RequestParam Integer id){
		return this.activationCodeService.findOneById(id);
	}
}
