package kodlamaio.hrms.business.abstracts;


import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployerActivationByEmployee;

public interface EmployerActivationByEmployeeService {
	Result verifyEmployer(EmployerActivationByEmployee eabe);
	boolean existsByEmployerId(Integer id);
}
