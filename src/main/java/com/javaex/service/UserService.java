package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserService.getUser()");
		
		UserVo authUser = userDao.userSelectOne(userVo);
		
		return authUser;
	}
	
	//회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("UserService.insertUser()");
		
		int count = userDao.userInsert(userVo);
		
		return count;
	}
	
	//회원 정보 수정
	public int updateUser(UserVo userVo) {
		System.out.println("UserService.updateUser()");
		
		int count = userDao.userUpdate(userVo);
		
		return count;
	}
	
	//세션의 authUser로 user 찾기
	public UserVo getOneUser(UserVo userVo) {
		System.out.println("UserService.getOneUser()");
		
		UserVo authUser = userDao.userSelect(userVo);
		
		return authUser;
	}
}





