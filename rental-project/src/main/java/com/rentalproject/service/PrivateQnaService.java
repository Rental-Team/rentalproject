package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.PrivateQnaDto;

public interface PrivateQnaService {

	void writeBoard(PrivateQnaDto privateqna);
	
	List<PrivateQnaDto> listBoard();
	
	
	PrivateQnaDto findQnaBoardByQnaNo(int qnaNo);
}