package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.EmployerActivationByEmployeeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.cloudinary.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.ActivationCodes;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.EmployerActivationByEmployee;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmployerManager implements EmployerService{
	
	private EmployerDao employerDao;
	private ActivationCodeService activationCodeService;
	private UserService userService;
	private EmployerActivationByEmployeeService employerActivationByEmployeeService;
	private CloudinaryService cloudinaryService;
	
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, ActivationCodeService activationCodeService,
			UserService userService, EmployerActivationByEmployeeService employerActivationByEmployeeService,
			CloudinaryService cloudinaryService) {
		super();
		this.employerDao = employerDao;
		this.activationCodeService = activationCodeService;
		this.userService = userService;
		this.employerActivationByEmployeeService = employerActivationByEmployeeService;
		this.cloudinaryService = cloudinaryService;
	}

	@Override
	public DataResult<Employer> add(Employer employer) {
		// TODO Auto-generated method stub
		
		if(!companyNameChecker(employer)) {
			return new ErrorDataResult<Employer>(null,"Şirket Adı Doldurulmak Zorundadır");
		}
		else if(!webSiteChecker(employer)) {
			return new ErrorDataResult<Employer>(null,"WebSite Adresi Doldurulmak Zorundadır");
		}
		else if(!isRealEmployer(employer)) {
			return new ErrorDataResult<Employer>(null,"Geçersiz Email Adresi");
		}
		else if(!passwordNullChecker(employer)) {
			return new ErrorDataResult<Employer>(null,"Şifre Bilgisi Doldurulmak Zorundadır");
		}
		else if(!isRealPhoneNumber(employer)) {
			return new ErrorDataResult<Employer>(null,"Telefon Numarası Geçersiz");
	    }
		else if(!isEmailAlreadyRegistered(employer)) {
			return new ErrorDataResult<Employer>(null,"Email Zaten Kayıtlı");
		}
		User savedUser = this.userService.add(employer);
		this.activationCodeService.generateCode(new ActivationCodes(),savedUser.getId());
		employer.setActivated(false);
		return new SuccessDataResult<Employer>(this.employerDao.save(employer),"İş Veren Hesabı Eklendi, Doğrulama Kodu Gönderildi ID:"+employer.getId());
	}
	
	private boolean companyNameChecker(Employer employer) {
		if(employer.getCompanyName().isBlank() || employer.getCompanyName() == null) {
			return false;
		}
		return true;
	}
	
	private boolean webSiteChecker(Employer employer) {
		if(employer.getWebAdress().isBlank() || employer.getWebAdress() == null) {
			return false;
		}
		return true;
	}
	
	private boolean isRealEmployer(Employer employer) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(!matcher.matches()) {
	    	 return false;
	     }
	     else if(!employer.getEmail().contains(employer.getWebAdress().substring(4,employer.getWebAdress().length()-4))) {
	    	return false; 
	     }
	     return true;
	     
	}
	
	private boolean isEmailAlreadyRegistered(Employer employer) {
		if(employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0) {
			return false;
		}
		return true;
	}
	
	private boolean passwordNullChecker(Employer employer) {
		if(employer.getPassword().isBlank() || employer.getPassword() == null) {
			return false;
		}
		return true;
	}
	
	private boolean isRealPhoneNumber(Employer employer) {
		String patterns 
	      = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
	      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
	      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		/*
		 * ÖRNEK TELEFON NUMARALARI
		 * String[] validPhoneNumbers 
      = {"2055550125","202 555 0125", "(202) 555-0125", "+111 (202) 555-0125", 
      "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"};
		 */
		Pattern pattern = Pattern.compile(patterns);
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if(!matcher.matches()) {
			return false;
		}
		return true;
		
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Başarılı Şekilde Employer Listelendi");
	}
	
	@Override
	public Result uploadImage(MultipartFile file, int employerId) {

		Map<String, String> uploader = (Map<String, String>) 
				cloudinaryService.save(file).getData(); 
		String imageUrl= uploader.get("url");
		Employer employer = employerDao.getOne(employerId);
		employer.setCompanyLogo(imageUrl);
		employerDao.save(employer);
		return new SuccessResult("Kayıt Başarılı");
	}

	@Override
	public Result verifyChecker(Integer employerId) {
		if(employerActivationByEmployeeService.existsByEmployerId(employerId) &&
				activationCodeService.findOneById(employerId).getData().isConfirmed()) {
			Employer e = employerDao.getOne(employerId);
			e.setActivated(true);
			employerDao.save(e);
			return new SuccessResult("her iki taraflı da onaylı");
		}
		
		return new ErrorResult("iki taraflı onay almamış");
	}

	@Override
	public Result uploadBanner(MultipartFile file, int employerId) {

		Map<String, String> uploader = (Map<String, String>) 
				cloudinaryService.save(file).getData(); 
		String imageUrl= uploader.get("url");
		Employer employer = employerDao.getOne(employerId);
		employer.setCompanyBanner(imageUrl);
		employerDao.save(employer);
		return new SuccessResult("Kayıt Başarılı");
	}

	@Override
	public DataResult<List<Employer>> getEmployerThree() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getJobAdvertisementFour(PageRequest.of(1, 3)),"Başarılı");
	}
	
	

}
