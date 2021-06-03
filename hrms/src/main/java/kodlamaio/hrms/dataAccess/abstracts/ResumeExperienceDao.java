package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ResumeExperience;

public interface ResumeExperienceDao extends JpaRepository<ResumeExperience, Integer>{

}
