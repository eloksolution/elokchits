package in.eloksolutions.ala.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.eloksolutions.ala.beans.LotDetailsVO;
import in.eloksolutions.ala.beans.MemberVO;
import in.eloksolutions.ala.model.LotMember;
import in.eloksolutions.ala.service.LotMemberService;
import in.eloksolutions.ala.service.MemberService;
import in.eloksolutions.ala.util.Utils;

@Controller
@RequestMapping("/lotmember")
public class LotMemberController {
	@Autowired
	LotMemberService lotMemberService;
	@Autowired
	MemberService memberService;
	
	//-------------------add Member---------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addMembers(HttpServletRequest request,Model model){
		System.out.println("Request is coming");
		String  lotId=request.getParameter("lotId");
		String  memId=request.getParameter("memId");
		String memName=request.getParameter("memName");
		String refId=request.getParameter("refId");
		System.out.println("user from session refId memid"+refId+" memId"+memId+" lotId"+lotId);
		LotMember mem=new LotMember();
		mem.setLotId(Utils.setIntValue(lotId));
		mem.setMemId(Utils.setIntValue(memId));
		mem.setMemName(memName);
		mem.setRefId(Utils.setIntValue(refId));
		mem.setUpdatedDate(new Date());
		lotMemberService.addMember(mem);
			
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "/lotMembers/{lotid}")
	public List<LotMember> lotMembers(@PathVariable("lotid") int lotid,Model model,HttpServletRequest request) {
		System.out.println("Fetching all Lot with X00001 lotmemberId " + lotid);
		
		List<LotMember> m= lotMemberService.findLotsMembers(lotid);
	
		return m;
	}
	
	@ResponseBody
	@RequestMapping(value = "/allLotMembers/{lotid}")
	public List<MemberVO> allLotMembers(@PathVariable("lotid") int lotid) {
		System.out.println("Fetching all Lot with X00001 lotMembersId " + lotid);
		
		List<MemberVO> lotMembers= lotMemberService.allLotMembers(lotid);
		System.out.println("Fetching all Lot with X00001 alllotMembers " + lotMembers);
		return lotMembers;
	}
	
}
