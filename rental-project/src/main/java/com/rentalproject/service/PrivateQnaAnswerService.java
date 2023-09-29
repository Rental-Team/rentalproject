package com.rentalproject.service;

import com.rentalproject.dto.PrivateQnaAnswerDto;

public interface PrivateQnaAnswerService {

	void writeAnswer(PrivateQnaAnswerDto privateQnaAnswer);

	void editAnswer(PrivateQnaAnswerDto privateQnaAnswer);
}