package in.eloksolutions.ala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.dao.LotMemberDAO;
import in.eloksolutions.ala.model.LotMember;
@Repository
@Service("lotMemberService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class LotMemberServiceImpl implements LotMemberService {
	private LotMemberDAO lotMemberDAO;
	
	public LotMemberDAO getLotMemberDAO() {
		return lotMemberDAO;
	}
	@Autowired
	public void setLotMemberDAO(LotMemberDAO lotMemberDAO) {
		this.lotMemberDAO = lotMemberDAO;
	}
	public void addMember(LotMember mem) {
		lotMemberDAO.save(mem);
		
	}
	@Override
	public LotMember searchlotMembers(int lotid) {
		
		return lotMemberDAO.searchlotMembers(lotid);
	}
	@Override
	public List<MemberVO> lotsMembers(int lotid) {
	
		return lotMemberDAO.lotsMembers(lotid);
	}
	@Override
	public List<LotMember> findLotsMembers(int lotid) {
		return lotMemberDAO.findLotsMembers(lotid);
	}
	@Override
	public List<MemberVO> allLotMembers(int lotid) {
		return lotMemberDAO.allLotMembers(lotid);
		
	}
	
		


}
