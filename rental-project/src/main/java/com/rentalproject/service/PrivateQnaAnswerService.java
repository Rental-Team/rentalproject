package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.PrivateQnaAnswerDto;

public interface PrivateQnaAnswerService {

	void writeAnswer(PrivateQnaAnswerDto privateQnaAnswer);

	void editAnswer(PrivateQnaAnswerDto privateQnaAnswer);

	List<PrivateQnaAnswerDto> getAnswerListByQnaNo(int qnaNo);
}