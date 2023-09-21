package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.mapper.PrivateQnaMapper;

public class PrivateQnaServiceImpl implements PrivateQnaService {

	@Autowired
	private PrivateQnaMapper privateQnaMapper;
	
	@Override
	public void writeBoard(PrivateQnaDto privateqna) {
	
			privateQnaMapper.insertBoard(privateqna);
	}

	@Override
	public List<PrivateQnaDto> listBoard() {
		
		List<PrivateQnaDto> boardList = privateQnaMapper.selectAllBoard();
		return boardList;
	}

	@Override
	public PrivateQnaDto findQnaBoardByQnaNo(int qnaNo) {
		
		PrivateQnaDto qnaBoard = privateQnaMapper.selectQnaBoardByQnaNo(qnaNo);
		return qnaBoard;
	}

	
}
