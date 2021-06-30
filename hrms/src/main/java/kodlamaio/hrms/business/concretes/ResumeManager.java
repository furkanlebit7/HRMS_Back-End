package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.utilities.cloudinary.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeLanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeSchoolDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeTechDao;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.concretes.ResumeExperience;
import kodlamaio.hrms.entities.concretes.ResumeLanguage;
import kodlamaio.hrms.entities.concretes.ResumeSchools;
import kodlamaio.hrms.entities.concretes.ResumeTechs;

@Service
public class ResumeManager implements ResumeService{
	
	private ResumeDao resumeDao;
	private ResumeSchoolDao resumeSchoolDao;
	private ResumeLanguageDao resumeLanguageDao;
	private ResumeExperienceDao resumeExperienceDao;
	private ResumeTechDao resumeTechDao;


	
	@Autowired
	public ResumeManager(ResumeDao resumeDao, ResumeSchoolDao resumeSchoolDao, ResumeLanguageDao resumeLanguageDao,
			ResumeExperienceDao resumeExperienceDao, ResumeTechDao resumeTechDao) {
		super();
		this.resumeDao = resumeDao;
		this.resumeSchoolDao = resumeSchoolDao;
		this.resumeLanguageDao = resumeLanguageDao;
		this.resumeExperienceDao = resumeExperienceDao;
		this.resumeTechDao = resumeTechDao;
	}

	@Override
	public Result add(Resume resume) {
		
		Resume referansResume = resumeDao.save(resume);
		
		setResumeSchools(referansResume.getResumeSchools(),referansResume);
		setResumeLanguages(referansResume.getResumeLanguages(),referansResume);
		setResumeExperience(referansResume.getResumeExperiences(),referansResume);
		setResumeTechs(referansResume.getResumeTechs(),referansResume);
		
		return new SuccessResult("Resume eklendi");
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll(),"CV ler listelendi");
	}

	@Override
	public DataResult<List<Resume>> findByCandidateId(int id) {
		return new SuccessDataResult<List<Resume>>(this.resumeDao.findByCandidateId(id));
	}
	
	private void setResumeSchools(List<ResumeSchools> rs, Resume resume){
		for (ResumeSchools resumeSchools : rs) {
			resumeSchools.setResume(resume);
			resumeSchoolDao.save(resumeSchools);
		}
	}
	
	private void setResumeLanguages(List<ResumeLanguage> ln,Resume resume) {
		for (ResumeLanguage resumeLanguage : ln) {
			resumeLanguage.setResume(resume);
			resumeLanguageDao.save(resumeLanguage);
		}
	}
	
	private void setResumeExperience(List<ResumeExperience> re, Resume resume) {
		for (ResumeExperience resumeExperience : re) {
			resumeExperience.setResume(resume);
			resumeExperienceDao.save(resumeExperience);
		}
	}
	
	private void setResumeTechs(List<ResumeTechs> rt,Resume resume) {
		for (ResumeTechs resumeTechs : rt) {
			resumeTechs.setResume(resume);
			resumeTechDao.save(resumeTechs);
		}
	}

	@Override
	public Result updateResume(Resume resume) {
		Resume ref = new Resume();
		ref=resume;
		this.resumeDao.save(ref);
		return new SuccessResult("Güncelleme Başarılı");
	}

	

}









