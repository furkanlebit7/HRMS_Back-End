package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	
	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerDao employerDao;
	private CityDao cityDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,EmployerDao employerDao,CityDao cityDao) {
		this.jobAdvertisementDao=jobAdvertisementDao;
		this.employerDao=employerDao;
		this.cityDao=cityDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.findAll(),"İş ilanları Listelendi");
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		if(!jobPositionChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İş Pozisyonu alanı zorunludur");
		}
		else if(!jobDescriptionChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İş Açıklaması alanı zorunludur");
		}
		else if(!jobCityChecker(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"İl bilgisi alanı zorunludur");
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
		else if(!isCityExist(jobAdvertisement)) {
			return new ErrorDataResult<JobAdvertisement>(null,"Şehir bulunamadı");
		}
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.save(jobAdvertisement),"İş ilanı başarılı bir şekilde paylaşıldı");
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
	
	
	private boolean jobPositionChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getJobTitle().getTitle().isBlank()|| jobAdvertisement.getJobTitle().getTitle().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean jobDescriptionChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return false;
		}
		return true;
	}
	private boolean jobCityChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getCity().getCityName().isBlank()|| jobAdvertisement.getCity().getCityName().equals(null)) {
			return false;
		}
		return true;
	}
	private boolean jobMinSalaryController(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMinSalary()<0 || jobAdvertisement.getMinSalary()>=jobAdvertisement.getMaxSalary()) {
			return false;
		}
		return true;
	}
	private boolean jobMaxSalaryController(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary()<0 || jobAdvertisement.getMaxSalary()<=jobAdvertisement.getMinSalary()) {
			return false;
		}
		return true;
	}
	private boolean jobNumberChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getNumberOfPosition()<=0) {
			return false;
		}
		return true;
	}
	
	private boolean jobDateChecker(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getLastDate()==null) {
			return false;
		}
		return true;
	}
	
	private boolean isEmployerExist(JobAdvertisement jobAdvertisement) {
		if(!this.employerDao.existsById(jobAdvertisement.getEmployer().getId())) {
			return false;
		}
		return true;
	}
	
	private boolean isCityExist(JobAdvertisement jobAdvertisement) {
		if(!this.cityDao.existsById(jobAdvertisement.getCity().getId())) {
			return false;
		}
		return true;
	}
	

}
