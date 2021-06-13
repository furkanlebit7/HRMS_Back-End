package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService{
	
	
	private JobTitleDao jobTitleDao;
	
	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}
	
	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(),"JobTitle Listeleme Başarılı");
	}
	
	@Override
	public Result add(JobTitle jobTitle) {
		if(jobTitleDao.findAllByTitle(jobTitle.getTitle()).stream().count()!=0) {
			return new ErrorDataResult<JobTitle>(null,"İş Pozisyonu zaten mevcut");
		}
			
			return new SuccessDataResult<JobTitle>(this.jobTitleDao.save(jobTitle),"İş Pozisyonu başarılı bir şekilde eklendi");
	}

	
	
	


	

}
