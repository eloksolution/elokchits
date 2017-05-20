package in.eloksolutions.ala.service;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.LotMember;

public interface LotMemberService {

	void addMember(LotMember mem);

	

	LotMember lotMembers(int lotid);




}
