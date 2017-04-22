package in.eloksolutions.ala.service;

import java.util.List;

import in.eloksolutions.ala.model.User;

public interface UserService {

	public abstract User getLoginUser(String user, String pass);

	List<User> findAllUsers();

	public abstract void addUser(User user);

}
