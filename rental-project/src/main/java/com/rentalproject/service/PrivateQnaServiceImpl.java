package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.mapper.PrivateQnaAnswerMapper;
import com.rentalproject.mapper.PrivateQnaMapper;

public class PrivateQnaServiceImpl implements PrivateQnaService {

	@Autowired
	private PrivateQnaMapper privateQnaMapper;
	
	@Autowired
	private PrivateQnaAnswerMapper privateQnaAnswerMapper;
	
	@Override
	public void writeBoard(PrivateQnaDto privateqna) {
	
			privateQnaMapper.insertBoard(privateqna);
	}

	//여기 리스트 
	@Override
	public List<PrivateQnaDto> listBoard() {
		
		List<PrivateQnaDto> boardList = privateQnaMapper.selectAllBoard();
		return boardList;
	}

   // 여기 디테일 
	@Override
	public PrivateQnaDto findQnaBoardByQnaNo(int qnaNo) {
		
		PrivateQnaDto qnaBoard = privateQnaMapper.selectQnaBoardByQnaNo(qnaNo);
		List<PrivateQnaAnswerDto> answerList = privateQnaAnswerMapper.selectPrivateQnaAnserbyQnaNo(qnaNo);
		qnaBoard.setPrivateQnaAnswerList(answerList);
		return qnaBoard;
	}

	 @Override //답변 완료 업데이트
	    public void updateAnswerStatus(int qnaNo, boolean answered) {
	      
	        privateQnaMapper.updateAnswerStatus(qnaNo, answered);
 }
	 @Override
	 public boolean getAnswerStatus(int qnaNo) {
	     boolean answerStatus = privateQnaMapper.getAnswerStatus(qnaNo);
	     return answerStatus;
	 }

	 @Override
	 public String getMemberIdByQnaNo(int qnaNo) {
	     String memberId = privateQnaMapper.getMemberIdByQnaNo(qnaNo);
	     return memberId;
	 }

		/*
		 * @Override public List<PrivateQnaDto> listBoardByUserId(int userId) {
		 * List<PrivateQnaDto> boardList = privateQnaMapper.selectBoardByUserId(userId);
		 * return boardList; }
		 */
	 @Override
	 public List<PrivateQnaDto> listBoardByUserId(int userId) {
	     List<PrivateQnaDto> boardList;
	     
	     if (userId == 17) {
	         boardList = privateQnaMapper.selectAllBoard();
	     } else {
	         boardList = privateQnaMapper.selectBoardByUserId(userId);
	     }
	     
	     return boardList;
	 }


}