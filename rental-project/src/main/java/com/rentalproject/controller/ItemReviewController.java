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
import com.rentalproject.dto.ItemReviewDto;
import com.rentalproject.service.ItemReviewService;

import lombok.Setter;

@Controller
@RequestMapping(path = {"/item"})
public class ItemReviewController {
	
	@Setter(onMethod_= {@Autowired})
	private ItemReviewService itemReviewService;
	
	@PostMapping(path = {"/write-item-review"})  // 상품 후기 작성 
	@ResponseBody
	public String writeItemReview(ItemReviewDto itemReview, HttpSession session,
									@RequestParam (defaultValue="-1") int pageNo) {
		
		if (session.getAttribute("loginuser") == null) { 
			
			return "fail";
		}
		
		itemReviewService.WriteItemReview(itemReview); 
		
	return "success";
	}
	
	@GetMapping(path= {"/review-list"})  // 후기 리스트 보기 
	public String showReviewList(int itemNo, Model model) {
		List<ItemReviewDto> itemReviews = itemReviewService.getReviewListByItemNo(itemNo);
		model.addAttribute("itemReviews", itemReviews); 
		
		return "item/review-list";
	}
	
	@GetMapping(path= {"/delete-review"}) // 후기 삭제
	@ResponseBody
	public String deleteItemReview(int reviewNo) {
		itemReviewService.deleteItemReview(reviewNo);
		
		return "success";
	}
	
	@PostMapping(path= {"/edit-review"}) // 후기 수정
	@ResponseBody
	public String editItemReview(ItemReviewDto itemReview) {
		itemReviewService.editItemReview(itemReview);
		return "success";
	} 
	
	@PostMapping(path= {"/write-reply"})
	@ResponseBody
	public String writeReply(ItemReviewDto itemReview) {
		String reviewContent = itemReview.getReviewContent();
		
		if (reviewContent != null && !reviewContent.isEmpty()) {
	       ItemReviewDto parentReply = itemReviewService.findItemReviewByReviewNo(itemReview.getReviewNo());

	        itemReview.setItemNo(parentReply.getItemNo());
	        itemReview.setParents(parentReply.getParents());
	        itemReview.setSequence(parentReply.getSequence() + 1);
	        itemReview.setDepth(parentReply.getDepth() + 1);
	        itemReview.setReviewContent(reviewContent);  

	        itemReviewService.updateSeqeunce(itemReview);
	        itemReviewService.writeReply(itemReview);

	        return "success";
	    } else {
	        return "fail";
	    }
		
	}
}
