package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.PrivateQnaDto;

public interface PrivateQnaService {

	void writeBoard(PrivateQnaDto privateqna);
	
	List<PrivateQnaDto> listBoard();
	
	 
	PrivateQnaDto findQnaBoardByQnaNo(int qnaNo);
	

	void updateAnswerStatus(int qnaNo, boolean answered);
	
	
	boolean getAnswerStatus(int qnaNo);
	
	List<PrivateQnaDto> listBoardByUserId(int userId); //본인 글만 볼수있게
	
	String getMemberIdByQnaNo(int qnaNo);	// 작성자 조회 
}