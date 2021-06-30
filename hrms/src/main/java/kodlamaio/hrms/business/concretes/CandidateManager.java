package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.IdentityValidation;
import kodlamaio.hrms.core.utilities.cloudinary.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.ActivationCodes;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private UserService userService;
	private ActivationCodeService activationCodeService;
	private CloudinaryService cloudinaryService;
	
	public CandidateManager(CandidateDao candidateDao,UserService userService,ActivationCodeService activationCodeService,CloudinaryService cloudinaryService) {
		this.candidateDao=candidateDao;
		this.userService=userService;
		this.activationCodeService=activationCodeService;
		this.cloudinaryService = cloudinaryService;
	}
	
	@Override
	public DataResult<Candidate> add(Candidate candidate) {
		
		if(!firstNameChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null,"Ad Bilgisi Doldurulmak Zorundadır");
		}
		else if(!lastNameChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null,"SoyAdı Bilgisi Doldurulmak Zorundadır");
		}
		
		else if(!IdentityValidation.isRealPerson(candidate.getIdentityNumber())) {
			return new ErrorDataResult<Candidate>(null,"Kimlik Doğrulanamadı");
		}
		else if(candidate.getIdentityNumber().isBlank()) {
			return new ErrorDataResult<Candidate>(null,"Tc Kimlik Bilgisi Boş Bırakılamaz");
		}
		
		else if(!birthDateChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null,"Doğum Tarihi Bilgisi Doldurulmak Zorundadır");
		}
		
		else if(!emailChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null,"Email Bilgisi Doldurulmak Zorundadır");
		}
		else if(!isRealEmail(candidate)) {
			return new ErrorDataResult<Candidate>(null,"Email Adresiniz Yanlış");
		}
		
		else if(!passwordChecker(candidate)) {
			return new ErrorDataResult<Candidate>(null,"Şifre Bilgisi Doldurulmak Zorundadır");
		}
		
		else if(candidateDao.findAllByEmail(candidate.getEmail()).stream().count() != 0 ) {
			return new ErrorDataResult<Candidate>(null,"Email Zaten Kayıtlı");
		}
		else if(candidateDao.findAllByIdentityNumber(candidate.getIdentityNumber()).stream().count() != 0 ) {
			return new ErrorDataResult<Candidate>(null,"TC Numarası Zaten Kayıtlı");
		}
		User savedUser = this.userService.add(candidate);
		this.activationCodeService.generateCode(new ActivationCodes(), savedUser.getId());
		return new SuccessDataResult<Candidate>(this.candidateDao.save(candidate),"İş Arayan Hesaba Eklendi , Doğrulama Kodu Gönderildi"+savedUser.getId());
	}

	
	
	
	
	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(),"Candidate listeleme başarılı");
	}
	
	@Override
	public Result uploadImage(MultipartFile file, int candidateId) {

		Map<String, String> uploader = (Map<String, String>) 
				cloudinaryService.save(file).getData(); 
		String imageUrl= uploader.get("url");
		Candidate candidate = candidateDao.getOne(candidateId);
		candidate.setPhoto(imageUrl);
		candidateDao.save(candidate);
		return new SuccessResult("Kayıt Başarılı");
	}

	@Override
	public Result updateCandidate(Candidate candidate) {
		Candidate ref = new Candidate();
		ref=candidate;
		candidateDao.save(ref);
		return new SuccessResult("Update başarılı");
	}
	
	
	
/*--------------------------------------------------------------------------------------------------------------------------------*/
	
	
	private boolean firstNameChecker(Candidate candidate) {
		if(candidate.getFirstName().isBlank() || candidate.getFirstName().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean lastNameChecker(Candidate candidate) {
		if(candidate.getLastName().isBlank() || candidate.getLastName().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean birthDateChecker(Candidate candidate) {
		if(candidate.getBirthDate().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean emailChecker(Candidate candidate) {
		if(candidate.getEmail().isBlank()||candidate.getEmail().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean passwordChecker(Candidate candidate) {
		if(candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean isRealEmail(Candidate candidate) {
		String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(candidate.getEmail());
	     if(!matcher.matches()) {
	    	 return false;
	     }
	     return true;
	}

	
	
	
	
	
	
	

}
