package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
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
		
		UserVo authUser = userDao.userSelectOne(userVo);
		
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
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
	public String join() {
		System.out.println("UserController.join()");
		
		return "";
	}
	
}





