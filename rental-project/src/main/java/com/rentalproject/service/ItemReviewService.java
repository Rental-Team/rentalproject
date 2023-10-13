package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.ItemReviewDto;

public interface ItemReviewService {

	void WriteItemReview(ItemReviewDto itemReview);

	List<ItemReviewDto> getReviewListByItemNo(int itemNo);

	void deleteItemReview(int reviewNo);

	void editItemReview(ItemReviewDto itemReview); 
	
	ItemReviewDto findItemReviewByReviewNo(int reviewNo);
	
	void updateSeqeunce(ItemReviewDto itemReview);
	
	void writeReply(ItemReviewDto itemReview);

}
