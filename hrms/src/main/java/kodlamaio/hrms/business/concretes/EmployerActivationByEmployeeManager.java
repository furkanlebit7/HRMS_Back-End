package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.business.abstracts.EmployerActivationByEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerActivationByEmployeeDao;
import kodlamaio.hrms.entities.concretes.EmployerActivationByEmployee;

@Service
public class EmployerActivationByEmployeeManager implements EmployerActivationByEmployeeService{
	
	private EmployerActivationByEmployeeDao employerActivationByEmployeeDao;

	@Autowired
	public EmployerActivationByEmployeeManager(EmployerActivationByEmployeeDao employerActivationByEmployeeDao) {
		super();
		this.employerActivationByEmployeeDao = employerActivationByEmployeeDao;
	}

	@Override
	public Result verifyEmployer(EmployerActivationByEmployee eabe) {
		this.employerActivationByEmployeeDao.save(eabe);
		return new SuccessResult("İş veren başarılı bir şekilde onaylandı");
	}

	@Override
	public boolean existsByEmployerId(Integer id) {
		return this.employerActivationByEmployeeDao.existsByEmployerId(id);
	}

	

	

}
