package in.eloksolutions.ala.service;

import in.eloksolutions.ala.beans.LotDetailsVO;
import in.eloksolutions.ala.beans.MemberBillVO;
import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.dao.AuctionDAO;
import in.eloksolutions.ala.dao.LotDAO;
import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.LotMember;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Service("auctionService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AuctionServiceImpl implements AuctionService{
	@Autowired
	private AuctionDAO auctionDAO;
	
	@Autowired
	private LotDAO lotDAO;
	
	public LotDAO getLotDAO() {
		return lotDAO;
	}

	@Autowired
	public void setLotDAO(LotDAO lotDAO) {
		this.lotDAO = lotDAO;
	}
	
	public AuctionDAO getAuctionDAO() {
		return auctionDAO;
	}

	@Autowired
	public void setAuctionDAO(AuctionDAO membersDAO) {
		this.auctionDAO = auctionDAO;
	}

	public void addLotMember(LotMember member){
		auctionDAO.save(member);
	}
	@Override
	public void update(LotMember member){
		auctionDAO.update(member);
		int count = lotDAO.getLiftMemberCount(member.getLotId());
		if(count == 0){
			Lot lot=lotDAO.findById(member.getLotId());
			lot.setStatus(0);
			lotDAO.add(lot);
		}
	}
		public LotMember searchById(Integer memberId){
		return auctionDAO.findById(memberId);
	}


	public List<LotMember> searchAllLotMembers() {
		return auctionDAO.findAllLotMembers();
	}
	
	@Override
	public List<LotMember> searchAllActiveLotMembers() {
		return auctionDAO.findAllActiveLotMembers();
	}

	@Override
	public List<LotMember> findByYearnMonth(Integer year, Integer month) {
		return auctionDAO.findByYearnMonth(year, month);
	}

	@Override
	public List<LotDetailsVO> getLotDetails(Integer year, Integer month) {
		return auctionDAO.getLotDetails(year, month);
	}

	@Override
	public void saveLotMember(LotMember member) {
		auctionDAO.save(member);
		
	}
	
	@Override
	public List<MemberVO> searchMembers(String q ,String lotid) {
		return auctionDAO.searchMembers(q  ,lotid);
	}

	@Override
	public Map<String, List<MemberBillVO>> billGeneration(Integer mon, Integer year, Integer refId,String typ) throws Exception {
		 Calendar cal = Calendar.getInstance();
		 year = cal.get(Calendar.YEAR);
		 mon = cal.get(Calendar.MONTH);
		 Date currDate=cal.getTime();
		 List<MemberBillVO> mbvList=new ArrayList<MemberBillVO>(100);
		 List<LotMember> lmems=auctionDAO.findByYearnMonth(year, mon+1);
		// System.out.println("get auction members "+lmems);
		 if(lmems==null || lmems.size()==0){
			 throw new Exception("Please Update the action amounts for the lots in Action Home Screen");
		 }
		 List<Lot> lots=lotDAO.findAllActiveLots();
		 //System.out.println("Lots "+lots);
		 if(lots==null || lots.size()==0){
			 throw new Exception("No active lots");
		 }
		 Map<Integer, LotMember> lotAuctionMap=new HashMap<Integer, LotMember>();
		 Map<Integer, MemberBillVO> lotsums=new HashMap<>();
		 Map<Integer, Lot> lotsMap=new HashMap<>();
		 
		 for(Lot l:lots){
			 MemberBillVO mbv=new MemberBillVO();
			 lotsMap.put(l.getLotId(),l);
			 mbv.lotId=l.getLotId();
			 mbv.lotType=l.getType();
			 mbv.lotCode=l.getCode();
			 mbv.totalMon=l.getNoOfMonths();
			 mbv.sumAmount=l.getSumAmount();
			 mbv.startDate=l.getStartDate();
			 mbv.runMon=getNumberOfMonths(l.getStartDate(),currDate);
			 mbv.balMon=l.getNoOfMonths()-mbv.runMon;
			 if(l.getType()==2){
			//	 System.out.println("TPE 2 "+l);
				 mbv.commission=0;
				 mbv.beforeLiftAmount=l.getBeforeLiftAmount();
				 mbv.afterLiftAmount=l.getAfterLiftAmount();
				 mbv.premium=l.getBeforeLiftAmount();
			 }else{
			 	mbv.commission=(l.getSumAmount()*l.getCommisionper())/100;
			 	mbv.premium=l.getSumAmount()/l.getNoOfMonths();
			 }
			 lotsums.put(l.getLotId(), mbv);
		 }
		 
		 for(LotMember lm:lmems){
			 lotAuctionMap.put(lm.getLotId(),lm);
			 MemberBillVO mbv=lotsums.get(lm.getLotId());
			 if(mbv.lotType==2){
				 mbv.auctionAmt=0;
				 mbv.commission=0;
				 mbv.payAmt=mbv.premium;
			 }else{
			 	mbv.auctionAmt=lm.getAuctionAmt();
			 	mbv.commission=(mbv.auctionAmt-mbv.commission)/mbv.totalMon;
				 mbv.payAmt=mbv.premium-mbv.commission;
			 }
			 
		 }
		 List<LotMember> allMembers=auctionDAO.findAllActiveLotMembers(refId);
		 //System.out.println("Ref lot members "+allMembers);
		 for(LotMember lm:allMembers){
			 MemberBillVO mbv=new MemberBillVO();
			// System.out.println("lot id is "+lm.getLotId());
			 MemberBillVO commmbv=lotsums.get(lm.getLotId());
			 mbv.lotId=lm.getLotId();
			 mbv.lotCode=commmbv.lotCode;
			 mbv.sumAmount=commmbv.sumAmount;
			 mbv.auctionAmt=commmbv.auctionAmt;
			 mbv.totalMon=commmbv.totalMon;
			 mbv.runMon=commmbv.runMon;
			 mbv.balMon=commmbv.balMon;
			 mbv.premium=commmbv.premium;
			 mbv.payAmt=commmbv.payAmt;	
			 //System.out.println("commmbv "+commmbv);
			 //System.out.println("lot members "+lm);
			 if(commmbv.lotType==2){
				 Date paidDate=lm.getPaidDate();
				 Date date = new Date();    
				 SimpleDateFormat sdf=new SimpleDateFormat("MM-yyyy");
				 String sTodayMonthYear=sdf.format(date);
					System.out.println("Today month year "+sTodayMonthYear);
				String sPaidMonthYear=null;	
				 if(paidDate!=null){
				 	sPaidMonthYear=sdf.format(paidDate);
				 	System.out.println("Paid month year "+sPaidMonthYear);
				 }
				 if(paidDate!=null && !sTodayMonthYear.equals(sPaidMonthYear)){
					  System.out.println("payed year and month "+sPaidMonthYear);
					 mbv.payAmt=commmbv.afterLiftAmount;
					 mbv.premium=commmbv.afterLiftAmount;
				 }else{
					 mbv.payAmt=commmbv.beforeLiftAmount;	 
				 }
			 }
			 mbv.commission=commmbv.commission;
			 
			 if(lm.getMemName()!=null && lm.getMemName().indexOf("-")>-1)
			 	mbv.name=lm.getMemName().substring(0,lm.getMemName().indexOf("-"));
			 else
				 mbv.name=lm.getMemName();
			 mbv.memId=lm.getMemId();
			 System.out.println("after setting data mbv "+mbv);
			 mbvList.add(mbv);		 
		 }
		 LinkedHashMap<String, List<MemberBillVO>> memBillsMap=new LinkedHashMap<String, List<MemberBillVO>>();
		
		 if("2".equals(typ)){
			 for(MemberBillVO m1:mbvList){
				 List<MemberBillVO> mbList=memBillsMap.get(m1.lotId+"");
				 if(mbList==null){
					 mbList=new ArrayList<MemberBillVO>();
				 }
				 mbList.add(m1);
				 memBillsMap.put(m1.lotId+"", mbList);
			 }
		 }else{
			 for(MemberBillVO m1:mbvList){
				// System.out.println("Member id is "+m1.memId);
				 List<MemberBillVO> mbList=memBillsMap.get(m1.memId+"");
				 if(mbList==null){
					 mbList=new ArrayList<MemberBillVO>();
				 }
				 if(mbList.size()<7){
					 mbList.add(m1);
					 memBillsMap.put(m1.memId+"", mbList);
				 }
				 else{
					 List<MemberBillVO> mbList1=memBillsMap.get(m1.memId+"-1");
					 if(mbList1==null){
						 mbList1=new ArrayList<MemberBillVO>();
					 }
					 System.out.println("Size is "+mbList1.size());
					 if(mbList1.size()<7){
						 mbList1.add(m1);
						 memBillsMap.put(m1.memId+"-1", mbList1);
					 }else{
						 List<MemberBillVO> mbList2=memBillsMap.get(m1.memId+"-2");
						 if(mbList2==null){
							 mbList2=new ArrayList<MemberBillVO>();
						 }
						 mbList2.add(m1);
						 memBillsMap.put(m1.memId+"-2", mbList2);
					 }
					
				 }
			 }
		 }
		 System.out.println("Bills "+memBillsMap);
		return memBillsMap;
	}
	private static int getNumberOfMonths(Date fromDate, Date toDate) {
		System.out.println("fromDate "+fromDate+" toDate "+toDate);
	    int monthCount = 0;
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(fromDate);
	    int c1date = cal.get(Calendar.DATE);
	    int c1month = cal.get(Calendar.MONTH);
	    int c1year = cal.get(Calendar.YEAR);
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(toDate);
	    int c2date = cal1.get(Calendar.DATE);
	    int c2month = cal1.get(Calendar.MONTH);
	    int c2year = cal1.get(Calendar.YEAR);
	    GregorianCalendar grCal = new GregorianCalendar();
	    boolean isLeapYear1 = grCal.isLeapYear(c1year);
	    boolean isLeapYear2 = grCal.isLeapYear(c2year);
	    monthCount = ((c2year - c1year) * 12) + (c2month - c1month);
	    if(isLeapYear2 && c2month == 1 && c2date == 29){
	        monthCount = monthCount+ ((c1date == 28)?0:1);
	    }else if(isLeapYear1 && c1month == 1 && c1date == 29){
	        monthCount = monthCount+ ((c2date == 28)?0:1);
	    }else{
	        monthCount = monthCount+ ((c2date >= c1date)?0:1);
	    }
	    return monthCount;

	}
	
	/*@Override
	public Map<Integer, List<MemberBillVO>> newBillGeneration(Integer mon, Integer year, Integer refId) {
		 List<LotMember> allMembers=auctionDAO.findAllActiveLotMembers(refId);
		 for(LotMember lm:allMembers){
			 MemberBillVO mbv=new MemberBillVO();
			 MemberBillVO commmbv=lotsums.get(lm.getLotId());
			 mbv.lotId=lm.getLotId();
			 mbv.sumAmount=commmbv.sumAmount;
			 mbv.auctionAmt=commmbv.auctionAmt;
			 mbv.totalMon=commmbv.totalMon;
			 mbv.runMon=commmbv.runMon;
			 mbv.balMon=commmbv.balMon;
			 mbv.premium=commmbv.premium;
			 mbv.commission=commmbv.commission;
			 mbv.payAmt=commmbv.payAmt;
			 mbv.name=lm.getMemName();
			 mbv.memId=lm.getMemId();
			 mbvList.add(mbv);		 
		 }
	}*/
	
}
