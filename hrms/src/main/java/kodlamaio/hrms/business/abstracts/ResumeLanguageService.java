package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeLanguage;
import kodlamaio.hrms.entities.dtos.ResumeLanguagesDto;

public interface ResumeLanguageService {
	DataResult<List<ResumeLanguage>> getAll();
	Result add(ResumeLanguagesDto language);
	DataResult<List<ResumeLanguage>> findAllByResumeId(int id);
	Result removeById(int id);
}
