package in.eloksolutions.ala.service;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.Lot;

import java.util.Date;
import java.util.List;

public interface LotService {

	public abstract void addLot(Lot lot);

	public abstract Lot searchById(int lotId);
	
	public abstract List<Lot> findAllLots();
	
	public abstract List<Lot> findAllActiveLots();

	public abstract List<MemberVO> searchlotMembers(int lotid);

	public void updateDate(int lotId, Date startdate);

	public abstract void deleteMember(int memrowid);

	public abstract void deletelot(int lotid);

	public abstract void statusUpdate(int lotid, int Status);

	
}
