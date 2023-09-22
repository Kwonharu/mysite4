package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
public class ApiGuestController {

	@Autowired
	private GuestService guestService;
	
	//방명록 메인 화면
	@RequestMapping(value="/api/guest/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestController.addList()");
		
		return "guest/listAjax";
	}
	
	//방명록 데이터만 가져오기(데이터만 전송) ajax
	@ResponseBody	//response에 넣겠다
	@RequestMapping(value="/api/guest/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestVo> list() {
		System.out.println("ApiGuestController.list()");
		
		//전체 리스트 가져오기
		List<GuestVo> guestList = guestService.getGuestList();
		System.out.println(guestList);
		
		return guestList;
			 //메모리의 주소 (실제 객체가 아님)
	}
	
}






