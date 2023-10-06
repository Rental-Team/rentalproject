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

import com.rentalproject.common.Util;
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
		
		int pageSize = 10;
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
	
	@GetMapping("/item/write")

	public String itemWriteForm(ItemDto item, Model model) {
		
		
		return "admin/item/write";
	}
	
	// 상품 등록
	@PostMapping("/item/write")
	public String write(ItemDto item, MultipartFile attach, HttpServletRequest req, RedirectAttributes rttr, Model model) {

		//log.info("register: " + item);
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<ItemAttachDto> attachList = handleUploadFile(attach, uploadDir);
		item.setItemAttachList(attachList);

		adminService.writeItem(item);
//		rttr.addAttribute("result", item.getItemNo());
		
		if (!attachList.isEmpty()) {
	        model.addAttribute("imagePath", "/resources/upload/" + attachList.get(0).getSavedFileName());
	    }
		
		rttr.addFlashAttribute("write_result", item.getItemName());

		return "redirect:/admin/item/list";
	}
	

	
	// 첨부파일 
	private ArrayList<ItemAttachDto> handleUploadFile(MultipartFile attach, String uploadDir) {
		ArrayList<ItemAttachDto> attachList = new ArrayList<>();
		if (!attach.isEmpty()) {
			try {
				String savedFileName = Util.makeUniqueFileName(attach.getOriginalFilename());
				File uploadPath = new File(uploadDir);
				
				if (!uploadPath.exists()) {
	                uploadPath.mkdirs();
	            }
				
				// 원본 이미지 저장
				File target = new File(uploadDir, savedFileName);
				attach.transferTo(target);
				
				// 썸네일 생성
				Thumbnails.of(target)
						.size(100, 100)
						.toFile(new File(uploadDir, "thumb_" + savedFileName));
				
				
				// 파일 정보를 dto에 저장
				ItemAttachDto itemAttach = new ItemAttachDto();
				itemAttach.setUserFileName(attach.getOriginalFilename());
				itemAttach.setSavedFileName(savedFileName);
				
				attachList.add(itemAttach);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return attachList;
	}
	
	// 다운로드
	@GetMapping(path = { "/download" })
	public View download(int attachNo, Model model) {
		
		// 1. 첨부파일 조회
		ItemAttachDto attach = adminService.findItemAttachByAttachNo(attachNo);
		
		// 2. 다운로드 처리
		model.addAttribute("attach", attach);	// View에서 사용할 수 있도록 데이터 저장
		DownloadView downloadView = new DownloadView();
		
		// return "download"; // "/WEB-INF/views/" + download + ".jsp"
		return downloadView;
	}

	// 상품 상세
	@GetMapping("/item/detail")
	public void detail(@RequestParam("itemNo") int itemNo,
			 			Model model) { //@RequestParam(defaultValue = "-1")int pageNo,
		
		ItemAttachDto attach = adminService.findItemAttachByAttachNo(itemNo);
		ItemDto item = adminService.itemDetail(itemNo);
		
		
		model.addAttribute("attach", attach);
		model.addAttribute("item", item);
		

	}

	// 상품 수정 폼
	@GetMapping("/item/edit")
	public String editForm(@RequestParam("itemNo") int itemNo, Model model) {
		
		if (itemNo == -1) {
			return "redirect:list";
		}
		
		ItemDto item = adminService.itemDetail(itemNo);
		
		//log.info(item);
		
		if (item == null) {
			return "redirect:/admin/item/list";
		}
		
		
		
		model.addAttribute("item", item);
		model.addAttribute("itemNo", itemNo);
		
		
		return "admin/item/edit";
		
	}
	
	// 상품 수정
	@PostMapping("/item/edit")
	public String edit(ItemDto item ) {
		
		adminService.editItem(item);
		
		return String.format("redirect:detail?itemNo=%d", item.getItemNo());
	}
	
	// 상품 삭제
	@GetMapping("/delete/{itemNo}")
	public String delete(@PathVariable("itemNo") int itemNo) {
		
		adminService.deleteItem(itemNo);
				
		return "redirect:/admin/item/list";
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
