package in.eloksolutions.ala.service;

import in.eloksolutions.ala.beans.LotDetailsVO;
import in.eloksolutions.ala.beans.MemberBillVO;
import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.LotMember;

import java.util.List;
import java.util.Map;

public interface AuctionService {
	void saveLotMember(LotMember member);
	List<LotMember> searchAllLotMembers();
	void addLotMember(LotMember member);
	LotMember searchById(Integer memberId);
	List<LotMember> findByYearnMonth(Integer year, Integer month);
	List<LotDetailsVO> getLotDetails(Integer year, Integer month);
	List<MemberVO> searchMembers(String q, String lotid);
	void update(LotMember member);
	Map<String, List<MemberBillVO>> billGeneration(Integer mon,Integer year, Integer refId, String typ) throws Exception;
	List<LotMember> searchAllActiveLotMembers();
	
}
