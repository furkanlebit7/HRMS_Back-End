package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityServie;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.concretes.City;

@Service
public class CityManager implements CityServie{

	private CityDao cityDao;
	
		@Autowired
		public CityManager(CityDao cityDao) {
			this.cityDao = cityDao;
	}
		
	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(),"Başarılı Şekilde Şehirler Listelendi");
	}

	@Override
	public DataResult<City> add(City city) {
		if(!cityNameChecker(city)) {
			return new ErrorDataResult(null,"Şehir ismi boş olamaz");
		}
		return new SuccessDataResult<City>(this.cityDao.save(city),"Şehir eklendi");
	}
	
	private boolean cityNameChecker(City city) {
		if(city.getCityName().isEmpty() || city.getCityName().isBlank()) {
			return false;
		}
		return true;
	}

}
