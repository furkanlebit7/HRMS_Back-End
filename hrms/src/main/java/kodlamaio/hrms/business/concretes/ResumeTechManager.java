package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeTechService;
import kodlamaio.hrms.core.utilities.converters.DtoConvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeTechDao;
import kodlamaio.hrms.entities.concretes.ResumeTechs;
import kodlamaio.hrms.entities.dtos.ResumeTechsDto;

@Service
public class ResumeTechManager implements ResumeTechService{

	private ResumeTechDao resumeTechDao;
	private DtoConvertService dtoConvertService;
	
	@Autowired
	public ResumeTechManager(ResumeTechDao resumeTechDao,DtoConvertService dtoConvertService) {
		super();
		this.resumeTechDao = resumeTechDao;
		this.dtoConvertService=dtoConvertService;
	}

	@Override
	public DataResult<List<ResumeTechs>> getAll() {
		return new SuccessDataResult<List<ResumeTechs>>(this.resumeTechDao.findAll(),"Resume teknolojileri listelendi");
	}

	@Override
	public Result removeById(int id) {
		this.resumeTechDao.deleteById(id);
		return new SuccessResult("Teknoloji başarılı bir şekilde silindi");
	}


	@Override
	public DataResult<List<ResumeTechs>> findAllByResumeId(int id) {
		return new SuccessDataResult<List<ResumeTechs>>(this.resumeTechDao.findAllByResumeId(id),"Teknolojiler Cv ye göre listelendi");
	}

	@Override
	public Result add(ResumeTechsDto tech) {
		this.resumeTechDao.save((ResumeTechs) dtoConvertService.dtoClassConverter(tech, ResumeTechs.class));
		return new SuccessResult("Teknoloji Ekleme İşlemi Başarılı");
	}

}
