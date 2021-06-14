package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.EmployerActivationByEmployeeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.CodeGenerator;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.ActivationCodeDao;
import kodlamaio.hrms.entities.concretes.ActivationCodes;

@Service
public class ActivationCodeManager implements ActivationCodeService{
	
		private ActivationCodeDao activationCodeDao;
	
	 @Autowired
		public ActivationCodeManager(ActivationCodeDao activationCodeDao) {
			super();
			this.activationCodeDao = activationCodeDao;
		}

	    @Override
		public void generateCode(ActivationCodes code,Integer id) {
			// TODO Auto-generated method stub
	    			ActivationCodes code_ = code;
					code.setActivationCode(null);
					code.setConfirmed(false);
					if(code.isConfirmed() == false) {
						CodeGenerator generator = new CodeGenerator();
						code.setActivationCode(generator.create());
						code.setUserId(id);
						
						activationCodeDao.save(code);
						
					}
					return ;
		}
		
		@Override
		public Result verify(String verificationCode,Integer id) {
			// TODO Auto-generated method stub
			ActivationCodes ref = activationCodeDao.findByUserId(id).stream().findFirst().get();
			if(ref.getActivationCode().equals(verificationCode) && ref.isConfirmed() != true) {
				ref.setConfirmed(true);
				LocalDate e = LocalDate.now();
				ref.setConfirmedDate(e);				
				return  new SuccessDataResult<ActivationCodes>(this.activationCodeDao.save(ref),"Başarılı");
			}
			else if(ref.isConfirmed() == true) {
				return  new ErrorDataResult<ActivationCodes>(null,"Zaten Doğrulanmış Hesap");
			}
			return  new ErrorDataResult<ActivationCodes>(null,"Doğrulama Kodu Geçersiz");
			
		}
			
			
		@Override
		public DataResult<ActivationCodes> findOneById(Integer id) {
			return new SuccessDataResult<ActivationCodes>(this.activationCodeDao.findByUserId(id).stream().findFirst().get(),"bulundu");
		}
		
	}