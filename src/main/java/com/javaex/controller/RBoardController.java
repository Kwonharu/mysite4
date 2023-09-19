package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;

@Controller
@RequestMapping(value="/rboard")
public class RBoardController {
	
	@Autowired
	private RBoardService rBoardService;
	
	//리스트(검색 O)
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value="keyword", required=false, defaultValue="") String keyword, Model model){
		System.out.println("RBoardController.list()");
		
		//boardService를 통해 리스트를 가져온다
		List<RBoardVo> rBoardList = rBoardService.getRBoardList(keyword);
		//System.out.println(boardList);
		
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("rBoardList", rBoardList);
		
		return "rboard/list";
	}
	
}






