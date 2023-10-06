package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.PrivateQnaDto;

public interface PrivateQnaService {

	void writeBoard(PrivateQnaDto privateqna);
	
	List<PrivateQnaDto> listBoard(int from, int count);
	
	List<PrivateQnaDto> listPrivateQnaByPage(int from, int count);//페이징
	
	int getPrivateQnaCount();//페이징 - 총 게시물 개수를 db에서 가져오기 = 관리자페이징
	
	int getPrivateQnaCountByMemberNo(int memberNo); // 일반 회원 페이징 
	 
	PrivateQnaDto findQnaBoardByQnaNo(int qnaNo);
	

	void updateAnswerStatus(int qnaNo, boolean answered);
	
	
	boolean getAnswerStatus(int qnaNo);
	
	//List<PrivateQnaDto> listBoardByUserId(int userId); //본인 글만 볼수있게(변수명 변경전 혹시 남겨둠 나중에 지울꺼)
	
	/*
	 * List<PrivateQnaDto> listBoardByMemberNo((int memberNo);
	 *///본인 글만 볼수있게
	
	String getMemberIdByQnaNo(int qnaNo);	// 작성자 조회 

	List<PrivateQnaDto> listBoardByMemberNo(int memberNo, int from, int count);

	

	
	

	
}