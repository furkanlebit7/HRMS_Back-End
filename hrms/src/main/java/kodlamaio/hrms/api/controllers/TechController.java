package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.TechService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Techs;

@RestController
@RequestMapping("/api/techs")
public class TechController {
	
	private TechService techService;

	@Autowired
	public TechController(TechService techService) {
		super();
		this.techService = techService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Techs>> getAll() {
		return this.techService.getAll();
	}
	
	
}
