package com.rentalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.service.FreeBoardReviewService;

import lombok.Setter;

@Controller
@RequestMapping(path = { "/freeboard" })
public class FreeBoardReviewController {
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReviewService freeBoardReviewService;
	
	@GetMapping(path= {"/review-list"})   // ajax로 댓글 리스트 보기 기능 구현
	public String ShowReviewList(int freeBoardNo, Model model) {
		List<FreeBoardReviewDto> freeBoardReviews = freeBoardReviewService.getReviewListByFreeBoardNo(freeBoardNo);
		model.addAttribute("freeBoardReviews", freeBoardReviews);
		
		
		return "freeboard/review-list";
	}
	
	
	@PostMapping(path = {"/write-freeboard-review"})   // 자유게시글 댓글 쓰기 
	@ResponseBody
	public String wrtieFreeBoardReview(FreeBoardReviewDto freeBoardReview,HttpSession session,
									   @RequestParam (defaultValue="-1") int pageNo) {
		  
		if (session.getAttribute("loginuser") == null) { // 댓글 작성하기 버튼 눌렀을 때 로그인 안되어 있으면 로그인 화면으로 
			return "fail";
		}
		
		freeBoardReviewService.WriteFreeBoardReview(freeBoardReview); 
		
		return "success"; 
	}
	
	@GetMapping(path = {"/delete-reply"}) // 자유게시글 댓글 삭제 
	@ResponseBody
	public String deleteFreeBoardReview(int freeBoardReplyNo) {
		
		freeBoardReviewService.deleteFreeBoardReview(freeBoardReplyNo);
		
		return "success";
	}
	
	@PostMapping(path = {"/edit-reply"}) // 자유게시글 댓글 수정
	@ResponseBody
	public String editFreeBoardReview(FreeBoardReviewDto freeBoardReview) { 
		 
		freeBoardReviewService.editFreeBoardReview(freeBoardReview);
		
		return "success";
	} 
	
	@PostMapping(path = {"/write-rereply"}) // 자유게시글 대댓글
	@ResponseBody
	public String writeRereply(FreeBoardReviewDto freeBoardReview) {
	    String replyContent = freeBoardReview.getReplyContent();

	    if (replyContent != null && !replyContent.isEmpty()) {
	        FreeBoardReviewDto parentFreeBoardReply = freeBoardReviewService.findFreeBoardReviewByFreeBoardReplyNo(freeBoardReview.getFreeBoardReplyNo());

	        freeBoardReview.setFreeBoardNo(parentFreeBoardReply.getFreeBoardNo());
	        freeBoardReview.setReplyParents(parentFreeBoardReply.getReplyParents());
	        freeBoardReview.setReplySequence(parentFreeBoardReply.getReplySequence() + 1);
	        freeBoardReview.setReplyDepth(parentFreeBoardReply.getReplyDepth() + 1);
	        freeBoardReview.setReplyContent(replyContent);  

	        freeBoardReviewService.updateReplySequence(freeBoardReview);
	        freeBoardReviewService.writeRereply(freeBoardReview);

	        return "success";
	    } else {
	        return "fail";
	    }
	}
}
