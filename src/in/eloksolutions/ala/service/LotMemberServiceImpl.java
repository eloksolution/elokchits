package in.eloksolutions.ala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.eloksolutions.ala.dao.LotMemberDAO;
import in.eloksolutions.ala.model.Lot;
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
	public LotMember lotMembers(int lotid) {
		
		return lotMemberDAO.lotMembers(lotid);
	}
	


}
