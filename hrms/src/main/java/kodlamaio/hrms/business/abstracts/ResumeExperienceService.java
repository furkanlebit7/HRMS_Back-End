package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.ResumeExperience;

public interface ResumeExperienceService {
	DataResult<List<ResumeExperience>> getAll();
}
