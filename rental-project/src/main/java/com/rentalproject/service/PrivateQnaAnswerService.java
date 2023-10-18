package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.PrivateQnaAnswerDto;

public interface PrivateQnaAnswerService {
	/* 답변작성 */
	void writeAnswer(PrivateQnaAnswerDto privateQnaAnswer);

	/* 답변수정 */
	void editAnswer(PrivateQnaAnswerDto privateQnaAnswer);

	/* 답변글 조회 */
	List<PrivateQnaAnswerDto> getAnswerListByQnaNo(int qnaNo);
}