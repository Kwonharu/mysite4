package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	
	//파일 업로드 폼
	@RequestMapping(value="/fileupload/form", method= {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("GalleryController.form()");
		
		return "gallery/form";
	}
	
	
	//파일 업로드
	@RequestMapping(value="/fileupload/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		System.out.println("GalleryController.upload()");
		
		//파일 이름 가져옴
		//System.out.println(file.getOriginalFilename());
		
		//파일이 있으면 false / 없으면 true
		//System.out.println(file.isEmpty());
		
		//file 객체는 파일이 없어도 생성됨
		//System.out.println(file);
		
		
		String saveName = fileService.save(file);	//db 저장, 서버에 파일 복사
		
		model.addAttribute("saveName", saveName);
		
		return "gallery/result";
	}
	
}



