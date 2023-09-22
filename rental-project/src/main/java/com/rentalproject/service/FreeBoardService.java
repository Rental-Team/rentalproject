package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.FreeBoardDto;

public interface FreeBoardService {
	
	void writeFreeBoard(FreeBoardDto freeboard) throws Exception;
	
	List<FreeBoardDto> listFreeBoard();   // 자유게시판의 어떤 글이 이동하는지 인터페이스 등록
	
	FreeBoardDto findFreeBoardByFreeBoardNo(int freeBoardNo); // free보드 넘버로 보드 찾기

	void editFreeBoard(FreeBoardDto freeBoard);
	
	void deleteFreeBoard(int freeBoardNo);

}
