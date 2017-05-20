package in.eloksolutions.ala.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.Member;
import in.eloksolutions.ala.model.User;
import in.eloksolutions.ala.service.UserService;
import in.eloksolutions.ala.util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	//-------------------User Login--------------------------------------------------------
	 @ResponseBody
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public  String addUser(HttpServletRequest request,HttpSession session,Model model){
			System.out.println("Request is coming");
			//User user=(User)session.getAttribute("loginuser");
			String  firstName=request.getParameter("firstname");
			String  lastName=request.getParameter("lastname");
			String  userName=request.getParameter("email");
			String  password=request.getParameter("password");
			String  phone=request.getParameter("phonenumber");
			User user=new User();
	
			user.setUserId(userName);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserName(userName);
			user.setPassword(password);
			user.setPhone(phone);
			
			userService.addUser(user);
			
			return "success";
		}
	@ResponseBody
	@RequestMapping(value = "/login")
	public List<User> getLogin(HttpServletRequest request,Model model) {
		String user=request.getParameter("username");
		String pass=request.getParameter("password");
		User loggedUser=userService.getLoginUser(user, pass);
		if(loggedUser==null){
			model.addAttribute("msg", "UserName , Password invalid.Please enter correct details.");
			return (List<User>) loggedUser;
		}
		List<User> users=userService.findAllUsers();
		Map<String,String> userNames=new HashMap<String, String>();
		for(User u:users){
			userNames.put(u.getUserId(), u.getFirstName()+","+u.getLastName());
		}
		request.getSession().setAttribute("users", users);
		request.getSession().setAttribute("userNames", userNames);
		request.getSession().setAttribute("loginuser", loggedUser);
		System.out.println("-from session="+request.getSession().getAttribute("loginuser"));
		model.addAttribute("loginuser",loggedUser);
		return users;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/partners")
	public List<User> getPartners(HttpServletRequest request,Model model) {
		List<User> users=userService.findAllUsers();
		System.out.println("users "+users);
		model.addAttribute("users",users);
		return users;
		
	}
}
