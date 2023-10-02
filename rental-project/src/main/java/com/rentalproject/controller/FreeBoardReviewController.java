package com.rentalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.service.FreeBoardReviewService;

import lombok.Setter;

@Controller
@RequestMapping(path = { "/freeboard" })
public class FreeBoardReviewController {
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReviewService freeBoardReviewService;
	
	@PostMapping(path = {"/freeboard-review"})   // 자유게시글 댓글 쓰기 
	public String wrtieFreeBoardReview(FreeBoardReviewDto freeBoardReview,
									   @RequestParam (defaultValue="-1") int pageNo) {
		
		if(pageNo < 1) {
			return "redirect:freeboardlist";
		} 
		freeBoardReviewService.WriteFreeBoardReview(freeBoardReview); 
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d&pageNo=%d", freeBoardReview.getFreeBoardNo(), pageNo);
		
	}
	
	@GetMapping(path = {"/delete-reply"}) // 자유게시글 댓글 삭제 
	public String deleteFreeBoardReview(int freeBoardReplyNo, int freeBoardNo, int pageNo) {
		
		freeBoardReviewService.deleteFreeBoardReview(freeBoardReplyNo);
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d&pageNo=%d", freeBoardNo, pageNo);
	}
	
	@PostMapping(path = {"/edit-reply"}) // 자유게시글 댓글 수정
	public String editFreeBoardReview(FreeBoardReviewDto freeBoardReview,
									  @RequestParam(defaultValue="-1") int pageNo) { 
		 
		freeBoardReviewService.editFreeBoardReview(freeBoardReview);
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d&pageNo=%d", freeBoardReview.getFreeBoardNo(), pageNo);
	} 
}
