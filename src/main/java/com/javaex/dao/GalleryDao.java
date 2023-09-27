package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//갤러리 리스트 가져오기
	public List<GalleryVo> selectGalleryList() {
		System.out.println("GalleryDao.selectGalleryList()");
		
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.selectGalleryList");
		
		return galleryList;
	}
	
	//업로드한 파일 정보 저장
	public int insertUploadFile(GalleryVo galleryVo) {
		System.out.println("GalleryDao.insertUploadFile()");
		int count = -1;
		
		count = sqlSession.insert("gallery.insertUploadFile", galleryVo);
		

		return count;
	}
	
	//갤러리 리스트 가져오기
	public GalleryVo selectGalleryPost(int no) {
		System.out.println("GalleryDao.selectGalleryPost()");
		
		GalleryVo galleryVo = sqlSession.selectOne("gallery.selectGalleryPost", no);
		
		return galleryVo;
	}
	
	//글 삭제
	public int deleteGallery(int no) {
		System.out.println("GalleryDao.deleteGallery()");
		
		int count = -1;
		
		count = sqlSession.delete("gallery.deleteGallery", no);
		
		return count;
	}

}





