package in.eloksolutions.ala.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;

public interface MemberDAO {

	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Member member);

	public abstract Member findById(Integer memberId);
	
	public abstract List<Member> findAllMembers();

	public abstract List<Member> findByName(String name);

	public abstract List<Lot> findMemberLots(int memid);

	public abstract List<Member> getMembersForPartner(int partId);

	public abstract void deleteMember(int memid);


}
