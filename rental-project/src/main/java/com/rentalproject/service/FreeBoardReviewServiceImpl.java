package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.mapper.FreeBoardReviewMapper;

import lombok.Setter;

public class FreeBoardReviewServiceImpl implements FreeBoardReviewService{
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReviewMapper freeboardReviewMapper;
	
	@Override   // 자유게시판 댓글 작성 
	public void WriteFreeBoardReview(FreeBoardReviewDto freeBoardReview) {
		
		freeboardReviewMapper.insertFreeBoardReview(freeBoardReview);
		freeboardReviewMapper.updateReplyParents(freeBoardReview.getFreeBoardReplyNo(), freeBoardReview.getFreeBoardReplyNo());
		
	}
	
	@Override   // 자유게시판 댓글 삭제 
	public void deleteFreeBoardReview(int freeBoardReplyNo) {
		freeboardReviewMapper.deleteFreeBoardReview(freeBoardReplyNo);
	}
	
	@Override // 자유게시판 댓글 수정 
	public void editFreeBoardReview(FreeBoardReviewDto freeBoardReview) {
		freeboardReviewMapper.editFreeBoardReview(freeBoardReview);
	}
	

	
	@Override
	public List<FreeBoardReviewDto> getReviewListByFreeBoardNo(int freeBoardNo) {
		List<FreeBoardReviewDto> reviews = freeboardReviewMapper.selectFreeBoardReviewByFreeBaordNo(freeBoardNo);
		return reviews;
	}

	@Override
	public FreeBoardReviewDto findFreeBoardReviewByFreeBoardReplyNo(int freeBoardReplyNo) {
		FreeBoardReviewDto freeBoardReview = freeboardReviewMapper.selectFreeBoardReviewByFreeBoardNo(freeBoardReplyNo);
		return freeBoardReview;
	}

	@Override
	public void updateReplySequence(FreeBoardReviewDto freeBoardReview) {
		freeboardReviewMapper.updateReplySequence(freeBoardReview);
		
	}

	@Override
	public void writeRereply(FreeBoardReviewDto freeBoardReview) {
		freeboardReviewMapper.insertRereply(freeBoardReview);
		
	}

}
