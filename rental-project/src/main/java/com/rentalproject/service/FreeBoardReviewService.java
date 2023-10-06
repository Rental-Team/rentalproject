package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;

public interface FreeBoardReviewService {
	
	void WriteFreeBoardReview(FreeBoardReviewDto freeBoardReview);

	void deleteFreeBoardReview(int freeBoardReplyNo);

	void editFreeBoardReview(FreeBoardReviewDto freeBoardReview);

	List<FreeBoardReviewDto> getReviewListByFreeBoardNo(int freeboardNo);
	

}
