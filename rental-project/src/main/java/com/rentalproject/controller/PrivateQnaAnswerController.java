package com.rentalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.service.PrivateQnaAnswerService;
import com.rentalproject.service.PrivateQnaService;

@Controller
@RequestMapping(path= {"/privateboard"})
public class PrivateQnaAnswerController {

	@Autowired
	private PrivateQnaAnswerService privateQnaAnswerService;
	
	@Autowired
	private PrivateQnaService privateQnaService;
	
	
	@GetMapping(path = {"/answer-list"})
	public String showAnswerList(int qnaNo, Model model) { 
		
		
		List<PrivateQnaAnswerDto> answers = privateQnaAnswerService.getAnswerListByQnaNo(qnaNo);
		model.addAttribute("answers" ,answers);
		
		
		return "privateboard/answer-list";
	}
	

	
	@PostMapping(path ={"/write-answer"})  //답변 
	public String writeAnswer(PrivateQnaAnswerDto privateQnaAnswer ,@RequestParam("qnaNo") int qnaNo,	@RequestParam(defaultValue = "-1") int pageNo){
																	
	privateQnaAnswerService.writeAnswer(privateQnaAnswer);	
	
	privateQnaService.updateAnswerStatus(qnaNo, true); // 답변여부 업데이트
	
	return String.format("redirect:privateqnadetail?qnaNo=%d&pageNo=%d", privateQnaAnswer.getQnaNo(), pageNo);

}
	
	
	@GetMapping("/edit-answer-form")
	public String showEditAnswerForm(@RequestParam(defaultValue = "-1") int qnaNo, @RequestParam(defaultValue = "-1") int pageNo, Model model) {
	    
	    model.addAttribute("qnaNo", qnaNo);
	    model.addAttribute("pageNo", pageNo);
	    
	   
	    return "privateboard/edit-answer-form";
	}
	
	
	
	

	@PostMapping("/edit-answer")
	public String editAnswer(PrivateQnaAnswerDto privateQnaAnswer, @RequestParam(defaultValue = "-1") int pageNo) {
	    privateQnaAnswerService.editAnswer(privateQnaAnswer);
	    
	    
	    
	    return String.format("redirect:privateqnadetail?qnaNo=%d&pageNo=%d", privateQnaAnswer.getQnaNo(), pageNo);

	}
	

	
}