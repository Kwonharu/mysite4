package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//로그인
	public UserVo userSelectOne(UserVo userVo) {
		System.out.println("UserDao.userVo");
		
		UserVo authUser = sqlSession.selectOne("user.selectAuthUser", userVo);
		
		return authUser;
		
	}
}
