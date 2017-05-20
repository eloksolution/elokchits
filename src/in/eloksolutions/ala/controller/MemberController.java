package in.eloksolutions.ala.controller;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;
import in.eloksolutions.ala.service.MemberService;
import in.eloksolutions.ala.util.Conversions;
import in.eloksolutions.ala.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService membersService;
	
	//-------------------add Member---------------------------------------------------------
	        @ResponseBody
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public  String addMembers(HttpServletRequest request,HttpSession session,Model model){
				System.out.println("Request is coming");
				//User user=(User)session.getAttribute("loginuser");
				String  fname=request.getParameter("firstname");
				String  lname=request.getParameter("lastname");
				String  phone=request.getParameter("phone");
				String  reference=request.getParameter("refId");
				String  address=request.getParameter("address");
				String  email=request.getParameter("email");
				System.out.println("Req email"+email);
				String  memId=request.getParameter("memId");
				System.out.println("Req memId"+memId);
				Member mem=new Member();
				if(!Utils.isNull(memId)){
					int imemid=Integer.parseInt(memId);
						mem.setMemId(imemid);
				}
				
				mem.setFirstName(fname);
				mem.setLastName(lname);
				mem.setPhone(phone);
				mem.setRefId(Utils.setIntValue(reference));
				mem.setAddress(address);
				mem.setEmail(email);
				mem.setStatus((byte) 1);
				mem.setUpdateDate(new Date());
				mem.setUpdatedBy(1);
				membersService.addMember(mem);
				System.out.println("the id in mem"+mem.getMemId());
				return "success";
			}
	//-------------------Retrieve single Member details--------------------------------------------------------
	    @ResponseBody
		@RequestMapping(value = "/search/{memid}")
			public String getMember(@PathVariable("memid") int memid,Model model,HttpServletRequest request) {
				System.out.println("Fetching all members with X00001  " + memid);
				Member members = membersService.searchById(memid);
				Map<String,String> users=(Map<String,String>)request.getSession().getAttribute("userNames");
				System.out.println("user from session "+users);
				members.setReferName(users.get(""+members.getRefId()));
				List<Lot> lot=membersService.searchMemberLots(memid);
				for(Lot l:lot){
					l.setStatusName(Conversions.lotStatus.get(l.getStatus()));
				}
				System.out.println(" in findMemberLots is con"+lot);
				model.addAttribute("lot", lot);
				model.addAttribute("mem",members);
				return "memberView";
			}
	//-------------------Retrieve All pincode Members--------------------------------------------------------
	        @ResponseBody
	     	@RequestMapping(value = "/searchall", method = RequestMethod.GET)
			public List<Member> getAllMembers(Model model,HttpServletRequest request) {
				List<Member> member = membersService.searchAllMembers();
				Map<String,String> users=(Map<String,String>)request.getSession().getAttribute("userNames");
				System.out.println("user from session "+users);
				
				/*for(Member m:member){
					m.setReferName(users.get(""+m.getRefId()));
				}*/
				model.addAttribute("mem",member);
				return member;
			}
	//-------------------Retrieve Members By Name--------------------------------------------------------
	        @ResponseBody
			@RequestMapping(value = "/searchname", method = RequestMethod.GET)
			public List<Map<String, String>> getMembers(HttpServletRequest request,Model model) {
				String  term=request.getParameter("term");
				System.out.println("the name in cont"+term);
				List<Member> member = membersService.searchByName(term);
				List<Map<String,String>> list=new ArrayList<>();
				Map<String,String> users=(Map<String,String>)request.getSession().getAttribute("userNames");
				System.out.println("user from session "+users);
			
				for(Member m:member){
					LinkedHashMap<String,String> map=new LinkedHashMap<>();
					String nam="";
				/*	if(m.getLastName()!=null)nam=m.getFirstName()+" "+m.getLastName()+"-"+users.get(m.getRefId()+"");
					else
						nam=m.getFirstName()+"-"+users.get(m.getRefId()+"");
					System.out.println("ref name is  "+nam);*/
					map.put("value",nam);
					map.put("memId",m.getMemId()+"");
					map.put("refId",m.getRefId()+"");
					list.add(map);
				}
				return list;
			}
		//-----------------------partnerMembers-------------------------------------------------
			
			@RequestMapping(value = "/partnerMembers", method = RequestMethod.GET)
			public String partnerMemberReport(Model model,HttpServletRequest request) {
				String partnerId=request.getParameter("partnerId");
				int partId=Integer.parseInt(partnerId);
				List<Member> members = membersService.getMembersForPartner(partId);
				System.out.println("Members are "+members);
				model.addAttribute("report",members);
				return "partnerMemberslist";
			}

            @ResponseBody
			@RequestMapping(value = "/memberEdit/{memid}")
			public Member memberEdit(@PathVariable("memid") int memid,Model model,HttpServletRequest request) {
				System.out.println("Fetching all members with X00001 memberEdit " + memid);
				Member members = membersService.searchById(memid);
				Map<String,String> users=(Map<String,String>)request.getSession().getAttribute("userNames");
				System.out.println("user from session "+users);
				//members.setReferName(users.get(""+members.getRefId()));
				model.addAttribute("mem",members);
				return members;
			}
            @ResponseBody
    		@RequestMapping(value = "/deletemember/{memid}")
    		public String deleteMember(@PathVariable("memid") int memid,Model model) {
    			System.out.println("member id is"+memid);
    			membersService.deleteMember(memid);
    			return "success";
    		}
            }
