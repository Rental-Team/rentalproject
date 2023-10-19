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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalproject.common.Util;
import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.service.AdminService;
import com.rentalproject.service.OrderServcie;
import com.rentalproject.ui.ThePager;
import com.rentalproject.ui.ThePager2;
import com.rentalproject.view.DownloadView;

import net.coobird.thumbnailator.Thumbnails;


@Controller
@RequestMapping("/admin")
public class AdminController {
		
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderServcie orderServcie;
	
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
	

	// 자유게시글 리스트 화면 불러오기 ( 게시글 불러오기 )
	@GetMapping(path = {"freeboard/freeboardlist"})
	public String list(@RequestParam(defaultValue = "1") int pageNo,
	                   @RequestParam(defaultValue = "") String type,
	                   @RequestParam(defaultValue = "") String keyword, Model model,
	                   HttpSession session, HttpServletRequest request) {

	    int pageSize = 10;
	    int pagerSize = 5;
	    String linkUrl = "freeboardlist";
	    //int dataCount = adminService.getFreeBoardCount();
	   
	    int searchDataCount = 0;
	    
	    int from = (pageNo - 1) * pageSize;
	    List<FreeBoardDto> freeBoardList;

	    if (type.isEmpty() && keyword.isEmpty()) {
	        // 전체 목록의 데이터 수
	    	searchDataCount = adminService.getFreeBoardCount(); 
	    	 // 전체 목록 조회
	        freeBoardList = adminService.listFreeBoardByPage(from, pageSize);
	    } else {
	        // 검색 결과의 데이터 수를 계산, 데이터 검색
	        if ("freeBoardTitle".equals(type)) {
	            searchDataCount = adminService.getSearchByTitleCount(keyword);
	            freeBoardList = adminService.selectSearchByTitle(keyword, from, pageSize);
	        } else if ("freeBoardContent".equals(type)) {
	            searchDataCount = adminService.getSearchByContentCount(keyword);
	            freeBoardList = adminService.selectSearchByContent(keyword, from, pageSize);
	        } else if ("memberId".equals(type)) {
	            searchDataCount = adminService.getSearchByMemberIdCount(keyword);
	            freeBoardList = adminService.selectSearchByMemeberId(keyword,from, pageSize);
	        } else {
	            searchDataCount = adminService.getSearchFreeBoardCount(keyword);
	            freeBoardList = adminService.selectSearchFreeBoard(keyword, from, pageSize);
	        }
	    }  

	    for (FreeBoardDto freeboard : freeBoardList) {
	        // 작성자 조회
	        String memberId = adminService.getMemberId(freeboard.getFreeBoardNo());
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

	    return "admin/freeboard/freeboardlist";
	}
	
	
	
	// 자유게시글 작성 화면 불러오기
	@GetMapping(path= {"freeboard/freeboardwrite"})
	public String writeFreeBoardForm(HttpSession session) { 
		
		if (session.getAttribute("loginuser") == null) { // 게시글 작성하기 버튼 눌렀을 때 로그인 안되어 있으면 로그인 화면으로 
			return "redirect:/account/login";
		}
		
		return "admin/freeboard/freeboardwrite";
		
	}
	
	// 자유게시글 등록하기
	@PostMapping(path= {"freeboard/freeboardwrite"})
	
		public String writeFreeBoard(FreeBoardDto freeboard, MultipartFile attach, 
									 HttpServletRequest req) throws Exception { 
		

		// 파일업로드 처리
		String uploadAttachFile = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = handleUploadFile(attach, uploadAttachFile);
		freeboard.setFreeBoardAttachList(freeBoardAttachList);
		
		HttpSession session = req.getSession();   // 작성자로 글 등록하기 
		int memberNo = ( (MemberDto) session.getAttribute("loginuser")).getMemberNo();
		freeboard.setMemberNo(memberNo); 

		
		adminService.writeFreeBoard(freeboard);
		
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
	@GetMapping(path = {"freeboard/freeboarddetail"})
	public String freeboardDetail(@RequestParam(defaultValue = "-1") int freeBoardNo,
						 @RequestParam(defaultValue = "-1") int pageNo,
						 Model model) {
		
		if(freeBoardNo == -1 || pageNo == -1) {  // 주소창에 detail로 바로 접근하지 못하게 함 
			return "redirect:freeboardlist";
		}
		
		FreeBoardDto freeboard = adminService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
		if(freeboard == null) { // 조회된 글이 없을때 리스트로  
			return "redirect:freeboardlist";
		} 
		
		 String memberId = adminService.getMemberId(freeBoardNo);
		 freeboard.setMemberId(memberId);
		 
		 String memberImage = adminService.getMemberImage(freeBoardNo);
		 freeboard.setMemberImage(memberImage);
		 
		//int count = freeBoardReportService.reportcount(freeBoardNo);
		//int recommandCount = freeBoardRecommandService.recommandcount(freeBoardNo);
		
		//model.addAttribute("recommandCount",recommandCount);
		//model.addAttribute("count", count);
		model.addAttribute("freeBoard", freeboard);
		model.addAttribute("pageNo", pageNo); 
		
 
		adminService.updateFreeBoardviewCount(freeBoardNo);  // 조회수 증가 ;
		
		return "admin/freeboard/freeboarddetail";
		
	}  
	
		// 첨부파일 조회 및 다운로드하기
		@GetMapping(path = {"/freeboardDownload"})
		public View freeboardDownload(int attachNo, Model model) {
			
		// 첨부파일 조회하기	
		FreeBoardAttachDto freeBoardAttach = adminService.selectFreeBoardAttachByAttachNo(attachNo);
		// 다운로드 처리하기  
		model.addAttribute("attach", freeBoardAttach);
		DownloadView downloadView = new DownloadView();
		
		return downloadView;
	}
	
	 
		// 자유게시글 수정하기 ( 자유게시글 상세보기 내용 불러오기 )
		@GetMapping(path = {"freeboard/freeboardedit"})
		public String showFreeBoardEditForm(@RequestParam(defaultValue = "-1") int freeBoardNo, 
											@RequestParam(defaultValue = "-1") int pageNo,
											Model model) {
			
			if (freeBoardNo == -1 || pageNo == -1) {
				return "redirect:freeboardlist";
			}
			
		FreeBoardDto freeboard = adminService.findFreeBoardByFreeBoardNo(freeBoardNo);
		
			if (freeboard == null) {
				return "redirect:freeboardlist";
			}
		
		model.addAttribute("freeBoard", freeboard);
		model.addAttribute("pageNo", pageNo);
		
		return "admin/freeboard/freeboardedit";
	}
	
	// 자유게시글 수정하기 ( 수정한 글 등록하기 )
		@PostMapping(path = {"freeboard/freeboardedit"})
		public String freeBoardEdit(FreeBoardDto freeboard, MultipartFile attach, HttpServletRequest req,
									@RequestParam(defaultValue = "-1") int pageNo) {
		
			if (pageNo < 1) {
				return "redirect:freeboardlist";
			}
			
		String uploadAttachFile = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<FreeBoardAttachDto> freeBoardAttachList = handleUploadFile(attach, uploadAttachFile);
		freeboard.setFreeBoardAttachList(freeBoardAttachList);
		
		// update 처리하기
		adminService.editFreeBoard(freeboard);
		
		return String.format("redirect:freeboarddetail?freeBoardNo=%d&pageNo=%d", freeboard.getFreeBoardNo(), pageNo);
	}
	
	// 자유게시글 삭제하기
	@GetMapping(path = {"freeboard/freeboarddelete/{freeBoardNo}" })
	public String freeBoardDelete(@PathVariable("freeBoardNo") int freeBoardNo,
								  @RequestParam(defaultValue = "-1") int pageNo) {
		
		if (pageNo == -1) {
			return "redirect:/freeboard/freeboardlist";
		}
		adminService.deleteFreeBoard(freeBoardNo);
		return String.format("redirect:/admin/freeboard/freeboardlist?pageNo=%d", pageNo);
	}
		
		
	// 신고된 게시글 조회 ( 관리자만 가능한 기능 )
	@GetMapping("freeboard/reported-List") 
	public String reportlist(@RequestParam(defaultValue = "1") int pageNo, FreeBoardDto freeboard,
							 Model model, HttpSession session, HttpServletRequest request) { 
		
		
		int memberNo = ((MemberDto) session.getAttribute("loginuser")).getMemberNo();
	    request.setAttribute("memberNo", memberNo);
	    
		if (memberNo == 17) {
	        List<FreeBoardDto> reportList = adminService.selectReportedFreeBoard();
	        
	        for (FreeBoardDto freeboard1 : reportList ) {  // 작성자 조회
				String memberId = adminService.getMemberId(freeboard1.getFreeBoardNo());
				freeboard1.setMemberId(memberId);
			} 
	        
	        model.addAttribute("memberNo",memberNo);
	        model.addAttribute("reportList", reportList); 
			model.addAttribute("pageNo", pageNo);
			
	        return "admin/freeboard/reported-list";
	    } else { 
	        return "redirect:admin/freeboard/freeboardlist";
	    }  
	}
	
	@GetMapping(path= {"freeboard/review-list"})   // ajax로 댓글 리스트 보기 기능 구현
	public String ShowReviewList(int freeBoardNo, Model model) {
		List<FreeBoardReviewDto> freeBoardReviews = adminService.getReviewListByFreeBoardNo(freeBoardNo);
		model.addAttribute("freeBoardReviews", freeBoardReviews);
		
		
		return "admin/freeboard/review-list";
	}
	
	
	@PostMapping(path = {"freeboard/write-freeboard-review"})   // 자유게시글 댓글 쓰기 
	@ResponseBody
	public String wrtieFreeBoardReview(FreeBoardReviewDto freeBoardReview,HttpSession session,
									   @RequestParam (defaultValue="-1") int pageNo) {
		  
		if (session.getAttribute("loginuser") == null) { // 댓글 작성하기 버튼 눌렀을 때 로그인 안되어 있으면 로그인 화면으로 
			return "fail";
		}
		
		adminService.WriteFreeBoardReview(freeBoardReview); 
		
		return "success"; 
	}
	
	@GetMapping(path = {"freeboard/delete-reply"}) // 자유게시글 댓글 삭제 
	@ResponseBody
	public String deleteFreeBoardReview(int freeBoardReplyNo) {
		
		adminService.deleteFreeBoardReview(freeBoardReplyNo);
		
		return "success";
	}
	
	@PostMapping(path = {"freeboard/edit-reply"}) // 자유게시글 댓글 수정
	@ResponseBody
	public String editFreeBoardReview(FreeBoardReviewDto freeBoardReview) { 
		 
		adminService.editFreeBoardReview(freeBoardReview);
		
		return "success";
	} 
	
	@PostMapping(path = {"freeboard/write-rereply"}) // 자유게시글 대댓글
	@ResponseBody
	public String writeRereply(FreeBoardReviewDto freeBoardReview) {
	    String replyContent = freeBoardReview.getReplyContent();

	    if (replyContent != null && !replyContent.isEmpty()) {
	        FreeBoardReviewDto parentFreeBoardReply = adminService.findFreeBoardReviewByFreeBoardReplyNo(freeBoardReview.getFreeBoardReplyNo());

	        freeBoardReview.setFreeBoardNo(parentFreeBoardReply.getFreeBoardNo());
	        freeBoardReview.setReplyParents(parentFreeBoardReply.getReplyParents());
	        freeBoardReview.setReplySequence(parentFreeBoardReply.getReplySequence() + 1);
	        freeBoardReview.setReplyDepth(parentFreeBoardReply.getReplyDepth() + 1);
	        freeBoardReview.setReplyContent(replyContent);  

	        adminService.updateReplySequence(freeBoardReview);
	        adminService.writeRereply(freeBoardReview);

	        return "success";
	    } else {
	        return "fail";
	    }
	}
	
	
}