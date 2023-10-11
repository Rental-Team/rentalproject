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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalproject.common.Util;
import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.service.AdminService;
import com.rentalproject.ui.ThePager;
import com.rentalproject.view.DownloadView;

import net.coobird.thumbnailator.Thumbnails;


@Controller
@RequestMapping("/admin")
public class AdminController {
		
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/home")
	public void adminHome(Model model) throws Exception {
		List<MemberDto> memberList = adminService.MemberList();
		
		model.addAttribute("memberList", memberList);
		
		
	}
	
	@GetMapping("/member/list")
	public String getMemberList(Model model) throws Exception {
		
		List<MemberDto> memberList = adminService.MemberList();
		
		model.addAttribute("memberList", memberList);
		
		return "admin/member/list";
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
		
		// 썸네일 경로를 추가(여기서 ItemDto에 썸네일 필드 추가)
//		for (ItemDto item : itemList) {
//	            String thumbnail = item.getThumbnail();
//	            if(thumbnail != null) {
//	            	int dotIdx = thumbnail.lastIndexOf(".");
//	            	if (dotIdx > 0) {
//	            		item.setThumbnail(thumbnail.substring(0, dotIdx) + "_thumbnail" +thumbnail.substring(dotIdx)); 
//	            	}
//	            } 
//	 
//	    }
		
		
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
	public String write(ItemDto item, MultipartFile attach, HttpServletRequest req) {

		// 아이템 업로드
		//log.info("register: " + item);
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<ItemAttachDto> attachList = handleUploadFile(attach, uploadDir);
		item.setItemAttachList(attachList);

		// 상품 등록
		adminService.writeItem(item);
	

		return "redirect:/admin/item/list";
	}
	

	
	// 첨부파일 
	private ArrayList<ItemAttachDto> handleUploadFile(MultipartFile attach, String uploadDir) {
		ArrayList<ItemAttachDto> attachList = new ArrayList<>();
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
	public String edit(ItemDto item, MultipartFile attach, HttpServletRequest req, 
			  @RequestParam(defaultValue = "-1") int pageNo ) {
		
		if( pageNo < 1) {
			return "redirect:list";
		}
		
		// 파일 업로드 처리
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<ItemAttachDto> attachList = handleUploadFile(attach, uploadDir);
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
	
	
}
