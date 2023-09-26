package com.rentalproject.service;

import com.rentalproject.dto.FreeBoardReviewDto;

public interface FreeBoardReviewService {
	
	void WriteFreeBoardReview(FreeBoardReviewDto freeBoardReview);

	void deleteFreeBoardReview(int freeBoardReplyNo);

	void editFreeBoardReview(FreeBoardReviewDto freeBoardReview);
	

}
