package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<BoardVo> boardSelect(String keyword) {
		System.out.println("BoardDao.boardSelect");
		
		List<BoardVo> BoardList = sqlSession.selectList("board.selectList", keyword);
		
		return BoardList;
	}
	
	//조회수 증가
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit()");
		
		int count = sqlSession.update("board.updateHit", no);
		//System.out.println("updateHit() count: "+count);
		
		return count;
	}
	
	//글 1개 가져오기
	public BoardVo boardSelectOne(int no) {
		System.out.println("BoardDao.boardSelectOne()");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectOne", no);
		boardVo.setContent(boardVo.getContent().replace("\r\n", "<br>"));
		
		return boardVo;
	}
	
	//글쓰기
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardDao.boardWrite()");
		
		int count = -1;
		
		try {
			count = sqlSession.insert("board.insertBoard", boardVo);
		}catch(DataIntegrityViolationException e) {
			System.out.println("DB 오류 발생: " + e);
		}
		
		return count;
	}
	
	//글 삭제
	public int boardDelete(int no) {
		System.out.println("BoardDao.boardDelete()");
		
		int count = -1;
		
		try {
			count = sqlSession.delete("board.boardDelete", no);
		}catch(DataIntegrityViolationException e) {
			System.out.println("DB 오류 발생: " + e);
		}
		
		return count;
	}
	
	//글 수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardDao.boardUpdate()");
		
		int count = -1;
		
		try {
			count = sqlSession.update("board.boardUpdate", boardVo);
		}catch(DataIntegrityViolationException e) {
			System.out.println("DB 오류 발생: " + e);
		}
		
		return count;
	}
	
}






