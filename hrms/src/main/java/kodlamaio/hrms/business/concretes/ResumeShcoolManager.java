package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeSchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeSchoolDao;
import kodlamaio.hrms.entities.concretes.ResumeSchools;

@Service
public class ResumeShcoolManager implements ResumeSchoolService{
	
	private ResumeSchoolDao resumeSchoolDao;
	
	@Autowired
	public ResumeShcoolManager(ResumeSchoolDao resumeSchoolDao) {
		super();
		this.resumeSchoolDao = resumeSchoolDao;
	}

	@Override
	public DataResult<List<ResumeSchools>> getAll() {
		return new SuccessDataResult<List<ResumeSchools>>(this.resumeSchoolDao.findAll(),"Resume Okulları listelendi");
	}

	@Override
	public DataResult<List<ResumeSchools>> findByResumeId(int id) {
		return new SuccessDataResult<List<ResumeSchools>>(this.resumeSchoolDao.findByResumeId(id),"Kişinin Okul bilgileri listelendi");
	}
	
}
