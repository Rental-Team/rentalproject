package com.rentalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.service.PrivateQnaService;

@Controller
@RequestMapping(path = {"/privateboard"})
public class PrivateQnaController {

	@Autowired
	private PrivateQnaService privateQnaService;
	
    @GetMapping(path= {"/privateqnalist"})
	public String list(Model model) {
		  
    	List<PrivateQnaDto>boardList = privateQnaService.listBoard();
    	model.addAttribute("boardList",boardList);
    	
    	
    	
		  return "privateboard/privateqnalist";
	  }
	 
    
    @GetMapping(path = {"/privateqnawrite"}) 
	 public String showWriteForm() {
		 
		 return "privateboard/privateqnawrite";
	 }
	 
	 @PostMapping(path= {"privateqnawrite"})
	 public String write(PrivateQnaDto privateqna) {
		
		System.out.println(privateqna); 
	
		privateQnaService.writeBoard(privateqna);
		
		 return "redirect:privateqnalist";
		
		
		 
	 }

}




