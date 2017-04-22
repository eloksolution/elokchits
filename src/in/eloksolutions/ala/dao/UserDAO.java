package in.eloksolutions.ala.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import in.eloksolutions.ala.model.User;

public interface UserDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract User getLoginUser(String user, String pass);

	List<User> findAllUsers();

	public abstract void add(User user);

	

}
