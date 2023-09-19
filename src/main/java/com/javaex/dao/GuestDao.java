package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
	
}





