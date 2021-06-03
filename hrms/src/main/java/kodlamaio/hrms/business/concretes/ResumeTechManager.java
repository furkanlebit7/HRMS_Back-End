package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeTechService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeTechDao;
import kodlamaio.hrms.entities.concretes.ResumeTechs;

@Service
public class ResumeTechManager implements ResumeTechService{

	private ResumeTechDao resumeTechDao;
	
	@Autowired
	public ResumeTechManager(ResumeTechDao resumeTechDao) {
		super();
		this.resumeTechDao = resumeTechDao;
	}

	@Override
	public DataResult<List<ResumeTechs>> getAll() {
		return new SuccessDataResult<List<ResumeTechs>>(this.resumeTechDao.findAll(),"Resume teknolojileri listelendi");
	}

}
