package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeSchools;
import kodlamaio.hrms.entities.dtos.ResumeSchoolsDto;

public interface ResumeSchoolService {
	DataResult<List<ResumeSchools>> getAll();
	Result add(ResumeSchoolsDto school);
	DataResult<List<ResumeSchools>> findByResumeId(int id);
	Result removeById(int id);
}
