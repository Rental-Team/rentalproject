package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.mapper.PrivateQnaAnserMapper;

public class PrivateQnaAnswerServiceImpl implements PrivateQnaAnswerService {
	
	@Autowired
	private PrivateQnaAnserMapper privateQnaAnserMapper;
	
	
	@Override
	public void writeAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
		
		privateQnaAnserMapper.insertAnswer(privateQnaAnswer);
		
	}


	@Override
	public void editAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
			privateQnaAnserMapper.updateAnswer(privateQnaAnswer);
		
	}
	
	

}
