package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TechService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.TechDao;
import kodlamaio.hrms.entities.concretes.Techs;

@Service
public class TechManager implements TechService{

	private  TechDao techDao;
	
	@Autowired
	public TechManager(TechDao techDao) {
		super();
		this.techDao = techDao;
	}

	@Override
	public DataResult<List<Techs>> getAll() {
		return new SuccessDataResult<List<Techs>>(this.techDao.findAll(),"Teknolojiler Listelendi");
	}
}
