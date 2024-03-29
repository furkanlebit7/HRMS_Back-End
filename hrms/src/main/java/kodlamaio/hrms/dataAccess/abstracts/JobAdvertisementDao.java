package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	
	List<JobAdvertisement> getByEmployerId(int employerId);

	List<JobAdvertisement> findAllByIsActive(boolean isActive);

	List<JobAdvertisement> findAllByIsActiveOrderByCreatedDateDesc(boolean isActive);
	
	List<JobAdvertisement> findAllByIsActiveAndEmployerId(boolean isActive,int employerId);
	
	@Query("SELECT jA FROM JobAdvertisement jA ORDER BY jA.id DESC")
	List<JobAdvertisement> getJobAdvertisementFour(Pageable pageable);
}
