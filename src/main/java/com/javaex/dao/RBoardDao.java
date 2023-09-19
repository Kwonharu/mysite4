package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<RBoardVo> boardSelect(String keyword){
		System.out.println("RBoardDao.boardSelect()");
		
		List<RBoardVo> rBoardList = sqlSession.selectList("rboard.selectList", keyword);
		
		return rBoardList;
	}
	
	
}




