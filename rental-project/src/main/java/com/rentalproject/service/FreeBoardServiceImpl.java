package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.mapper.FreeBoardMapper;
import com.rentalproject.mapper.FreeBoardReviewMapper;

public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	private FreeBoardMapper freeboardMapper;
	
	@Autowired
	private FreeBoardReviewMapper freeBoardReviewMapper;
	
	@Override
	public void writeFreeBoard(FreeBoardDto freeboard) throws Exception {
		
		freeboardMapper.writeFreeBoard(freeboard);  // 자유게시판에 게시글 저장
	}
	
	@Override
	public List<FreeBoardDto> listFreeBoard() {
		
		List<FreeBoardDto> freeBoardList = freeboardMapper.selectAllFreeBoard();
		
		return freeBoardList;  // 자유게시판 전체 목록 조회
		
	}
	
	@Override
	public FreeBoardDto findFreeBoardByFreeBoardNo(int freeBoardNo) {
		
		FreeBoardDto freeBoard = freeboardMapper.selectFreeBoardByFreeBoardNo(freeBoardNo); // 자유게시판 글 중 하나 클릭할때 그 게시글 조회
		
		List<FreeBoardReviewDto> freeBoardReviewList = freeBoardReviewMapper.selectFreeBoardReviewByFreeBaordNo(freeBoardNo);
		freeBoard.setFreeBoardReviewList(freeBoardReviewList); // 자유게시판 상세보기 하단 댓글 조회
		
		return freeBoard; 
		
	}
	
	@Override   // 자유게시판 게시글 수정 내용 가지고 오기
	public void editFreeBoard(FreeBoardDto freeBoard) {
		freeboardMapper.updateFreeBoard(freeBoard);
	}
	
	@Override // 자유게시판 게시글 삭제 
	public void deleteFreeBoard(int freeBoardNo) {
		freeboardMapper.deleteFreeBoard(freeBoardNo);
	}
}
