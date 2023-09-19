package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {

	@Autowired
	private RBoardDao rBoardDao;
	
	
	//게시판 리스트 가져오기
	public List<RBoardVo> getRBoardList(String keyword) {
		System.out.println("RBoardService.getBoardList()");
		
		List<RBoardVo> rBoardList = rBoardDao.boardSelect(keyword);
		
		return rBoardList;
	}

}





