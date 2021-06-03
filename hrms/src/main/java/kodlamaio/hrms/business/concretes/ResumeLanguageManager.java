package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeLanguageDao;
import kodlamaio.hrms.entities.concretes.ResumeLanguage;

@Service
public class ResumeLanguageManager implements ResumeLanguageService{
	
	private ResumeLanguageDao resumeLanguageDao;
	
	@Autowired
	public ResumeLanguageManager(ResumeLanguageDao resumeLanguageDao) {
		super();
		this.resumeLanguageDao = resumeLanguageDao;
	}

	@Override
	public DataResult<List<ResumeLanguage>> getAll() {
		return new SuccessDataResult<List<ResumeLanguage>>(this.resumeLanguageDao.findAll(),"Resume Dilleri listelendi");
	}

}
