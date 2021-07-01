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

import kodlamaio.hrms.business.abstracts.ResumeTechService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeTechs;
import kodlamaio.hrms.entities.dtos.ResumeTechsDto;

@CrossOrigin
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
	
	@GetMapping("/removeById")
	Result removeById(@RequestParam int id) {
		return this.resumeTechService.removeById(id);
	}
	
	@GetMapping("/getByResumeId")
	public DataResult<List<ResumeTechs>> findAllByResumeId(@RequestParam int id){
		return this.resumeTechService.findAllByResumeId(id);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody ResumeTechsDto tech) {
		return this.resumeTechService.add(tech);
	}
	
	
}
