package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestVo> selectGuestList(){
		
		List<GuestVo> guestList = sqlSession.selectList("guest.selectGuestList");	
		
		return guestList;
	}
	
	//등록
	public int insertGuest(GuestVo guestVo){
		System.out.println("GuestDao.insertGuest()");
		
		int count = -1;
		
		try {
			count = sqlSession.insert("guest.insertGuest", guestVo);	
		}catch(DataIntegrityViolationException e) {
			System.out.println("DB 오류 발생: " + e);
		}
		
		return count;
	}
	
	//삭제
	public int deleteGuest(int no){
		System.out.println("GuestDao.deleteGuest()");
		
		int count = -1;
		
		try {
			count = sqlSession.insert("guest.deleteGuest", no);	
		}catch(DataIntegrityViolationException e) {
			System.out.println("DB 오류 발생: " + e);
		}
		
		return count;
	}
	
}





