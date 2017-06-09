package in.eloksolutions.ala.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.LotMember;

public interface LotMemberDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(LotMember mem);
	
	public abstract LotMember searchlotMembers(int lotid);

	public abstract List<MemberVO> lotsMembers(int lotid);

	public abstract List<LotMember> findLotsMembers(int lotid);

	public abstract List<MemberVO> allLotMembers(int lotid);

	

}
