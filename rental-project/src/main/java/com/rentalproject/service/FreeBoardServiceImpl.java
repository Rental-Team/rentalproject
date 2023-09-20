package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.mapper.FreeBoardMapper;

public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	private FreeBoardMapper freeboardMapper;
	
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
		
		FreeBoardDto freeBoard = freeboardMapper.selectFreeBoardByFreeBoardNo(freeBoardNo);
		
		return freeBoard;
	}

}
