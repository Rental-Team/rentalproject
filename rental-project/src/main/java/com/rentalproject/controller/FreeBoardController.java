package com.rentalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.service.FreeBoardService;

@Controller
@RequestMapping(path = {"/freeboard"})
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	// 자유게시글 리스트 화면 불러오기 ( 전체 게시글 불러오기 )
	@GetMapping(path= {"/list"})
	public String list(Model model) {
		
		List<FreeBoardDto> freeBoardList = freeBoardService.listFreeBoard();
		
		
		model.addAttribute("freeBoardList", freeBoardList);
		
		return "freeboard/list";
	}
	
	
	// 자유게시글 작성 화면 불러오기
	@GetMapping(path= {"/write"})
	public String writeFreeBoardForm() {
		
		return "freeboard/write";
		
	}
	
	// 자유게시글 등록하기
	@PostMapping(path= {"/write"})
	
		public String writeFreeBoard(FreeBoardDto freeboard, HttpServletRequest req) throws Exception {
		
		System.out.println("제목:" + freeboard.getFreeBoardTitle());
		System.out.println("내용:" + freeboard.getFreeBoardContent());
		
		freeBoardService.writeFreeBoard(freeboard);
		
		return "redirect:list";
		
	}
	
	// 자유게시글 클릭 후 상세보기
	@GetMapping(path = {"/detail"})
	public String detail(@RequestParam(defaultValue = "-1") int freeBoardNo, Model model) {
		
		if(freeBoardNo == -1) {  // 주소창에 detail로 바로 접근하지 못하게 함
			
			return "redirect:list";
		}
		
		FreeBoardDto freeboard = freeBoardService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
		if(freeboard == null) { // 조회된 글이 없을때 리스트로 
			
			return "redirect:list";
		}
		model.addAttribute("freeBoard", freeboard);
		
		return "freeboard/detail";
		
	}
	
		
}

	

	


