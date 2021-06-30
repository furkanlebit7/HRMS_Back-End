package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	List<Employer> findAllByEmail(String email);
	Employer findOneById(Integer id);
	
	@Query("SELECT e FROM Employer e ORDER BY e.id")
	List<Employer> getJobAdvertisementFour(Pageable pageable);
}
