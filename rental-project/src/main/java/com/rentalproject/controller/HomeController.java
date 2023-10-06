package com.rentalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rentalproject.dto.NoticeDto;
import com.rentalproject.service.NoticeService;

@Controller
public class HomeController {
	public NoticeService noticeService;
	
	@Autowired
	public HomeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
     @GetMapping(path = {"/", "/home"})
     public String home(Model model) {
    
    	
    	    
    	 List<NoticeDto> noticeList = noticeService.listNotice();
    	 
    	 
    	 model.addAttribute("noticeList", noticeList);
    	 
    	 
    	
    	 return "home";
     }
     
     
}
