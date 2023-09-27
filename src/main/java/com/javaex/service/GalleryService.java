package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.FileVo;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao; 
	
	
	//갤러리 리스트 가져오기
	public List<GalleryVo> getGalleryList() {
		System.out.println("GalleryService.getGalleryList()");
		
		List<GalleryVo> galleryList = galleryDao.selectGalleryList();
		
		return galleryList;
	}
	
	
	//파일 업로드
	public int uploadFile(String content, String name, int no, MultipartFile file) {
		System.out.println("GalleryService.uploadFile()");
		
		//파일 null일때 처리 isEmpty()
		//...
		//.....
		
		
		//파일 저장 디렉토리
		String saveDir = "C:\\javaStudy\\upload";
		
		//파일 관련 정보 추출 //////////////////////////
		//오리지널 파일 명
		String orgName = file.getOriginalFilename();
		//System.out.println(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//System.out.println(exName);
		
		//저장 파일명(겹치지 않아야 함)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
							//		 long		 	 +			String
		//System.out.println(saveName);
		
		//파일 사이즈
		long fileSize = file.getSize();
		//System.out.println(fileSize);
		
		//파일 전체 경로
		String filePath = saveDir + "\\" + saveName;
		//System.out.println(filePath);
		
		//vo로 묶기
		GalleryVo galleryVo = new GalleryVo(no, name, content, orgName, saveName, filePath, fileSize); 
		//System.out.println(galleryVo);
		
		
		//dao 만들기 --> db 저장
		int count = galleryDao.insertUploadFile(galleryVo);
		
		
		//파일 저장(서버쪽 하드 디스크에 저장) //////////////////////////////
		
		try {
			byte[] fileData;
			
			fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	//글 1개 가져오기
	public GalleryVo getGalleryPost(int no) {
		System.out.println("GalleryService.getGalleryPost()");
		
		GalleryVo galleryVo = galleryDao.selectGalleryPost(no);
		
		return galleryVo;
	}
	
	//글 삭제
	public int eraseGallery(int no) {
		System.out.println("GalleryService.eraseGallery()");
		
		int count = galleryDao.deleteGallery(no);
		
		return count;
	}
}





