package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobTitles;

public interface JobTitleDao extends JpaRepository<JobTitles, Integer>{

}
