package com.rentalproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.FreeBoardRecommandService;
import com.rentalproject.service.FreeBoardReportService;
import com.rentalproject.service.FreeBoardService;
import com.rentalproject.ui.ThePager2;
import com.rentalproject.view.DownloadView;

@Controller
@RequestMapping(path = {"/freeboard"})
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	@Autowired
	private FreeBoardReportService freeBoardReportService;
	@Autowired
	private FreeBoardRecommandService freeBoardRecommandService;
	
	// 자유게시글 리스트 화면 불러오기 ( 게시글 불러오기 )
	@GetMapping(path = {"/freeboardlist"})
	public String list(@RequestParam(defaultValue = "1") int pageNo,
	                   @RequestParam(defaultValue = "") String type,
	                   @RequestParam(defaultValue = "") String keyword, Model model,
	                   HttpSession session, HttpServletRequest request) {

	    int pageSize = 10;
	    int pagerSize = 5;
	    String linkUrl = "freeboardlist";
	    //int dataCount = freeBoardService.getFreeBoardCount();
	   
	    int searchDataCount = 0;
	    
	    int from = (pageNo - 1) * pageSize;
	    List<FreeBoardDto> freeBoardList;

	    if (type.isEmpty() && keyword.isEmpty()) {
	        // 전체 목록의 데이터 수
	    	searchDataCount = freeBoardService.getFreeBoardCount(); 
	    	 // 전체 목록 조회
	        freeBoardList = freeBoardService.listFreeBoardByPage(from, pageSize);
	    } else {
	        // 검색 결과의 데이터 수를 계산, 데이터 검색
	        if ("freeBoardTitle".equals(type)) {
	            searchDataCount = freeBoardService.getSearchByTitleCount(keyword);
	            freeBoardList = freeBoardService.selectSearchByTitle(keyword, from, pageSize);
	        } else if ("freeBoardContent".equals(type)) {
	            searchDataCount = freeBoardService.getSearchByContentCount(keyword);
	            freeBoardList = freeBoardService.selectSearchByContent(keyword, from, pageSize);
	        } else if ("memberId".equals(type)) {
	            searchDataCount = freeBoardService.getSearchByMemberIdCount(keyword);
	            freeBoardList = freeBoardService.selectSearchByMemeberId(keyword,from, pageSize);
	        } else {
	            searchDataCount = freeBoardService.getSearchFreeBoardCount(keyword);
	            freeBoardList = freeBoardService.selectSearchFreeBoard(keyword, from, pageSize);
	        }
	    }  

	    for (FreeBoardDto freeboard : freeBoardList) {
	        // 작성자 조회
	        String memberId = freeBoardService.getMemberId(freeboard.getFreeBoardNo());
	        freeboard.setMemberId(memberId);
	    }

	    model.addAttribute("pageNo", pageNo);
	    model.addAttribute("freeBoardList", freeBoardList);

	    ThePager2 pager2 = new ThePager2(searchDataCount, pageNo, pageSize, pagerSize, linkUrl, type, keyword);
	    model.addAttribute("pager2", pager2);

	    int memberNo = 0;
	    if (session.getAttribute("loginuser") != null) {
	        memberNo = ((MemberDto) session.getAttribute("loginuser")).getMemberNo();
	    }
	    model.addAttribute("memberNo", memberNo);

	    // 검색 결과를 모델에 추가
	    model.addAttribute("searchList", freeBoardList);

	    return "freeboard/freeboardlist";
	}
	
	
	// 자유게시글 작성 화면 불러오기
	@GetMapping(path= {"/freeboardwrite"})
	public String writeFreeBoardForm(HttpSession session) { 
		
//		if (session.getAttribute("loginuser") == null) { // 게시글 작성하기 버튼 눌렀을 때 로그인 안되어 있으면 로그인 화면으로 
//			return "redirect:/account/login";
//		}
		
		return "freeboard/freeboardwrite";
		
	}
	
	// 자유게시글 등록하기
	@PostMapping(path= {"/freeboardwrite"})
	
		public String writeFreeBoard(FreeBoardDto freeboard, MultipartFile attach, 
									 HttpServletRequest req) throws Exception { 
		

		// 파일업로드 처리
		String uploadAttachFile = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = handleUploadFile(attach, uploadAttachFile);
		freeboard.setFreeBoardAttachList(freeBoardAttachList);
		
		HttpSession session = req.getSession();   // 작성자로 글 등록하기 
		int memberNo = ( (MemberDto) session.getAttribute("loginuser")).getMemberNo();
		freeboard.setMemberNo(memberNo); 

		
		freeBoardService.writeFreeBoard(freeboard);
		
		return String.format("redirect:freeboardlist?memberNo=%d", freeboard.getMemberNo());
		
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
	public String detail(@RequestParam(defaultValue = "-1") int freeBoardNo,
						 @RequestParam(defaultValue = "-1") int pageNo,
						 HttpServletRequest req, HttpServletResponse resp,
						 Model model) {
		
		if(freeBoardNo == -1 || pageNo == -1) {  // 주소창에 detail로 바로 접근하지 못하게 함 
			return "redirect:freeboardlist";
		}
		
		FreeBoardDto freeboard = freeBoardService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
		if(freeboard == null) { // 조회된 글이 없을때 리스트로  
			return "redirect:freeboardlist";
		} 
		
		 String memberId = freeBoardService.getMemberId(freeBoardNo);
		 freeboard.setMemberId(memberId);
		 
		 String memberImage = freeBoardService.getMemberImage(freeBoardNo);
		 freeboard.setMemberImage(memberImage);
		 
		int count = freeBoardReportService.reportcount(freeBoardNo);
		int recommandCount = freeBoardRecommandService.recommandcount(freeBoardNo);
		
		String coockieName = "showed_freeBoard_" + freeBoardNo;
		boolean hasViewed = false;
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(coockieName.equals(cookie.getName())) {
					hasViewed = true;
					break;
				}
			}
		}
		
		if (!hasViewed) {
			freeBoardService.updateFreeBoardviewCount(freeBoardNo);
			
			Cookie viewedFreeBoardCookie = new Cookie(coockieName,"1");
			viewedFreeBoardCookie.setMaxAge(24*60*60);
			viewedFreeBoardCookie.setPath("/");
			resp.addCookie(viewedFreeBoardCookie);
		}
		
		
		model.addAttribute("recommandCount",recommandCount);
		model.addAttribute("count", count);
		model.addAttribute("freeBoard", freeboard);
		model.addAttribute("pageNo", pageNo); 
		
 
		//freeBoardService.updateFreeBoardviewCount(freeBoardNo);  // 조회수 증가 ;
		
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
											@RequestParam(defaultValue = "-1") int pageNo,
											Model model) {
			
			if (freeBoardNo == -1 || pageNo == -1) {
				return "redirect:freeboardlist";
			}
			
		FreeBoardDto freeboard = freeBoardService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
			if (freeboard == null) {
				return "redirect:freeboardlist";
			}
		
		model.addAttribute("freeBoard", freeboard);
		model.addAttribute("pageNo", pageNo);
		
		return "freeboard/freeboardedit";
	}
	
	// 자유게시글 수정하기 ( 수정한 글 등록하기 )
		@PostMapping(path = {"/freeboardedit"})
		public String freeBoardEdit(FreeBoardDto freeboard, MultipartFile attach, HttpServletRequest req,
									@RequestParam(defaultValue = "-1") int pageNo) {
		
			if (pageNo < 1) {
				return "redirect:freeboardlist";
			}
			
		String uploadAttachFile = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = handleUploadFile(attach, uploadAttachFile);
		freeboard.setFreeBoardAttachList(freeBoardAttachList);
		
		// update 처리하기
		freeBoardService.editFreeBoard(freeboard);
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d&pageNo=%d", freeboard.getFreeBoardNo(), pageNo);
	}
	
	// 자유게시글 삭제하기
	@GetMapping(path = {"/freeboarddelete/{freeBoardNo}" })
	public String freeBoardDelete(@PathVariable("freeBoardNo") int freeBoardNo,
								  @RequestParam(defaultValue = "-1") int pageNo) {
		
		if (pageNo == -1) {
			return "redirect:/freeboard/freeboardlist";
		}
		freeBoardService.deleteFreeBoard(freeBoardNo);
		return String.format("redirect:/freeboard/freeboardlist?pageNo=%d", pageNo);
	}
}		

	 
	 
		
	



	
	

	


