package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ResumeTechs;

public interface ResumeTechDao extends JpaRepository<ResumeTechs, Integer>{

}
