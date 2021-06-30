package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	
	DataResult<List<JobAdvertisement>> getAll();
	
	Result add(JobAdvertisementDto jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> findAllByIsActive();
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveOrderByCreatedDateDesc();
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveAndEmployerId(int employerId);
	
	DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id);
	
	DataResult<JobAdvertisement> setJobAdvertisementActive(int id);
	
	DataResult<List<JobAdvertisement>>getJobAdvertisementFour();
	
	DataResult<List<JobAdvertisement>> getAllPageable(int pageNo,int pageSize);
}
