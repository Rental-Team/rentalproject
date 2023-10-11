package com.rentalproject.controller;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.FreeBoardReportDto; 
import com.rentalproject.service.FreeBoardReportService; 

import lombok.Setter;

@Controller
@RequestMapping(path = {"/freeboard"})
public class FreeBoardReportController {
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReportService freeBoardReportService;  
	
	
	@PostMapping("/freeBoard-report")
	@ResponseBody
	public String addFreeBoardReport (FreeBoardReportDto freeboardReport) { 
		
		int result = freeBoardReportService.addFreeBoardReport(freeboardReport); 
		
		return result + "";
	} 
}
