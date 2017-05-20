package in.eloksolutions.ala.dao;

import in.eloksolutions.ala.beans.LotDetailsVO;
import in.eloksolutions.ala.beans.MemberBillVO;
import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.LotMember;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public interface AuctionDAO {

	JdbcTemplate getJdbcTemplate();

	void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	void init(SessionFactory sessionFactory);

	List<LotMember> findAllLotMembers();

	void save(LotMember member);

	LotMember findById(Integer memberId);
	List<LotMember> findByYearnMonth(Integer year,Integer month);
	List<LotDetailsVO> getLotDetails(Integer year, Integer month);

	List<MemberVO> searchMembers(String q, String lotid);

	void update(LotMember member);
	List<MemberBillVO> billGeneration(Integer mon,Integer year);

	LotMember findByMemIdnLotid(Integer memId, Integer lotId);
	List<LotMember> findAllActiveLotMembers();
	List<LotMember> findAllActiveLotMembers(Integer refId);
}
