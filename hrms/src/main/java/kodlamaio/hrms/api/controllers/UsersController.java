package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.User;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;

	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	@ResponseBody
	public DataResult<List<User>>  getAll(){
		return this.userService.getAll();
	}
	@GetMapping("/getUserByEmail")
	@ResponseBody
	public DataResult<User>  getUserByEmail(@RequestParam String email){
		return this.userService.getUserByEmail(email);
	}
	
	
}

