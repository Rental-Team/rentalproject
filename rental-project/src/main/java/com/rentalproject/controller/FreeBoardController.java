package com.rentalproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import com.rentalproject.common.Util;
import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.service.FreeBoardService;
import com.rentalproject.view.DownloadView;

@Controller
@RequestMapping(path = {"/freeboard"})
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	// 자유게시글 리스트 화면 불러오기 ( 전체 게시글 불러오기 )
	@GetMapping(path= {"/freeboardlist"})
	public String list(Model model) { 
		
		List<FreeBoardDto> freeBoardList = freeBoardService.listFreeBoard();
		 
		model.addAttribute("freeBoardList", freeBoardList);
		
		return "freeboard/freeboardlist";
	}
	
	
	// 자유게시글 작성 화면 불러오기
	@GetMapping(path= {"/freeboardwrite"})
	public String writeFreeBoardForm() {
		
		return "freeboard/freeboardwrite";
		
	}
	
	// 자유게시글 등록하기
	@PostMapping(path= {"/freeboardwrite"})
	
		public String writeFreeBoard(FreeBoardDto freeboard, MultipartFile attach, HttpServletRequest req) throws Exception {
		
		System.out.println("제목:" + freeboard.getFreeBoardTitle());
		System.out.println("내용:" + freeboard.getFreeBoardContent());
		
		// 파일업로드 처리
		String uploadAttachFile = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = handleUploadFile(attach, uploadAttachFile);
		freeboard.setFreeBoardAttachList(freeBoardAttachList);
		
		freeBoardService.writeFreeBoard(freeboard);
		
		return "redirect:freeboardlist";
		
	}
	
	// 자유게시글 첨부파일저장 
	private ArrayList<FreeBoardAttachDto> handleUploadFile(MultipartFile attach, String uploadAttachFile) {
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = new ArrayList<>();
			if (attach != null && !attach.isEmpty()) {
				try {
					String uploadFileName = Util.makeUniqueFileName(attach.getOriginalFilename());
					
					attach.transferTo(new File(uploadAttachFile, uploadFileName));   // 첨부파일 저장 코드 
					
					// 파일 정보를 DTO에 저장
					FreeBoardAttachDto freeBoardAttach = new FreeBoardAttachDto();
					freeBoardAttach.setAttachFileName(attach.getOriginalFilename());
					freeBoardAttach.setSavedFileName(uploadFileName);
					
					freeBoardAttachList.add(freeBoardAttach);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return freeBoardAttachList;
	}



	// 자유게시글 클릭 후 상세보기
	@GetMapping(path = {"/freeboarddetail"})
	public String detail(@RequestParam(defaultValue = "-1") int freeBoardNo, Model model) {
		
		if(freeBoardNo == -1) {  // 주소창에 detail로 바로 접근하지 못하게 함 
			return "redirect:freeboardlist";
		}
		
		FreeBoardDto freeboard = freeBoardService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
		if(freeboard == null) { // 조회된 글이 없을때 리스트로  
			return "redirect:freeboardlist";
		}
		
		model.addAttribute("freeBoard", freeboard);
		
		return "freeboard/freeboarddetail";
		
	}
	
		// 첨부파일 조회 및 다운로드하기
		@GetMapping(path = {"/download"})
		public View download(int attachNo, Model model) {
			
		// 첨부파일 조회하기	
		FreeBoardAttachDto freeBoardAttach = freeBoardService.selectFreeBoardAttachByAttachNo(attachNo);
		// 다운로드 처리하기  
		model.addAttribute("attach", freeBoardAttach);
		DownloadView downloadView = new DownloadView();
		
		return downloadView;
	}
	
	 
		// 자유게시글 수정하기 ( 자유게시글 상세보기 내용 불러오기 )
		@GetMapping(path = {"/freeboardedit"})
		public String showFreeBoardEditForm(@RequestParam(defaultValue = "-1") int freeBoardNo, 
											Model model) {
		
		FreeBoardDto freeboard = freeBoardService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
		
		if(freeboard == null) { // 조회된 글이 없을때 리스트로 
			
			return "redirect:freeboardlist";
		}
		 
		model.addAttribute("freeBoard", freeboard);
		
		return "freeboard/freeboardedit";
	}
	
	// 자유게시글 수정하기 ( 수정한 글 등록하기 )
		@PostMapping(path = {"/freeboardedit"})
		public String freeBoardEdit(FreeBoardDto freeboard, MultipartFile attach, HttpServletRequest req) {
		
		String uploadAttachFile = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = handleUploadFile(attach, uploadAttachFile);
		freeboard.setFreeBoardAttachList(freeBoardAttachList);
		
		// update 처리하기
		freeBoardService.editFreeBoard(freeboard);
		
		return "redirect:freeboardlist";
	}
	
	// 자유게시글 삭제하기
	@GetMapping(path = {"/freeboarddelete/{freeBoardNo}" })
	public String freeBoardDelete(@PathVariable("freeBoardNo") int freeBoardNo ) {
		
		freeBoardService.deleteFreeBoard(freeBoardNo);
		
		return "redirect:/freeboard/freeboardlist";	
	}
	
	
	
	  // 자유게시판 조회수 증가
		/*
		 * @GetMapping(path = {"/freeboardlist"}) public String
		 * FreeBoardviewCountUpdate(@RequestParam(name="freeBoardNo") int freeBoardNo,
		 * Model model) {
		 * 
		 * freeBoardService.updateFreeBoardviewCount(freeBoardNo);
		 * 
		 * model.addAttribute("freeBoardNo", freeBoardNo);
		 * 
		 * return "freeboard/freeboardlist"; }
		 */
	 

	
		
}

	

	


