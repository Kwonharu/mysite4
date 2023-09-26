package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileService {

	//파일 업로드
	public String save(MultipartFile file) {
		System.out.println("FileService.save()");
		
		//파일 저장 디렉토리
		String saveDir = "C:\\javaStudy\\upload";
		
		//파일 관련 정보 추출 //////////////////////////
		//오리지널 파일 명
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exName);
		
		//저장 파일명(겹치지 않아야 함)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
							//		 long		 	 +			String
		System.out.println(saveName);
		
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);
		
		//파일 전체 경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//vo로 묶기
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize); 
		System.out.println(fileVo);
		
		
		//dao 만들기 --> db 저장
		System.out.println(fileVo + "DB저장");
		
		
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
		
		return saveName;
	};
	
	
	//서버쪽 파일명
	/*
	 겹치지 않아야 한다
	 - 저장 파일명(서버 중복 방지)
	 - 오리지널 파일명(다운로드) 
	 */
	
	//확장자
	
	//파일 사이즈
}







