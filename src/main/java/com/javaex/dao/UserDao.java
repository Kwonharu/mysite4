package com.javaex.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//로그인
	public UserVo userSelectOne(UserVo userVo) {
		System.out.println("UserDao.userSelectOne()");
		
		UserVo authUser = sqlSession.selectOne("user.selectAuthUser", userVo);
		
		return authUser;
	}
	
	//회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao.userInsert()");
		
		int count = -1;
		
		try {
			count = sqlSession.insert("user.insertUser", userVo);
		}catch(DataIntegrityViolationException e) {
			System.out.println("error:" + e);
		}
		
		System.out.println(count);
		
		return count;
	}
	
	//회원 정보 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao.userUpdate()");
		//System.out.println(userVo);
		
		int count = -1;
		
		try {
			count = sqlSession.update("user.updateUser", userVo);
		}catch(DataIntegrityViolationException e) {
			System.out.println("error:" + e);
		}
		
		System.out.println(count);
		
		return count;
	}
	
	//세션의 authUser로 user 찾기
	public UserVo userSelect(UserVo userVo) {
		System.out.println("UserDao.userSelect()");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		
		return authUser;
	}
	
}
