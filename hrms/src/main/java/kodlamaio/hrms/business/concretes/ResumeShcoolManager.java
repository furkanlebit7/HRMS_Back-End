package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeSchoolService;
import kodlamaio.hrms.core.utilities.converters.DtoConvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeSchoolDao;
import kodlamaio.hrms.entities.concretes.ResumeSchools;
import kodlamaio.hrms.entities.dtos.ResumeSchoolsDto;

@Service
public class ResumeShcoolManager implements ResumeSchoolService{
	
	private ResumeSchoolDao resumeSchoolDao;
	private DtoConvertService dtoConvertService;
	
	@Autowired
	public ResumeShcoolManager(ResumeSchoolDao resumeSchoolDao, DtoConvertService dtoConvertService) {
		super();
		this.resumeSchoolDao = resumeSchoolDao;
		this.dtoConvertService = dtoConvertService;
	}
	
	
	@Override
	public DataResult<List<ResumeSchools>> getAll() {
		return new SuccessDataResult<List<ResumeSchools>>(this.resumeSchoolDao.findAll(),"Resume Okulları listelendi");
	}

	

	@Override
	public DataResult<List<ResumeSchools>> findByResumeId(int id) {
		return new SuccessDataResult<List<ResumeSchools>>(this.resumeSchoolDao.findByResumeId(id),"Kişinin Okul bilgileri listelendi");
	}

	@Override
	public Result add(ResumeSchoolsDto school) {
		this.resumeSchoolDao.save((ResumeSchools) dtoConvertService.dtoClassConverter(school, ResumeSchools.class));
		return new SuccessResult("Okul Ekleme İşlemi Başarılır");
	}


	@Override
	public Result removeById(int id) {
		this.resumeSchoolDao.deleteById(id);
		return new SuccessResult("Okul Silme işlemi başarılı");
	}
	
}
