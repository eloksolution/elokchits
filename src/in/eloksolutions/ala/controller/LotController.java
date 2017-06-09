package in.eloksolutions.ala.controller;

import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;
import in.eloksolutions.ala.service.LotService;
import in.eloksolutions.ala.util.Conversions;
import in.eloksolutions.ala.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/lot")
public class LotController {
	@Autowired
	LotService lotService;
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	//-------------------add Lot--------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addLot(HttpServletRequest request){
		System.out.println("Request is coming");
		String  code=request.getParameter("lotcode");
		String  amount=request.getParameter("sumamount");
		String  members=request.getParameter("members");
		String  description=request.getParameter("description");
		String commission=request.getParameter("commission");
		String sStartDate=request.getParameter("startdate");
		System.out.println("sStartDate "+sStartDate);
		String beforeAmount=request.getParameter("beforeamount");
		String afterAmount=request.getParameter("afteramount");
		String lotType=request.getParameter("lottype");
		String lotId=request.getParameter("lotId");
		System.out.println("lot id is when edited "+lotId);
		Date startDate=null;
		try {
			startDate=sdf.parse(sStartDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Lot lot=new Lot();
		if(!Utils.isNull(lotId)){
			int ilotid=Integer.parseInt(lotId);
				lot.setLotId(ilotid);
				System.out.println("lot id is when edited  ilotid"+ilotid);	
		}
		lot.setCode(code);
		lot.setSumAmount(Utils.setIntValue(amount));
		lot.setNoOfMonths(Utils.setIntValue(members));
		lot.setDescription(description);
		lot.setCommisionper(Utils.setIntValue(commission));
		lot.setStatus(2);
		lot.setUpdatedBy(1);
		lot.setUpdatedDate(new Date());
		lot.setStartDate(new java.sql.Timestamp(startDate.getTime()));
		int typ=0;
		try {
			typ=Integer.parseInt(lotType);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		lot.setType(typ);
		int before=0;
		try {
			before=Integer.parseInt(beforeAmount);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		lot.setBeforeLiftAmount(before);
		
		int after=0;
		try {
			after=Integer.parseInt(afterAmount);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		lot.setAfterLiftAmount(after);
		lotService.addLot(lot);
		
		return "success";
		
	}
	//-------------------Retrieve single lot details--------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/search/{lotid}")
	public Lot getlot(@PathVariable("lotid") int lotid,HttpServletRequest req, Model model) {
		System.out.println("Fetching lot details with X00001  " +lotid);
		List<MemberVO> m=lotService.searchlotMembers(lotid);
		
	int liftMember =0, nonLiftMembers=0;
		
		if(m!=null){
			Map<String,String> users=(Map<String,String>)req.getSession().getAttribute("userNames");
			System.out.println("user from session "+users);
		for(MemberVO mem:m){
			mem.setReferName(users.get(mem.getRefId()+""));
			if(mem.paidDate!=null){
			liftMember++;
			}
		}
		}
		
		System.out.println("the list of members in cont"+m);
		Lot lot = lotService.searchById(lotid);
		lot.setStatusName(Conversions.lotStatus.get(lot.getStatus()));
		model.addAttribute("memberTotalCount",m.size());
		model.addAttribute("liftMembers",liftMember);
		model.addAttribute("nonLiftMembers",m.size()-liftMember);
		
		return lot;
	}
	
	//-------------------Retrieve ALL lots--------------------------------------------------------
	
    @ResponseBody
	@RequestMapping(value = "/searchall", method = RequestMethod.GET)
	public List<Lot> getAllLot(Model model) {
		List<Lot> lots = lotService.findAllLots();
		for(Lot l:lots){
			l.setStatusName(Conversions.lotStatus.get(l.getStatus()));
		}
		model.addAttribute("lot",lots);
		return lots;
	}
		@ResponseBody
		@RequestMapping(value = "/dateupdate/{lotid}", method = RequestMethod.POST)
		public String dateUpdate(@PathVariable("lotid") int lotid,Model model,HttpServletRequest req) {
			System.out.println("Fetching lot details with X00001  " +lotid);
			String  startDate=req.getParameter("startdate");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("start date is new "+startDate);
			Date date=null;
			try {
				date = sdf.parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(" new date is "+date);
			lotService.updateDate(lotid,date);
			return "success";
			
		}
		
		@ResponseBody
		@RequestMapping(value = "/deletemember/{memrowid}")
		public String deleteMember(@PathVariable("memrowid") int memrowid) {
			System.out.println("member id is"+memrowid);
			lotService.deleteMember(memrowid);
			return "success";
		}
		@ResponseBody
		@RequestMapping(value = "/deletlot/{lotid}")
		public String deletlot(@PathVariable("lotid") int lotid,Model model) {
			System.out.println("LOt id is"+lotid);
			lotService.deletelot(lotid);
			return "success";
		}
  
		@ResponseBody
		@RequestMapping(value = "/lotEdit/{lotid}")
		public Lot lotEdit(@PathVariable("lotid") int lotid,Model model,HttpServletRequest request) {
			System.out.println("Fetching all Lot with X00001 memberEdit " + lotid);
			Lot lots = lotService.searchById(lotid);
			
			return lots;
		}
		@ResponseBody
		@RequestMapping(value = "/statusUpdated/{lotid}", method=RequestMethod.POST )
		public String statusUpdate(@PathVariable("lotid") int lotid,HttpServletRequest req) {
			System.out.println("Fetching lot details with X00001  " +lotid);
			
			String  status=req.getParameter("status");
			
			System.out.println("start status is new "+status);
			if(!Utils.isNull(status)){
				int inStatus=Integer.parseInt(status);
				
				lotService.statusUpdate(lotid,inStatus);
			}
			
			
			return "success";
			
		}
}
