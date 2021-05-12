package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.entities.concretes.JobTitles;

public interface JobTitleService {
	List<JobTitles> getAll();
}
