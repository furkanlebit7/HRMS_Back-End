package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ResumeTechs;

public interface ResumeTechDao extends JpaRepository<ResumeTechs, Integer>{
	List<ResumeTechs>findAllByResumeId(int id);
}
