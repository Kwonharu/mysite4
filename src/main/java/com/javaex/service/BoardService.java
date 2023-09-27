package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//게시판 리스트 가져오기
	public List<BoardVo> getBoardList(String keyword) {
		System.out.println("BoardService.getBoardList()");
		
		List<BoardVo> boardList = boardDao.boardSelect(keyword);
		
		return boardList;
	}
	

	//리스트 (검색X, 페이징O)
	public Map<String, Object> getBoardList3(int crtPage) {
		System.out.println("BoardService.getBoardList3()");
		
		///////////////// 리스트 가져오기 ///////////////////////
		
		
		//페이지당 글 갯수
		int listCnt = 10;	//한 페이지에 출력되는 글 갯수
		
		//현제 페이지	crtPage 파라미터 받는다
		//int crtPage = 1;
		crtPage = (crtPage>0) ? crtPage : (crtPage = 1);
		
		//시작 글 번호
		int startRNum = (crtPage-1)*listCnt + 1;
		
		//끝 글 번호
		int endRNum = (startRNum+listCnt) - 1;
		
		/*
		 listCnt = 7	listCnt = 10
		 1	1	7		1	1	10
		 2	8	14		2	11	20
		 3	15	21		3	21	30
		 */
		
		//System.out.println(crtPage+", "+startRNum +", "+endRNum);
				
				
		List<BoardVo> boardList = boardDao.boardSelectList3(startRNum, endRNum);
		
		///////////////////////////////////////////////
		
		///////////////// 페이징 계산 ///////////////////////
		
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//전체 글 갯수
		int totalCnt = boardDao.selectTotalCnt();
		
		//마지막 버튼 번호
		int endPageBtnNo = (int) Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
								//올림 함수 //(현재 페이지 / 페이지당 버튼 갯수) * 페이지당 버튼 갯수
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount)+1;
		
		System.out.println(crtPage+", "+startPageBtnNo +", "+endPageBtnNo);
		
		//다음 화살표 유무
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCnt) {
			next = true;
			
		}else {	//다음 버튼이 없을(false) 때 endPageBtnNo를 다시 계산
			endPageBtnNo = (int) Math.ceil(totalCnt/(double)listCnt);
										/* 157/10.0 => 15.7 => 16 */
		}
		
		
		
		
		
		
		
		
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		/*
		System.out.println("===============================");
		System.out.println("crtPage: "+crtPage);
		System.out.println(startPageBtnNo);
		System.out.println(endPageBtnNo);
		System.out.println(prev);
		System.out.println(next);
		System.out.println("===============================");
		*/
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		return pMap;
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

		for(int i = 1; i<=157; i++) {
			boardVo.setTitle(i+" 번째 글 제목");
			boardVo.setContent(i+" 번째 글 내용");
			
			boardDao.boardWrite(boardVo);
		}
		
		return 1;
		
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
	
}






