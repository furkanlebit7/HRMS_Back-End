package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeExperienceDao;
import kodlamaio.hrms.entities.concretes.ResumeExperience;

@Service
public class ResumeExperienceManager implements ResumeExperienceService{

	private ResumeExperienceDao resumeExperienceDao;
		
	@Autowired
	public ResumeExperienceManager(ResumeExperienceDao resumeExperienceDao) {
		super();
		this.resumeExperienceDao = resumeExperienceDao;
	}

	@Override
	public DataResult<List<ResumeExperience>> getAll() {
		return new SuccessDataResult<List<ResumeExperience>>(this.resumeExperienceDao.findAll(),"Resume Deneyimleri listelendi");
	}

}
