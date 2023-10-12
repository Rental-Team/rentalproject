package com.rentalproject.controller;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.FreeBoardRecommandDto; 
import com.rentalproject.service.FreeBoardRecommandService; 

import lombok.Setter;

@Controller
@RequestMapping(path = {"/freeboard"})
public class FreeBoardRecommandController {
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardRecommandService freeBoardRecommandService;  
	
	
	@PostMapping("/freeBoard-recommand")
	@ResponseBody
	public String addFreeBoardRecommand (FreeBoardRecommandDto freeboardRecommand) { 
		
		int recommandresult = freeBoardRecommandService.addFreeBoardRecommand(freeboardRecommand); 
		
		return recommandresult + "";
	} 
}
