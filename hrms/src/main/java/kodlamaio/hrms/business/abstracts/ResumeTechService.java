package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ResumeTechs;
import kodlamaio.hrms.entities.dtos.ResumeTechsDto;

public interface ResumeTechService {
	DataResult<List<ResumeTechs>> getAll();
	Result removeById(int id);
	Result add(ResumeTechsDto tech);
	DataResult<List<ResumeTechs>> findAllByResumeId(int id);
}
