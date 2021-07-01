package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeLanguageService;
import kodlamaio.hrms.core.utilities.converters.DtoConvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeLanguageDao;
import kodlamaio.hrms.entities.concretes.ResumeLanguage;
import kodlamaio.hrms.entities.dtos.ResumeLanguagesDto;

@Service
public class ResumeLanguageManager implements ResumeLanguageService{
	
	private ResumeLanguageDao resumeLanguageDao;
	private DtoConvertService dtoConvertService;
	
	@Autowired
	public ResumeLanguageManager(ResumeLanguageDao resumeLanguageDao,DtoConvertService dtoConvertService) {
		super();
		this.resumeLanguageDao = resumeLanguageDao;
		this.dtoConvertService=dtoConvertService;
	}

	@Override
	public DataResult<List<ResumeLanguage>> getAll() {
		return new SuccessDataResult<List<ResumeLanguage>>(this.resumeLanguageDao.findAll(),"Resume Dilleri listelendi");
	}

	@Override
	public Result add(ResumeLanguagesDto language) {
		this.resumeLanguageDao.save((ResumeLanguage) dtoConvertService.dtoClassConverter(language, ResumeLanguage.class));
		return new SuccessResult("Dil Ekleme İşlemi Başarılır");
	}

	@Override
	public DataResult<List<ResumeLanguage>> findAllByResumeId(int id) {
		return new SuccessDataResult<List<ResumeLanguage>>(this.resumeLanguageDao.findAllByResumeId(id));
	}

	@Override
	public Result removeById(int id) {
		this.resumeLanguageDao.deleteById(id);
		return new SuccessResult("Dil başarılı bir şekilde silindi");
	}

}
