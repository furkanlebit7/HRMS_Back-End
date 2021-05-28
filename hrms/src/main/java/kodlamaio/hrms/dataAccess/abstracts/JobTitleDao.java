package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobTitle;
//Primary key alanı integer olduğu için INTEGER yazdık
public interface JobTitleDao extends JpaRepository<JobTitle, Integer>{
	
	//FindAllById yi findAllByTitle ye çevirdik
	List<JobTitle> findAllByTitle(String title);
	
}
