package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller	
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	
	//갤러리 리스트 가져오기
	@RequestMapping(value="/gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model,HttpSession session) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVo> galleryList = galleryService.getGalleryList();
		
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	//갤러리 등록
	@RequestMapping(value="/gallery/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="content") String content,
						 @RequestParam(value="file") MultipartFile file,
						 HttpSession session) {
		System.out.println("GalleryController.upload()");
		
		String name = ((UserVo) session.getAttribute("authUser")).getName();
		int no = ((UserVo) session.getAttribute("authUser")).getNo();
		
		galleryService.uploadFile(content, name, no, file);
		
		return "redirect:/gallery/list";
	}
	
}





