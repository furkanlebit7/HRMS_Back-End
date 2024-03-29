package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ResumeSchools;

public interface ResumeSchoolDao extends JpaRepository<ResumeSchools, Integer>{
	List<ResumeSchools> findByResumeId(int id);
	ResumeSchools findById(int id);
}
