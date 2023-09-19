package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model){
		System.out.println("BoardController.list()");
		
		//boardService를 통해 리스트를 가져온다
		List<BoardVo> boardList = boardService.getBoardList();
		//System.out.println(boardList);
		
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	//게시글 
	@RequestMapping(value="/read", method={RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam(value="no") int no,
					   Model model){
		System.out.println("BoardController.read()");
		
		//글 1개 가져오기
		BoardVo boardVo = boardService.getBoard(no);
		//System.out.println(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	//글쓰기 폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm(){
		System.out.println("BoardController.writeForm()");
		
		return "board/writeForm";
	}
	
	//글쓰기
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo){
		System.out.println("BoardController.write()");
		
		int count = boardService.writeBoard(boardVo);
		
		if(count != -1) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/writeForm?result=fail";
		}
	}
	
	//글삭제
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no") int no, HttpSession session){
		System.out.println("BoardController.delete()");
			
		//세션값 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//지우고 싶은 글 가져오기
		BoardVo boardVo = boardService.getBoard(no);
		
		//세션 no == board userNo일 때
		if(authUser.getNo() == boardVo.getUserNo()) {
			//삭제
			int count = boardService.deleteBoard(no);
			
			return "redirect:/board/list";
		}else {
			return "redirect:/board/list?result=fail";
		}
	}
	
	//글수정 폼
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(){
		System.out.println("BoardController.modifyForm()");
		
		return "board/modifyForm";
	}
	
}






