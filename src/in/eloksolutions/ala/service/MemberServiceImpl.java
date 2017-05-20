package in.eloksolutions.ala.service;

import in.eloksolutions.ala.dao.MemberDAO;
import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Service("memberService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO;
	
	
public MemberDAO getMembersDAO() {
		return memberDAO;
	}

    @Autowired
	public void setMemberDAO(MemberDAO membersDAO) {
		this.memberDAO = membersDAO;
	}

	public void addMember(Member member){
		memberDAO.save(member);
	}
	public Member searchById(Integer memberId){
		return memberDAO.findById(memberId);
	}
	public List<Member> searchAllMembers() {
		return memberDAO.findAllMembers();
	}
	public List<Member> searchByName(String name) {
		return memberDAO.findByName(name);
	}
	public List<Lot> searchMemberLots(int memid) {
		return memberDAO.findMemberLots(memid);
	}

	@Override
	public List<Member> getMembersForPartner(int partId) {
		return memberDAO.getMembersForPartner(partId);
	}

	@Override
	public void deleteMember(int memid) {
		 memberDAO.deleteMember(memid);
		
	}

}
