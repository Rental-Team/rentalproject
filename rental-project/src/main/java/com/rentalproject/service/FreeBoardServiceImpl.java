package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardAttachDto;
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
	public void writeFreeBoard(FreeBoardDto freeBoard) throws Exception {
		
		freeboardMapper.writeFreeBoard(freeBoard);  // 자유게시판에 게시글 저장
		
		// 자유게시판에 첨부파일 저장
		for(FreeBoardAttachDto freeBoardAttach : freeBoard.getFreeBoardAttachList()) {
			freeBoardAttach.setFreeBoardNo(freeBoard.getFreeBoardNo());
			freeboardMapper.insertFreeBoardAttach(freeBoardAttach); // 자동 증가 번호 생성 
		}
	}
	
	@Override
	public List<FreeBoardDto> listFreeBoard() {
		
		List<FreeBoardDto> freeBoardList = freeboardMapper.selectAllFreeBoard();
		
		return freeBoardList;  // 자유게시판 전체 목록 조회
		
	}
	
	@Override // 페이징
	public List<FreeBoardDto> listFreeBoardByPage(int from, int count) {
		List<FreeBoardDto> freeBoardList = freeboardMapper.selectFreeBoardByPage(from, count);
		return freeBoardList;
	}
	

	@Override  //페이징 - 총 게시물 개수를 db에서 가지고 오기 
	public int getFreeBoardCount() {
		int count = freeboardMapper.selectFreeBoardCount();
		return count;
	}
	
	@Override
	public FreeBoardDto findFreeBoardByFreeBoardNo(int freeBoardNo) {
		
		FreeBoardDto freeBoard = freeboardMapper.selectFreeBoardByFreeBoardNo(freeBoardNo); // 자유게시판 글 중 하나 클릭할때 그 게시글 조회
		
		if(freeBoard != null) {
			
			List<FreeBoardAttachDto> freeBoardAttachList = freeboardMapper.selectFreeBoardAttachByFreeBoardNo(freeBoardNo);
			freeBoard.setFreeBoardAttachList(freeBoardAttachList); // 첨부파일 조회
		
			List<FreeBoardReviewDto> freeBoardReviewList = freeBoardReviewMapper.selectFreeBoardReviewByFreeBaordNo(freeBoardNo);  
			freeBoard.setFreeBoardReviewList(freeBoardReviewList); // 자유게시판 상세보기 하단 댓글 조회
		}
		
		return freeBoard; 
		
	}
	
	@Override
	public FreeBoardAttachDto selectFreeBoardAttachByAttachNo(int attachNo) {
		FreeBoardAttachDto freeBoardAttach = freeboardMapper.selectFreeBoardAttachByAttachNo(attachNo); // 첨부번호로 첨부파일 찾기 
		return freeBoardAttach;
	}
	
	@Override   // 자유게시판 게시글 수정 내용 가지고 오기
	public void editFreeBoard(FreeBoardDto freeBoard) {
		freeboardMapper.updateFreeBoard(freeBoard);
		
		// 첨부파일 저장
		for(FreeBoardAttachDto freeBoardAttach : freeBoard.getFreeBoardAttachList()) {
			freeBoardAttach.setFreeBoardNo(freeBoard.getFreeBoardNo());
			freeboardMapper.insertFreeBoardAttach(freeBoardAttach); // 자동 증가 번호 생성 
		}
		 
	}
	
	@Override // 자유게시판 게시글 삭제 
	public void deleteFreeBoard(int freeBoardNo) {
		freeboardMapper.deleteFreeBoard(freeBoardNo);
	}
	
	@Override   // 자유게시판 조회수 증가 
	public void updateFreeBoardviewCount(int freeBoardNo) {
		freeboardMapper.updateFreeBoardviewCount(freeBoardNo);
	}

	@Override 
	public String getMemberId(int freeBoardNo) {
		String memberId = freeboardMapper.getMemberId(freeBoardNo);
		return memberId;
	}


}
