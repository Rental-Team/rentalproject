package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.mapper.PrivateQnaAnswerMapper;

public class PrivateQnaAnswerServiceImpl implements PrivateQnaAnswerService {
	
	@Autowired
	private PrivateQnaAnswerMapper privateQnaAnswerMapper;
	
	
	@Override
	public void writeAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
		
		privateQnaAnswerMapper.insertAnswer(privateQnaAnswer);
		
	}


	@Override
	public void editAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
			privateQnaAnswerMapper.updateAnswer(privateQnaAnswer);
		
	}
	
	

}
