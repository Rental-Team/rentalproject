package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.mapper.PrivateQnaAnserMapper;
import com.rentalproject.mapper.PrivateQnaMapper;

public class PrivateQnaServiceImpl implements PrivateQnaService {

	@Autowired
	private PrivateQnaMapper privateQnaMapper;
	
	@Autowired
	private PrivateQnaAnserMapper privateQnaAnserMapper;
	
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
		List<PrivateQnaAnswerDto> answerList = privateQnaAnserMapper.selectPrivateQnaAnserbyQnaNo(qnaNo);
		qnaBoard.setPrivateQnaAnswerList(answerList);
		return qnaBoard;
	}

	
}
