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
	
	//등록
	public int guestInsert(GuestVo guestVo) {
		System.out.println("GuestService.guestInsert()");
		
		int count = guestDao.insertGuest(guestVo);
		
		return count;
	}
	
	//등록
	public int guestDelete(int no) {
		System.out.println("GuestService.guestDelete()");
		
		int count = guestDao.deleteGuest(no);
		
		return count;
	}
	
	
	public GuestVo getGuest(int no) {
		
		GuestVo guestVo = guestDao.selectOneGuest(no);
		
		return guestVo;
	}
	
	
	//guest 등록 ajax
	public GuestVo addGuest(GuestVo guestVo) {
		System.out.println("GuestService.addGuest()");
		
		//등록
		//System.out.println(guestVo);
		int count = guestDao.insertSelectKey(guestVo);
		//System.out.println(guestVo);
		
		//데이터 1개 가져오기
		// no값 확인
		int no = guestVo.getNo();
		// no데이터 가져오기
		
		return guestDao.selectOneGuest(no);
	}
	
	
	//guest 삭제 ajax
	public boolean deleteGuest(GuestVo guestVo) {
		System.out.println("GuestService.guestDelete()");
		
		//no와 password가 일치하는 계정 찾기
		GuestVo gVo = guestDao.selectGuestOne(guestVo);
		//System.out.println(gVo);
		
		//비즈니스 로직
		if(gVo == null){	//해당 계정 없음 (패스워드 틀림)
			return false;
		}else {	//계정이 있음 (패스워드 일치 시)
			guestDao.deleteGuest(gVo.getNo());
			return true;
		}
		
	}
	
}





