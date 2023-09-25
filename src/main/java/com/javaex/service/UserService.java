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
	
	//idCheck
	public boolean idCheck(String id) {
		System.out.println("UserService.idCheck()");
		//System.out.println(id);
		
		UserVo userVo = userDao.selectUserOneById(id);
		//System.out.println(userVo);
		
		//비즈니스 로직
		if(userVo == null){	//해당 id 없음
			return true;
		}else {
			return false;
		}
	}
	
}





