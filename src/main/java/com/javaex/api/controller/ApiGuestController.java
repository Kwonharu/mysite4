package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;
import com.javaex.vo.JsonResultVo;

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
		//System.out.println(guestList);
		
		return guestList;
			 //메모리의 주소 (실제 객체가 아님)
	}
	
	//방명록 저장 ajax
	@ResponseBody
	@RequestMapping(value="/api/guest/add", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestVo add(@ModelAttribute GuestVo guestVo) {
		System.out.println("ApiGuestController.add()");

		GuestVo gVo = guestService.addGuest(guestVo);
		//System.out.println(gVo);
		
		return gVo;
	}
	
	//////////////////////////////////////////////////
	
	//방명록 메인 화면
	@RequestMapping(value="/api/guest/addList2", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList2() {
		System.out.println("ApiGuestController.addList2()");
		
		return "guest/listAjax2";
	}
	
	//요청 데이터를 json으로 받는다
	@ResponseBody
	@RequestMapping(value="/api/guest/add2", method = {RequestMethod.GET, RequestMethod.POST})
	public JsonResultVo add2(@RequestBody GuestVo guestVo) {
		System.out.println("ApiGuestController.add2()");
		System.out.println(guestVo);
		
		//service 처리
		GuestVo gVo = guestService.addGuest(guestVo);

		JsonResultVo jsonResultVo = new JsonResultVo();
		jsonResultVo.success(gVo);
		
		return jsonResultVo;
	}
	
	
	//복잡한 데이터 전송 테스트
	@RequestMapping(value="/api/guest/add3", method = {RequestMethod.GET, RequestMethod.POST})
	public String add3(@RequestBody List<GuestVo> guestList) {
		System.out.println("ApiGuestController.add3()");
		System.out.println(guestList);
		
		
		return null;
	}
	
}






