package com.rentalproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalproject.common.Util;
import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.PrivateQnaAnswerDto;
import com.rentalproject.dto.PrivateQnaAttachDto;
import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.service.AdminService;
import com.rentalproject.service.OrderServcie;
import com.rentalproject.service.PrivateQnaAnswerService;
import com.rentalproject.service.PrivateQnaService;
import com.rentalproject.ui.ThePager;
import com.rentalproject.view.DownloadView;
import com.rentalproject.view.DownloadView_PrivateQna;

import net.coobird.thumbnailator.Thumbnails;


@Controller
@RequestMapping("/admin")
public class AdminController {
		
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderServcie orderServcie;
	
	
	@Autowired
	private PrivateQnaService privateQnaService;
	
	@Autowired
	private PrivateQnaAnswerService PrivateQnaAnswerService;
	
	
	@GetMapping("/home")
	public void adminHome(Model model) throws Exception {
		List<MemberDto> memberList = adminService.MemberList();
		
		model.addAttribute("memberList", memberList);
	}
	
	// 회원 리스트
	@GetMapping("/member/list")
	public String getMemberList(@RequestParam(defaultValue = "1") int pageNo, Model model) throws Exception {
		
		// List<MemberDto> memberList = adminService.MemberList();
		
		int pageSize = 30;
		int pagerSize = 5;
		String linkUrl = "list";
		int dataCount = adminService.getMemberCount();
		
		int from = (pageNo -1)*pageSize;
		List<MemberDto> memberList = adminService.listMemberByPage(from, pageSize);
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl);
		
		model.addAttribute("memberList", memberList); 
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo); // 페이지 번호를 jsp에 전송
		
		return "admin/member/list";
	}
	
	// 회원 상세 조회
	@GetMapping("/member/detail")
	public String getMemberDetail(@RequestParam(defaultValue = "-1") int memberNo, Model model,
								  @RequestParam(defaultValue = "-1") int pageNo) {
		if (memberNo == -1 || pageNo == -1) { // 글번호와 페이지 번호가 -1 이면 (무단침입) 그냥 리스트 창으로 돌아가세요(글 번호가 요청에 포함되지 않은 경우) , 
			return "redirect:/admin/member/list"; //경로는 뛰어쓰기 금지 redirect : list (x)
		}
		
		MemberDto member = adminService.selectMemberDetail(memberNo);
		
		if (member == null) {
			return "redirect:/admin/member/list";
		}
		
		model.addAttribute("member", member);
		model.addAttribute("pageNo", pageNo);
		
		return "admin/member/detail";
	}

	
	@GetMapping("/item/list")
	public String list(@RequestParam(defaultValue = "1")int pageNo, Model model) {
		
		
		//List<ItemDto> list = itemService.getList();
		//log.info(list);
		
		int pageSize = 5;
		int pagerSize = 5;
		String linkUrl = "list";
		int dataCount = adminService.getItemCount();
		
		int from = (pageNo - 1)*pageSize;
		List<ItemDto> itemList = adminService.listItemByPage(from, pageSize);
		
		
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl);
		
		//model.addAttribute("list", list);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "admin/item/list";
	}
		
	
	// 상품 등록
	@GetMapping("/item/write")
	public String itemWriteForm(Model model) throws Exception {
		
		ObjectMapper objm = new ObjectMapper();
		List<CategoryDto> list = adminService.cateList();
		String cateList = objm.writeValueAsString(list);
		
		model.addAttribute("cateList", cateList);
		
		return "admin/item/write";
	}
	
	// 상품 등록
	@PostMapping("/item/write")
	public String write(ItemDto item,@RequestParam("attach") MultipartFile[] attachs, HttpServletRequest req) {

		// 아이템 업로드
		//log.info("register: " + item);
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<ItemAttachDto> attachList = handleUploadFile(attachs, uploadDir);
		item.setItemAttachList(attachList);

		// 상품 등록
		adminService.writeItem(item);
	

		return "redirect:/admin/item/list";
	}
	
	// 첨부파일 
	private ArrayList<ItemAttachDto> handleUploadFile(MultipartFile[] attachs, String uploadDir) {
		ArrayList<ItemAttachDto> attachList = new ArrayList<>();
		for (MultipartFile attach : attachs) {
		if (!attach.isEmpty()) {
			try {
				String savedFileName = Util.makeUniqueFileName(attach.getOriginalFilename());

				attach.transferTo(new File(uploadDir, savedFileName)); // 파일을 컴퓨터에 저장
				
				// 썸네일 생성
				File thumbnailFile = new File(uploadDir, "thumbnail_" + savedFileName);
				Thumbnails.of(new File(uploadDir, savedFileName))
							.size(100, 100) // 썸네일 크기
							.toFile(thumbnailFile);
				
				
				// 파일 정보를 dto에 저장
				ItemAttachDto itemAttach = new ItemAttachDto();
				itemAttach.setUserFileName(attach.getOriginalFilename());
				itemAttach.setSavedFileName(savedFileName);				
				attachList.add(itemAttach);
				
				
//				// 썸네일 이미지 정보 저장
//				ItemAttachDto thumbnailAttach = new ItemAttachDto();
//				thumbnailAttach.setUserFileName("thumbnail_" + attach.getOriginalFilename());
//				thumbnailAttach.setSavedFileName("thumbnail_" + savedFileName);
//				attachList.add(thumbnailAttach);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		}
		return attachList;
	}

	// 상품 상세
	@GetMapping("/item/detail")
	public String detail(@RequestParam(defaultValue = "-1") int itemNo, 
			 			@RequestParam(defaultValue="-1") int pageNo, 
			 			Model model) {
		
		if (itemNo == -1 || pageNo == -1) { // 글 번호가 요청에 포함되지 않은 경우
			return "redirect:/admin/item/list";
		}
		
		ItemDto item = adminService.itemDetail(itemNo);
		
		if (item == null) { // 조회된 상품이 없는 경우
			return "redirect:/admin/item/list";
		}
		
		model.addAttribute("item", item);
		model.addAttribute("pageNo", pageNo);
		
		return "admin/item/detail";
	}
	
	// 다운로드
	@GetMapping( "/download" )
	public View download(int attachNo, Model model) {
			
		// 1. 첨부파일 조회
		ItemAttachDto attach = adminService.findItemAttachByAttachNo(attachNo);
			
		// 2. 다운로드 처리
		model.addAttribute("attach", attach);	// View에서 사용할 수 있도록 데이터 저장
		DownloadView downloadView = new DownloadView();
		
		// return "download"; // "/WEB-INF/views/" + download + ".jsp"
		return downloadView;
	}

	// 상품 수정 폼
	@GetMapping("/item/edit")
	public String editForm(@RequestParam("itemNo") int itemNo,
							@RequestParam(defaultValue = "-1") int pageNo,
							Model model) {
		
		if (itemNo == -1 || pageNo == -1) {
			return "redirect:list";
		}
		
		ItemDto item = adminService.itemDetail(itemNo);
		
		//log.info(item);
		
		if (item == null) {
			return "redirect:item/list";
		}
		
		
		
		model.addAttribute("item", item);
		model.addAttribute("pageNo", itemNo);
		
		
		return "admin/item/edit";
		
	}
	
	// 상품 수정
	@PostMapping("/item/edit")
	public String edit(ItemDto item, @RequestParam("attach") MultipartFile[] attachs, HttpServletRequest req, 
			  @RequestParam(defaultValue = "-1") int pageNo ) {
		
		if( pageNo < 1) {
			return "redirect:list";
		}
		
		// 파일 업로드 처리
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<ItemAttachDto> attachList = handleUploadFile(attachs, uploadDir);
		item.setItemAttachList(attachList);
		
		// 상품 수정 처리
		adminService.editItem(item);
		
		return String.format("redirect:detail?itemNo=%d&pageNo=%d", item.getItemNo(), pageNo);
	}
	
	// 상품 삭제
	@GetMapping("/delete/{itemNo}")
	public String delete(@PathVariable("itemNo") int itemNo,
						  @RequestParam(defaultValue = "-1") int pageNo,
						  RedirectAttributes rttr) {
		
		if(pageNo == -1) {
			return "redirect:/admin/item/list";
		}
		
		adminService.deleteItem(itemNo);
		
		return String.format("redirect:/item/list?pageNo=%d", pageNo);
	}
	
	/////////////////////
	
	@GetMapping(path= {"/notice/list"})
	public String list(Model model) {
		
		
		
		List<NoticeDto> List = adminService.listNotice();
		
		
		 
		model.addAttribute("noticeList", List);
		
		return "/admin/notice/list";
	}
	
	@GetMapping("/notice/write")
    public String showNoticeWriteForm() {
        return "/admin/notice/write"; 
    }
	
	@PostMapping(path= {"/notice/write"})
	
		public String writeNotice(NoticeDto notice) throws Exception {
		
		
		
		adminService.writeNotice(notice);
		
		return "redirect:/admin/notice/list";
		
	}
	
	@GetMapping(path = {"/notice/detail"})
	public String noticedetail(@RequestParam(defaultValue = "-1") int noticeNo, Model model) {
		
		
		NoticeDto notice = adminService.findNoticeByNoticeNo(noticeNo);
		
		
	    
	    adminService.updateviewCount(noticeNo); // 조회수
	    
		
		model.addAttribute("notice", notice);
		
		return "/admin/notice/detail";
		
	}
	
	
	 
	@GetMapping(path = {"/notice/edit"})
	public String showNoticeEditForm(@RequestParam(defaultValue = "-1") int noticeNo, 
											Model model) {
		
		NoticeDto notice = adminService.findNoticeByNoticeNo(noticeNo);
		
		
		 
		model.addAttribute("notice", notice);
		
		return "/admin/notice/edit";
	}
	
	@PostMapping(path = {"/notice/edit"})
	public String noticeEdit(NoticeDto notice, HttpServletRequest req) {
		
		
		adminService.editNotice(notice);
		
		return "redirect:/admin/notice/list";
	}
	
	// 관리자에서 주문 리스트 띄우기
	@GetMapping("/rental/rentalList")
	public String showRentalList(Model model) {
		
	    List<RentalOrderPageDto> orderList = orderServcie.orderList();
	    
	    model.addAttribute("orderList", orderList);
		
		return "/admin/rental/rentalList";
	}
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	
	
	@GetMapping(path = {"/privateboard/privateqnalist"}) // 1대1문의 관리자 1대1 리스트
	public String list(
	        @RequestParam(defaultValue = "1") int pageNo,
	        @RequestParam(value = "qnaNo", required = false) Integer qnaNo,
	        Model model,
	        HttpSession session) {

		MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");

		int memberNo = -1;
		
		if (loginuser != null) {
			memberNo = loginuser.getMemberNo();
			
			
		}
	    
	    
		int pageSize = 10;
	    if (pageSize < 1) {
	        pageSize = 10; // 기본 페이지 크기를 설정하세요.
	    }
	    
	    // 페이지 번호 체크
	    if (pageNo < 1) {
	        pageNo = 1;
	    }


		
	    int pagerSize = 5;
	    String linkUrl = "privateqnalist";

	    int dataCount;
	   
	    List<PrivateQnaDto> qnaBoardList;
	   
	    
	    if (qnaNo != null) {
	        qnaBoardList = privateQnaService.searchByQnaNo(qnaNo);
	
	        dataCount = qnaBoardList.size(); // 검색 결과의 크기를 데이터 카운트로 설정
	    
	      

	    } else {
	        if (memberNo == 17) {
	            dataCount = privateQnaService.getPrivateQnaCount();
	            int from = (pageNo - 1) * pageSize;
	            qnaBoardList = privateQnaService.listBoard(from, pageSize);
	        } else {
	            dataCount = privateQnaService.getPrivateQnaCountByMemberNo(memberNo);
	            int from = (pageNo - 1) * pageSize;
	            qnaBoardList = privateQnaService.listBoardByMemberNo(memberNo, from, pageSize);
	        }
	    }



		/////// 작성자 조회 부분 
		for (PrivateQnaDto privateqna : qnaBoardList) {
			String memberId = privateQnaService.getMemberIdByQnaNo(privateqna.getQnaNo());
			privateqna.setMemberId(memberId);
		}
		//////////



		//////////////////////////////////////////////  답변 여부 
		for (PrivateQnaDto privateqna : qnaBoardList) {
			boolean answered = privateQnaService.getAnswerStatus(privateqna.getQnaNo());
			privateqna.setAnswered(answered);
		}
		//////////////////////////////////////////	 

		ThePager pager =new ThePager(dataCount,pageNo,pageSize,pagerSize,linkUrl);
			
		model.addAttribute("qnaBoardList", qnaBoardList);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("memberNo", memberNo); // 미답변 목록 조회 하기 할떄 memberNo17번만 사용해야해서 필요 
		if (session.getAttribute("loginuser") == null) { 

		return "redirect:/account/login";

		}

		return "/admin/privateboard/privateqnalist";
	}


	@GetMapping(path= {"/privateboard/privateqnadetail"}) // 관리자 1대1 문의 디테일
	public String detail(@RequestParam(defaultValue ="-1") int qnaNo,
						 @RequestParam(defaultValue = "-1") int pageNo,
						 Model model ,HttpSession session, HttpServletRequest request) {

		
		if(qnaNo == -1) {
			return "redirect:privateqnalist";
		}
		PrivateQnaDto privateqna = privateQnaService.findQnaBoardByQnaNo(qnaNo);
		



		int memberNo = -1;
		MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");
		
		if (loginuser != null) {
			memberNo = loginuser.getMemberNo();
			
			
		}



		//  privateqna 객체에 로그인한 사용자의 회원 번호를 추가
		privateqna.setMemberNo(memberNo);
		/////

		 //<model로 변경>
		  request.setAttribute("memberNo", memberNo); // 필요함 privateqnadetail JSP 페이지에서
		  
		
		String memberId = privateQnaService.getMemberIdByQnaNo(qnaNo); //qnaNo에 해당하는 회원의 아이디를 가져와 privateqna 객체에 설정//디테일에서도 작성자 볼수있게 
		privateqna.setMemberId(memberId);


		///////////////////////////////////////////////// 
		model.addAttribute("privateqna",privateqna);
		model.addAttribute("pageNo",pageNo);
		return "/admin/privateboard/privateqnadetail";
	}
	
	
	@PostMapping(path ={"/privateboard/write-answer"})  //1대1문의 답변 
	public String writeAnswer(PrivateQnaAnswerDto privateQnaAnswer ,@RequestParam("qnaNo") int qnaNo,	@RequestParam(defaultValue = "-1") int pageNo){
																	
	PrivateQnaAnswerService.writeAnswer(privateQnaAnswer);	
	
	privateQnaService.updateAnswerStatus(qnaNo, true); // 답변여부 업데이트
	
	return String.format("redirect:privateqnadetail?qnaNo=%d&pageNo=%d", privateQnaAnswer.getQnaNo(), pageNo);

}
	
	
	@GetMapping("/privateboard/edit-answer-form") //1대1문의 답변 수정 
	public String showEditAnswerForm(@RequestParam(defaultValue = "-1") int qnaNo, @RequestParam(defaultValue = "-1") int pageNo, Model model) {
	    
	    model.addAttribute("qnaNo", qnaNo);
	    model.addAttribute("pageNo", pageNo);
	    
	   
	    return "privateboard/edit-answer-form";
	
	
	
	
	}
	
	@PostMapping("/privateboard/edit-answer") // 1대1문의 답변 수정 
	public String editAnswer(PrivateQnaAnswerDto privateQnaAnswer, @RequestParam(defaultValue = "-1") int pageNo) {
	    PrivateQnaAnswerService.editAnswer(privateQnaAnswer);
	    
	    
	    
	    return String.format("redirect:privateqnadetail?qnaNo=%d&pageNo=%d", privateQnaAnswer.getQnaNo(), pageNo);

	}
	
		//1대1 문의 게시판 첨부파일 조회 및 다운로드하기 
		@GetMapping(path = {"/privateboard/download"})
		public View downloadQna(int attachNo, Model model) {
			
			
			
			// 1대1 문의 첨부파일 조회 //
			PrivateQnaAttachDto privateQnaAttach = privateQnaService.selectPrivateQnaAttachByAttachNo(attachNo);
			
			
			
			
			model.addAttribute("attach", privateQnaAttach);
			DownloadView_PrivateQna downloadView = new DownloadView_PrivateQna();
			
			return downloadView;
			
		}
	
		//1대1문의 미답변 목록  
		@GetMapping(path = {"/privateboard/unanswer-list"})
		public String showUnAnswerlist(Model model,HttpSession session , @RequestParam(defaultValue = "1") int pageNo) {
		
			int pageSize = 10 ;
			int pagerSize =5;
			String linkurl = "unanswer-list";
			int dataCount = privateQnaService.getUnanswerListCount();
			
			int from = (pageNo -1) * pageSize;
			
			
			
			
			MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");	
			
			int memberNo = -1;
			
			if (loginuser != null) {
				memberNo = loginuser.getMemberNo();
				
				
			}
		
			if (memberNo != 17) {
		      
		        return "redirect:/privateboard/privateqnalist"; 
		    }


			List<PrivateQnaDto> unAnswer = privateQnaService.unAnswerlist(from , pageSize);


			for (PrivateQnaDto privateqna : unAnswer) {
				String memberId = privateQnaService.getMemberIdByQnaNo(privateqna.getQnaNo());
				privateqna.setMemberId(memberId);
			}
		
			ThePager pager = new ThePager(dataCount, pageNo, pageSize , pagerSize , linkurl);
			
				model.addAttribute("unAnswer",unAnswer);
				model.addAttribute("memberNo", memberNo);
				model.addAttribute("pager",pager);
				return "/admin/privateboard/unanswer-list";
	        	
		
		}
	
}