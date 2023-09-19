package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//게시판 리스트 가져오기
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService.getBoardList()");
		
		List<BoardVo> boardList = boardDao.boardSelect();
		
		return boardList;
	}
	
	public BoardVo getBoard(int no) {
		System.out.println("BoardService.getBoard()");
		
		//글 읽기 비즈니스 로직
		//조회수 증가
		int count = boardDao.updateHit(no);
		
		//글 1개 가져오기
		BoardVo boardVo = boardDao.boardSelectOne(no);
		//System.out.println(boardVo);
		
		return boardVo;
	}
	
	//글쓰기
	public int writeBoard(BoardVo boardVo) {
		System.out.println("BoardService.writeBoard()");

		int count = boardDao.boardWrite(boardVo);
		
		return count;
	}

	//글 삭제
	public int deleteBoard(int no) {
		System.out.println("BoardService.deleteBoard()");
		
		int count = boardDao.boardDelete(no);
		
		return count;
	}
	
	
	//글 삭제
	public int modifyBoard(BoardVo boardVo) {
		System.out.println("BoardService.modifyBoard()");
		
		int count = boardDao.boardUpdate(boardVo);
		
		return count;
	}
	
	//검색
	public List<BoardVo> getSearchList(String word) {
		System.out.println("BoardService.getSearchList()");
		
		List<BoardVo> boardList = boardDao.searchList(word);
		
		return boardList;
	}
}






