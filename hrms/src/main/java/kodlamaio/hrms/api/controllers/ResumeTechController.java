package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ResumeTechService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.ResumeTechs;

@RestController
@RequestMapping("/api/resumeTech")
public class ResumeTechController {

	private ResumeTechService resumeTechService;

	@Autowired
	public ResumeTechController(ResumeTechService resumeTechService) {
		super();
		this.resumeTechService = resumeTechService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ResumeTechs>> getAll(){
		return this.resumeTechService.getAll();
	}
	
	
}
