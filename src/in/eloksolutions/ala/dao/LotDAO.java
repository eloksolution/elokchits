package in.eloksolutions.ala.dao;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.Lot;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public interface LotDAO {
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);
	
	public abstract void add(Lot lot);

	public abstract Lot findById(int lotId);

	public abstract List<Lot> findAllLots();

	public abstract List<Lot> findAllActiveLots();

	public abstract List<MemberVO> findlotMembers(int lotid);

	public abstract int getLiftMemberCount(int lotid);
	
	public void updateDate(Date startDate,int lotId);

	public abstract void deleteMember(int memrowid);
}
