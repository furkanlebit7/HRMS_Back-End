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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

@CrossOrigin
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
	
	
	private CandidateService candidateService;
	
	@Autowired
	public CandidateController(CandidateService candidateService) {
		this.candidateService =candidateService;
	}
	
	@GetMapping("/getAll")
	@ResponseBody
	public DataResult<List<Candidate>>  getAll(){
		return this.candidateService.getAll();
	}
	
	
	@PostMapping("/add")
	public DataResult<Candidate>add(@RequestBody Candidate candidate){
		return this.candidateService.add(candidate);
	}

	@PutMapping("/uploadImage")
	public Result saveImage(@RequestBody MultipartFile file,@RequestParam int resumeId) {
		return this.candidateService.uploadImage(file, resumeId);
		
	}
	
	@PostMapping("/updateCandidate")
	Result updateCandidate(@RequestBody Candidate candidate) {
		return this.candidateService.updateCandidate(candidate);
	}
	
	
}
