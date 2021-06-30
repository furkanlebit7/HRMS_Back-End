package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateService {
	DataResult<Candidate> add(Candidate candidate);
	DataResult<List<Candidate>>getAll();
	Result uploadImage(MultipartFile file, int candidateId);
	Result updateCandidate(Candidate candidate);
}
