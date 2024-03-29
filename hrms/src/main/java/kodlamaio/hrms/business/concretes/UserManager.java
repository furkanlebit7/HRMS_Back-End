package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{
	
	private UserDao userDao;
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao=userDao;
	}
	
	
	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(userDao.findAll(),"User listeleme başarılı");
	}
	
	@Override
	public User add(User user) {
		return userDao.save(user);
	}


	


	@Override
	public DataResult<User> getUserByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.getUserByEmail(email));
	}
	
}
