package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeExperience;
import kodlamaio.hrms.entities.dtos.ResumeExperiencesDto;

public interface ResumeExperienceService {
	DataResult<List<ResumeExperience>> getAll();
	DataResult<List<ResumeExperience>> findAllByResumeId(int id);
	Result removeById(int id);
	Result add(ResumeExperiencesDto experience);
}
