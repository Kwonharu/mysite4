package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	//리스트 출력
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestController.addList()");
		
		List<GuestVo> guestList = guestService.getGuestList();
		
		model.addAttribute("guestList", guestList);
		
		return "guest/addList";
	}
	
	//등록
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.insert()");
		
		int count = guestService.guestInsert(guestVo);
		System.out.println(count);
		
		if(count == -1) {
			return "redirect:/guest/addList";
		}else {
			return "redirect:/guest/addList";
		}
		
	}
	
}






