package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ActivationCodes;

public interface ActivationCodeService {
	void generateCode(ActivationCodes code, Integer id);
	Result verify(String verificationCode, Integer id);
}
