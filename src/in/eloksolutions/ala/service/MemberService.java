package in.eloksolutions.ala.service;

import java.util.List;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;

public interface MemberService {

	public abstract void addMember(Member mem);

	public abstract Member searchById(Integer memberId);
	
	public abstract List<Member> searchAllMembers();

	public abstract List<Member> searchByName(String name);

	public abstract List<Lot> searchMemberLots(int memid);

	public abstract List<Member> getMembersForPartner(int partId);

	public abstract void deleteMember(int memid);

	

}
