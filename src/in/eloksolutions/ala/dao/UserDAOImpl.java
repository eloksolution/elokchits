package in.eloksolutions.ala.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;
import in.eloksolutions.ala.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#getJdbcTemplate()
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 */
	protected void initDao() {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#init(org.hibernate.SessionFactory)
	 */
	@Autowired
	public void init(SessionFactory sessionFactory) {
	    setSessionFactory(sessionFactory);
	    this.sessionFactory=sessionFactory;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}

	@Override
	public User getLoginUser(String user, String pass) {
		Query query = currentSession().createQuery("from User where userName=:user and password=:pass and status=1");
		query.setString("user", user);
		query.setString("pass", pass);
		User qresult=(User) query.uniqueResult();
		return qresult;
	}

	@Override
	public List<User> findAllUsers() {
		String sql="FROM User";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		return query.list();
	}
	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
		sessionFactory.getCurrentSession().flush();
		System.out.println("after flush "+user);
	}
}
