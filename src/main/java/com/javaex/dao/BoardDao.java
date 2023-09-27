package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		System.out.println("BoardDao.boardSelect()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList", keyword);
		
		return boardList;
	}
	
	
	//리스트 (검색X, 페이징O)
	public List<BoardVo> boardSelectList3(int startRNum, int endRNum) {
		System.out.println("BoardDao.boardSelectList3()");
		
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("startRNum", startRNum);
		pageMap.put("endRNum", endRNum);
		//System.out.println(pageMap);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList3", pageMap);
		
		return boardList;
	}
	
	
	//글 전체 갯수
	public int selectTotalCnt() {
		System.out.println("BoardDao.selectTotalCnt()");
		
		//총 글 갯수 
		int totalCount = sqlSession.selectOne("board.selectTotalCnt");
		
		return totalCount;
	} 
	
	
	//리스트 (검색O, 페이징O)
	public List<BoardVo> boardSelectList4(String keyword) {
		System.out.println("BoardDao.boardSelectList4()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.boardSelectList4", keyword);
		
		return boardList;
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






