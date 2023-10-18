package com.rentalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.ItemQnaDto;
import com.rentalproject.service.ItemQnaService;

@Controller
@RequestMapping(path = {"/item"})
public class ItemQnaController {

    @Autowired
    private ItemQnaService itemQnaService;

    @PostMapping(path = {"/write-item-Qna"})
    @ResponseBody
    public String writeItemQna(ItemQnaDto itemQna,  HttpSession session) {
    	
    if (session.getAttribute("loginuser") == null) { 
			
			return "fail";
		}
    	
    	itemQnaService.writeItemQna(itemQna);
        
        return "success";
    }
    
    @GetMapping(path= {"/itemqna-list" }) // 문의 리스트 보기
    public String showItemQnaList(int itemNo,Model model) {
    	
    	List<ItemQnaDto> itemQna = itemQnaService.listItemQna(itemNo);
    	model.addAttribute("itemQnas",itemQna);
    	
    	
    	return "item/itemqna-list" ;
    }
    
    @GetMapping(path= {"/delete-itemqna"}) // 문의 삭제 
    @ResponseBody
    public String deleteItemQna(int itemqnaNo) {
    	itemQnaService.deleteItemQna(itemqnaNo);
    	
    	return "success";
    	
    }
    
    
    
   @PostMapping(path={"/edit-itemqna" }) // 문의 수정 
   @ResponseBody
   public String editItemQna(ItemQnaDto itemQna) {
    	
    	itemQnaService.editItemQna(itemQna);
    	
    	return"success";
    }
   /////////////////////////////////////////////////
    
	
	  @PostMapping(path= {"/write-replyqna"})	  
	  @ResponseBody 
	  public String writeReplyQna(ItemQnaDto itemQna) {
       String itemQnaContent = itemQna.getItemqnaContent();
       			
       
      if(itemQnaContent != null && !itemQnaContent.isEmpty()) {
    	 ItemQnaDto parentReply = itemQnaService.findItemQnaByItemQnaNo(itemQna.getItemqnaNo());
    	  
    	 	itemQna.setItemNo(parentReply.getItemNo());
    	 	itemQna.setParents(parentReply.getParents());
    	 	itemQna.setSequence(parentReply.getSequence() + 1);
    	 	itemQna.setDepth(parentReply.getDepth() + 1);
    	 	itemQna.setItemqnaContent(itemQnaContent)  ;
    	 
    	 
    	 itemQnaService.updateSeqeunce(itemQna);
    	 itemQnaService.writeReplyQna(itemQna);
    	  
    	 return "success";
    	 
    	 
      } else {
    	  
    	  return "fail";
      }
	
    
    
		  	
	  }
    
}
