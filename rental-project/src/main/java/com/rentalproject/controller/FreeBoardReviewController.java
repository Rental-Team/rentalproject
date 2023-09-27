package com.rentalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.service.FreeBoardReviewService;

import lombok.Setter;

@Controller
@RequestMapping(path = { "/freeboard" })
public class FreeBoardReviewController {
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReviewService freeBoardReviewService;
	
	@PostMapping(path = {"/freeboard-review"})   // 자유게시글 댓글 쓰기 
	public String wrtieFreeBoardReview(FreeBoardReviewDto freeBoardReview) {
		
		System.out.println("댓글내용:" + freeBoardReview);
		
		freeBoardReviewService.WriteFreeBoardReview(freeBoardReview); 
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d", freeBoardReview.getFreeBoardNo());
		
	}
	
	@GetMapping(path = {"/delete-reply"}) // 자유게시글 댓글 삭제 
	public String deleteFreeBoardReview(int freeBoardReplyNo, int freeBoardNo) {
		
		freeBoardReviewService.deleteFreeBoardReview(freeBoardReplyNo);
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d", freeBoardNo);
	}
	
	@PostMapping(path = {"/edit-reply"}) // 자유게시글 댓글 수정
	public String editFreeBoardReview(FreeBoardReviewDto freeBoardReview) {
		
		System.out.println("댓글수정내용:" + freeBoardReview);
		 
		freeBoardReviewService.editFreeBoardReview(freeBoardReview);
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d", freeBoardReview.getFreeBoardNo());
	}
	
}
