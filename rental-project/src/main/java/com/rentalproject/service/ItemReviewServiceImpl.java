package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemReviewDto;
import com.rentalproject.mapper.ItemReviewMapper;

import lombok.Setter;

public class ItemReviewServiceImpl implements ItemReviewService {
	
	@Setter(onMethod_= {@Autowired})
	private ItemReviewMapper itemReviewMapper;

	@Override
	public void WriteItemReview(ItemReviewDto itemReview) {
		 
		itemReviewMapper.insertItemReview(itemReview); 
		itemReviewMapper.updateParents(itemReview.getReviewNo(), itemReview.getReviewNo());
	}

	@Override
	public List<ItemReviewDto> getReviewListByItemNo(int itemNo) {
		List<ItemReviewDto> itemReviews = itemReviewMapper.selectItemReviewByItemNo(itemNo);
		return itemReviews;
	}

	@Override
	public void deleteItemReview(int reviewNo) {
		itemReviewMapper.deleteItemReview(reviewNo);
		
	}

	@Override
	public void editItemReview(ItemReviewDto itemReview) {
		itemReviewMapper.editItemReview(itemReview);
		
	}

	@Override
	public ItemReviewDto findItemReviewByReviewNo(int reviewNo) {
		ItemReviewDto itemReview = itemReviewMapper.selectItemReviewByReviewNo(reviewNo);
		return itemReview; 
	}

	@Override
	public void updateSeqeunce(ItemReviewDto itemReview) {
		itemReviewMapper.updateSequence(itemReview);
		
	}

	@Override
	public void writeReply(ItemReviewDto itemReview) {
		itemReviewMapper.insertReply(itemReview);
		
	}
	
	

}
