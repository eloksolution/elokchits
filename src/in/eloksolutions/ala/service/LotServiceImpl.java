package in.eloksolutions.ala.service;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.dao.LotDAO;
import in.eloksolutions.ala.model.Lot;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Service("lotService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class LotServiceImpl implements LotService{
	private LotDAO lotDAO;

	public LotDAO getLotDAO() {
		return lotDAO;
	}
	@Autowired
	public void setLotDAO(LotDAO lotDAO) {
		this.lotDAO = lotDAO;
	}
	public void addLot(Lot lot) {
		lotDAO.add(lot);
		
	}
	public Lot searchById(int lotId) {
		return lotDAO.findById(lotId);

	}
	public List<Lot> findAllLots() {
		return lotDAO.findAllLots();
	}

	@Override
	public List<Lot> findAllActiveLots() {
		return lotDAO.findAllActiveLots();
	}
	@Override
	public List<MemberVO> searchlotMembers(int lotid) {
		return lotDAO.findlotMembers(lotid);
	}
	@Override
	public void updateDate(int lotId, Date startDate){
			System.out.println("lotf fromj lotId"+lotId);
			lotDAO.updateDate(startDate, lotId);
		
	}
	@Override
	public void deleteMember(int memrowid) {
		lotDAO.deleteMember(memrowid);
	}
	@Override
	public void deletelot(int lotid) {
		lotDAO.deletelot(lotid);
	}
	@Override
	public void statusUpdate(int lotId, int status) {
		lotDAO.statusUpdate(status,lotId);
		
	}
	
}
