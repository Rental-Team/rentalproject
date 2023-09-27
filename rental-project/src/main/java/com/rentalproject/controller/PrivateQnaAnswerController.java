package com.rentalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.service.PrivateQnaAnswerService;
import com.rentalproject.service.PrivateQnaService;

@Controller
@RequestMapping(path= {"/privateboard"})
public class PrivateQnaAnswerController {

	@Autowired
	private PrivateQnaAnswerService privateQnaAnswerService; 
	
	@PostMapping(path ={"/write-answer"})  //답변 
	public String writeAnswer(PrivateQnaAnswerDto privateQnaAnswer){
	
	privateQnaAnswerService.writeAnswer(privateQnaAnswer);	
		
	System.out.println(privateQnaAnswer);
		
	return String.format("redirect:privateqnadetail?qnaNo=%d",privateQnaAnswer.getQnaNo());
}

	@PostMapping("/edit-answer")
	public String editAnswer(PrivateQnaAnswerDto privateQnaAnswer) {
	    privateQnaAnswerService.editAnswer(privateQnaAnswer);
	    return String.format("redirect:privateqnadetail?qnaNo=%d" , privateQnaAnswer.getQnaNo());
	}
}