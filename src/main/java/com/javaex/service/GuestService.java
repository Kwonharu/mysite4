package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {

	@Autowired
	private GuestDao guestDao;
	
	
	public List<GuestVo> getGuestList() {
		
		List<GuestVo> guestList = guestDao.selectGuestList();
		
		return guestList;
	}
	
}





