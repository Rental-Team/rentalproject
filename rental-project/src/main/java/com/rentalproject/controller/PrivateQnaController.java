package com.rentalproject.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.service.PrivateQnaService;

@Controller
@RequestMapping(path = {"/privateboard"})
public class PrivateQnaController {

	@Autowired
	private PrivateQnaService privateQnaService;
	
    @GetMapping(path= {"/privateqnalist"}) //리스트
	public String list(Model model) {
		  
    	List<PrivateQnaDto>qnaBoardList = privateQnaService.listBoard();
    	model.addAttribute("qnaBoardList",qnaBoardList);
    	
    	
    	
		  return "privateboard/privateqnalist";
	  }
	 
    
    @GetMapping(path = {"/privateqnawrite"}) //롸이트
    public String showWriteForm() {
		 
		 return "privateboard/privateqnawrite";
	 }
	 
	 @PostMapping(path= {"/privateqnawrite"}) //롸이트,업로드
	 public String write(PrivateQnaDto privateqna, MultipartFile attach, HttpServletRequest req) {
		
		if(!attach.isEmpty()) {
			try {
			String uploadDir = req.getServletContext().getRealPath("/resources/upload");	
			attach.transferTo(new File(uploadDir, attach.getOriginalFilename()));
				
			}catch (Exception ex) {
				ex.printStackTrace();
			}		
			
		}
		 
		 
		 
		 
		 
		 System.out.println(privateqna); 
	
		privateQnaService.writeBoard(privateqna);
		
		 return "redirect:privateqnalist";
				 
	 }
	 @GetMapping(path= {"/privateqnadetail"}) //디테일
	 public String detail(@RequestParam(defaultValue ="-1") int qnaNo, Model model) {
		 
		if(qnaNo == -1) {
			return "redirect:privateqnalist";
		}
		 PrivateQnaDto privateqna = privateQnaService.findQnaBoardByQnaNo(qnaNo);
		 System.out.println(privateqna);
		
		 model.addAttribute("privateqna",privateqna);
		 
		 return "privateboard/privateqnadetail";
	 }
	 
}




