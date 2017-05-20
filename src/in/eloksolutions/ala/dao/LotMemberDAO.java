package in.eloksolutions.ala.dao;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.LotMember;

public interface LotMemberDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(LotMember mem);

	public abstract LotMember lotMembers(int lotid);

	

}
