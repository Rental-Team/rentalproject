package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;

public interface FreeBoardService {
	
	void writeFreeBoard(FreeBoardDto freeboard) throws Exception;
	
	List<FreeBoardDto> listFreeBoard();   // 자유게시판의 어떤 글이 이동하는지 인터페이스 등록
	
	FreeBoardDto findFreeBoardByFreeBoardNo(int freeBoardNo); // 프리보드 넘버로 보드 찾기

	void editFreeBoard(FreeBoardDto freeBoard);
	
	void deleteFreeBoard(int freeBoardNo);

	void updateFreeBoardviewCount(int freeBoardViewCount); // 자유게시판 조회수 증가 

	FreeBoardAttachDto selectFreeBoardAttachByAttachNo(int attachNo);  // 첨부번호를 이용해 첨부파일 찾기 

}
