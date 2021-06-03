package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ResumeLanguage;

public interface ResumeLanguageDao extends JpaRepository<ResumeLanguage, Integer>{

}
