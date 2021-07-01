package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeExperienceService;
import kodlamaio.hrms.core.utilities.converters.DtoConvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeExperienceDao;
import kodlamaio.hrms.entities.concretes.ResumeExperience;
import kodlamaio.hrms.entities.dtos.ResumeExperiencesDto;

@Service
public class ResumeExperienceManager implements ResumeExperienceService{

	private ResumeExperienceDao resumeExperienceDao;
	private DtoConvertService dtoConvertService;
		
	@Autowired
	public ResumeExperienceManager(ResumeExperienceDao resumeExperienceDao,DtoConvertService dtoConvertService) {
		super();
		this.resumeExperienceDao = resumeExperienceDao;
		this.dtoConvertService=dtoConvertService;
	}

	@Override
	public DataResult<List<ResumeExperience>> getAll() {
		return new SuccessDataResult<List<ResumeExperience>>(this.resumeExperienceDao.findAll(),"Resume Deneyimleri listelendi");
	}

	@Override
	public DataResult<List<ResumeExperience>> findAllByResumeId(int id) {
		return new SuccessDataResult<List<ResumeExperience>>(this.resumeExperienceDao.findAllByResumeId(id));
	}

	@Override
	public Result removeById(int id) {
		this.resumeExperienceDao.deleteById(id);
		return new SuccessResult("Deneyim Silme işlemi başarılı");
	}

	@Override
	public Result add(ResumeExperiencesDto experience) {
		this.resumeExperienceDao.save((ResumeExperience) dtoConvertService.dtoClassConverter(experience,ResumeExperience.class));
		return new SuccessResult("Deneyim Ekleme İşlemi Başarılır");
	}

}
