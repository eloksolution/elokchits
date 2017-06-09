package in.eloksolutions.ala.controller;

import in.eloksolutions.ala.beans.LotDetailsVO;
import in.eloksolutions.ala.beans.MemberBillVO;
import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.LotMember;
import in.eloksolutions.ala.service.AuctionService;
import in.eloksolutions.ala.service.LotService;
import in.eloksolutions.ala.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	AuctionService auctionService;
	@Autowired
	LotService lotService;
	//-------------------Retrieve All pincode Members--------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/home/{year}/{month}", method = RequestMethod.GET)
	public List<LotDetailsVO> getAllMembers(@PathVariable("year") Integer year,@PathVariable("month") Integer month) {
		List<LotDetailsVO> mav = getLotDetails(year, month);
		return mav;
	}
	private List<LotDetailsVO> getLotDetails(Integer year, Integer month) {
		
		List<LotDetailsVO> lotdetails=auctionService.getLotDetails(year, month+1);
		System.out.println("lot detals vov"+lotdetails);
		
		return lotdetails;
	}
	@ResponseBody
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public List<LotDetailsVO> getLotDetails(HttpServletRequest req) {
		String yy=req.getParameter("year");
		String mm=req.getParameter("month");
		System.out.println("month is now "+mm);
		
		System.out.println("year is know"+ yy);
		int year=0,month=0;
		if(yy==null){
			 Calendar cal = Calendar.getInstance();
			 year = cal.get(Calendar.YEAR);
			 System.out.println("after if year  is now "+year);
			 month = cal.get(Calendar.MONTH);
			 System.out.println("after if month  is now "+month);
		}else{
			year=Integer.parseInt(yy);
			month=Integer.parseInt(mm);
		}
		List<LotDetailsVO> lotDetails = getLotDetails(year, month);
		return lotDetails;
	}
@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String getAllMembers(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("in the save method"+req);
		Map<String,String[]> map=req.getParameterMap();
		Set<String> keys=map.keySet();
		System.out.println("in keys"+keys);
		for(String k:keys){
			String[] vals=req.getParameterValues(k);
			System.out.println("keys k"+k+"  vals "+Arrays.asList(vals));
			String amount=vals[0];
			String lotMemId=vals[2];
			String slmemid="";
			if(vals.length>=2){
				String smemName=vals[1];
				if(smemName==null|| smemName.trim().length()==0)continue;
				String[] memNameArr=smemName.split("#");
				if(memNameArr.length>=2)
					slmemid=memNameArr[1];
				else
					slmemid=lotMemId;
			}else{
				continue;
			}
			
			if( (slmemid.trim().length()==0 ))continue;
				int memId=Integer.parseInt(slmemid);
				LotMember lm=auctionService.searchById(memId);
				Calendar cal=Calendar.getInstance();
				Integer year=0,month=0;
				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH);
				lm.setMonthLift(month+1);
				lm.setYearLift(year);
				lm.setPaidDate(new Date());
				lm.setAuctionAmt(Integer.parseInt(amount));
				auctionService.update(lm);
			System.out.println("Saved thd datea ");
		}
	
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchMember", method = RequestMethod.GET)
	public List<Map<String, String>> getAllMembers(HttpServletRequest req) {
		String q=req.getParameter("term");
		String lotid=req.getParameter("lotid");
		System.out.println("lotid is"+lotid+"  and term  "+q);
		List<MemberVO> mem=auctionService.searchMembers(q ,lotid);
		List<Map<String, String>> data=new ArrayList<>();
		for(MemberVO m:mem){
			LinkedHashMap<String, String> map=new LinkedHashMap<>();
			String lastName=Utils.trim(m.lastName);
			map.put("value", m.firstName+","+m.lastName+"-Slot #"+m.lotMemberId);
			map.put("value", m.firstName+","+lastName+"-Slot #"+m.lotMemberId+"#"+m.lotCode);
			map.put("id", m.lotMemberId);
			data.add(map);
		}
		System.out.println("Data  is"+data);
		return data;
	}
	@ResponseBody
	@RequestMapping(value = "/bil", method = RequestMethod.GET)
	public ModelAndView billGen(HttpServletRequest req) {
		String y=req.getParameter("year");
		String m=req.getParameter("mon");
		String sRefid=req.getParameter("ref");
		String typ=req.getParameter("typ");
		ModelAndView mav=new ModelAndView();
		int year=0,mon=0;
		if(y==null){
			 Calendar cal = Calendar.getInstance();
			 year = cal.get(Calendar.YEAR);
			 mon = cal.get(Calendar.MONTH);
		}else{
			year=Integer.parseInt(y);
			mon=Integer.parseInt(m);
		}
		int refId=0;
		if(sRefid!=null && sRefid.trim().length()>0){
			refId=Integer.parseInt(sRefid);
		}
		System.out.println("term is year"+y+" mon "+mon);
		
		System.out.println("term is year X0003***************"+year+" mon "+mon); 
		Map<String, List<MemberBillVO>> map=null;
		try {
			map=auctionService.billGeneration(mon, year,refId,typ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("bils",map);
		mav.setViewName("bil");
		if(typ!=null && typ.equals("1"))
			mav.setViewName("nbil");
		if(typ!=null && typ.equals("2"))
			mav.setViewName("pbil");
		return mav;
		
	}

	
}
