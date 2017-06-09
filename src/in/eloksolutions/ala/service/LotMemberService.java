package in.eloksolutions.ala.service;

import java.util.List;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.LotMember;

public interface LotMemberService {

	void addMember(LotMember mem);

	LotMember searchlotMembers(int lotid);

	List<MemberVO> lotsMembers(int lotid);

	List<LotMember> findLotsMembers(int lotid);

	List<MemberVO> allLotMembers(int lotid);

	

	

	




}
