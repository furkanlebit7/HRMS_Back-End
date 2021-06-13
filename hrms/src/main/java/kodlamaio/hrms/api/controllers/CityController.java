package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CityServie;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.City;

@CrossOrigin
@RestController
@RequestMapping("/api/city")
public class CityController {
	
	private CityServie cityService;
	@Autowired
	public CityController(CityServie cityService) {
		this.cityService=cityService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<City>> getAll(){
		return this.cityService.getAll();
	}
	
	@PostMapping("/add")
	public DataResult<City> add(City city){
		return this.cityService.add(city);
	}
	
}
