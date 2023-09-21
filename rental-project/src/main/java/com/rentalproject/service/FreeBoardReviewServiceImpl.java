package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.mapper.FreeBoardReviewMapper;

import lombok.Setter;

public class FreeBoardReviewServiceImpl implements FreeBoardReviewService{
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReviewMapper freeboardReviewMapper;
	
	@Override
	public void WriteFreeBoardReview(FreeBoardReviewDto freeboardReview) {
		
		freeboardReviewMapper.insertFreeBoardReview(freeboardReview);
		freeboardReviewMapper.updateReplyParents(freeboardReview.getFreeBoardReplyNo(), freeboardReview.getFreeBoardReplyNo());
		
	}
	

}
