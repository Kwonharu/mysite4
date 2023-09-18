package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인 폼
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
	
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo,
						HttpSession session) {
		System.out.println("UserController.login");
		
		UserVo authUser = userService.getUser(userVo);
		
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			//세션은 web 전용이므로 controller에서 처리한다.
			
			return "redirect:/";
		}else {
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout");
		
		//세션의 모든 값을 지움.
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원가입폼
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		
		int count = userService.insertUser(userVo);
		
		if(count != -1) {
			return "redirect:/user/joinOk";
		}else {
			return "redirect:/user/joinForm?result=fail";
		}
	}
	
	//회원가입 성공 폼
	@RequestMapping(value="/joinOk", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinOk() {
		System.out.println("UserController.joinOk()");
		
		return "user/joinOk";
	}
	
	//회원 수정 폼
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("UserController.modifyForm()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser != null) {
			UserVo authUserVo = userService.getOneUser(authUser);
			model.addAttribute("authUserVo", authUserVo);
			
			return "user/modifyForm";
		}else {
			return "redirect:/";
		}
	}
	
	//회원 수정
	@RequestMapping(value="/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.modify()");
		
		int count = userService.updateUser(userVo);
	
		if(count != -1) {
			return "redirect:/";
		}else {
			return "redirect:/user/modifyForm?result=fail";
		}
	}
	
}





