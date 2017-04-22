package in.eloksolutions.ala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.eloksolutions.ala.dao.UserDAO;
import in.eloksolutions.ala.model.User;

@Repository
@Service("userService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	

	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getLoginUser(String user, String pass) {
		return userDAO.getLoginUser(user,pass);
	}
	
	@Override
	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}

	public void addUser(User user) {
		userDAO.add(user);
		
	}
}
