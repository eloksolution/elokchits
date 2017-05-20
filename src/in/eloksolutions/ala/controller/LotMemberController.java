package in.eloksolutions.ala.controller;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.LotMember;
import in.eloksolutions.ala.model.Member;
import in.eloksolutions.ala.service.LotMemberService;
import in.eloksolutions.ala.service.MemberService;
import in.eloksolutions.ala.util.Utils;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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
		System.out.println("user from session refId memid"+refId+memId+lotId);
		LotMember mem=new LotMember();
		mem.setLotId(Utils.setIntValue(lotId));
		mem.setMemId(Utils.setIntValue(memId));
		mem.setMemName(memName);
		mem.setRefId(Utils.setIntValue(refId));
		mem.setUpdatedDate(new Date());
		lotMemberService.addMember(mem);
			
		return "";
	}
	@ResponseBody
	@RequestMapping(value = "/lotMember/{lotid}")
	public LotMember lotEdit(@PathVariable("lotid") int lotid,Model model,HttpServletRequest request) {
		System.out.println("Fetching all Lot with X00001 memberEdit " + lotid);
		LotMember lotMember = lotMemberService.lotMembers(lotid);
	
		return lotMember;
	}
}
