package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<BoardVo> boardSelect() {
		System.out.println("BoardDao.boardSelect");
		
		List<BoardVo> BoardList = sqlSession.selectList("board.selectList");
		
		return BoardList;
	}
	
	//조회수 증가
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit()");
		
		int count = sqlSession.update("board.updateHit", no);
		System.out.println("updateHit() count: "+count);
		
		return 1;
	}
	
	//글 1개 가져오기
	public BoardVo boardSelectOne(int no) {
		System.out.println("BoardDao.boardSelectOne()");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectOne", no);
		boardVo.setContent(boardVo.getContent().replace("\r\n", "<br>"));
		
		return boardVo;
	}
	
}






