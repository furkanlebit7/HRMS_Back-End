package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.converters.DtoConvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	
	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerDao employerDao;
	private CityDao cityDao;
	private JobTitleDao jobTitleDao;
	private DtoConvertService dtoConvertService;
	
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, EmployerDao employerDao, CityDao cityDao,
			JobTitleDao jobTitleDao, DtoConvertService dtoConvertService) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.jobTitleDao = jobTitleDao;
		this.dtoConvertService = dtoConvertService;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.findAll(),"İş ilanları Listelendi");
	}

	@Override
	public Result add(JobAdvertisementDto jobAdvertisement) {
		if(!jobPositionChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İş pozisyonu bulunamadı");
		}
		else if(!jobDescriptionChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İş Açıklaması alanı zorunludur");
		}
		else if(!jobCityChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İl bulunamadı");
		}
		else if(!jobMinSalaryController(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"Minimum değer sıfırın altında veya Maksimum değer üstünde olamaz");
		}
		else if(!jobMaxSalaryController(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"Maksimum değer sıfırın altında veya Minimum değer altında olamaz");
		}
		else if(!jobNumberChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"Açık pozisyon adedi boş veya sıfır olamaz");
		}
		else if(!jobDateChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"Son ilan tarihi alanı zorunludur");
		}
		else if(!isEmployerExist(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İş Veren bulunamadı");			
		}
		
		LocalDate e = LocalDate.now();
		jobAdvertisement.setCreatedDate(e);
		jobAdvertisement.setActive(false);
		this.jobAdvertisementDao.save((JobAdvertisement) dtoConvertService.dtoClassConverter(jobAdvertisement, JobAdvertisement.class));
		return new SuccessResult("İş ilanı başarılı bir şekilde eklendi");
	}
	
	
	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActive() {
		return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActive(true),"Başarılı");
	}
	
	

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveOrderByCreatedDateDesc() {
		return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActiveOrderByCreatedDateDesc(true),"Başarılı");
	}
	
	

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveAndEmployerId(int employerId) {
		if(!this.employerDao.existsById(employerId)) {
			return new ErrorDataResult("Hata: İş veren bulunamadı");
		}
		else {
			return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActiveAndEmployerId(true,employerId),"Başarılı");
		}
	}

	
	
	@Override
	public DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id) {
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult("Hata: İş ilanı bulunamadı");
		}
		JobAdvertisement jA =  this.jobAdvertisementDao.getOne(id);
		jA.setActive(false);
		return new SuccessDataResult <JobAdvertisement>(this.jobAdvertisementDao.save(jA),"İş İlanı Pasif olarak ayarlandı");
	}
	
	
	
	
	//*****************************************         CONTROLLERS             ********************************************
	
	
	private boolean jobPositionChecker(JobAdvertisementDto jobAdvertisement) {
		if(!jobTitleDao.existsById(jobAdvertisement.getJobTitleId())) {
			return false;
		}
		return true;
	}
	private boolean jobDescriptionChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return false;
		}
		return true;
	}
	private boolean jobCityChecker(JobAdvertisementDto jobAdvertisement) {
		if(!cityDao.existsById(jobAdvertisement.getCityId())) {
			return false;
		}
		return true;
	}
	private boolean jobMinSalaryController(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary()>0) {
			if(jobAdvertisement.getMinSalary()<0 || jobAdvertisement.getMinSalary()>=jobAdvertisement.getMaxSalary()) {
				return false;
			}
			return true;
		}
		return true;
		
	}
	private boolean jobMaxSalaryController(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary()>0) {
			if(jobAdvertisement.getMaxSalary()<0 || jobAdvertisement.getMaxSalary()<=jobAdvertisement.getMinSalary()) {
				return false;
			}
			return true;
		}
		return true;
		
	}
	private boolean jobNumberChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getNumberOfPosition()<=0) {
			return false;
		}
		return true;
	}
	
	private boolean jobDateChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getLastDate()==null) {
			return false;
		}
		return true;
	}
	
	private boolean isEmployerExist(JobAdvertisementDto jobAdvertisement) {
		if(!this.employerDao.existsById(jobAdvertisement.getEmployerId())) {
			return false;
		}
		return true;
	}
	

}
