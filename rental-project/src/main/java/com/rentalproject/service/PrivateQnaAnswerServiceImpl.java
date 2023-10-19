package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.mapper.PrivateQnaAnswerMapper;

public class PrivateQnaAnswerServiceImpl implements PrivateQnaAnswerService {
	
	@Autowired
	private PrivateQnaAnswerMapper privateQnaAnswerMapper;
	
	/* 답변작성 */
	@Override
	public void writeAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
		
		privateQnaAnswerMapper.insertAnswer(privateQnaAnswer);
		
	}

	/* 답변수정 */
	@Override
	public void editAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
		privateQnaAnswerMapper.updateAnswer(privateQnaAnswer);
		
	}

	/* 답변조회 */
	@Override
	public List<PrivateQnaAnswerDto> getAnswerListByQnaNo(int qnaNo) {
		   List<PrivateQnaAnswerDto> answers = privateQnaAnswerMapper.selectPrivateQnaAnserbyQnaNo(qnaNo);
		
		return answers;
	}
	
	

}
