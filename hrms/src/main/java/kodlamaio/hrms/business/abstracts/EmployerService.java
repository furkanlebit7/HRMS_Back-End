package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerService {
	DataResult<Employer> add(Employer employer);
	DataResult<List<Employer>>getAll();
	Result uploadImage(MultipartFile file, int employerId);
	Result uploadBanner(MultipartFile file, int employerId);
	Result verifyChecker(Integer employerId);
	DataResult<List<Employer>>getEmployerThree();
	Result updateEmployer(Employer employer);
	Result updateNameEmail(int employerId, String companyName,String companyEmail);
}
