package com.rentalproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rentalproject.common.Util;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.ItemThumbDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AdminService;
import com.rentalproject.ui.ThePager;
import com.rentalproject.view.DownloadView;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {
		
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/home")
	public void adminHome() throws Exception {
		
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
	public String itemWriteForm() {
		
		
		return "admin/item/write";
	}
	
	// 상품 등록
	@PostMapping("/item/write")
	public String write(ItemDto item, MultipartFile attach, HttpServletRequest req, RedirectAttributes rttr) {

		//log.info("register: " + item);
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		ArrayList<ItemAttachDto> attachList = handleUploadFile(attach, uploadDir);
		item.setItemAttachList(attachList);

		adminService.writeItem(item);
//		rttr.addAttribute("result", item.getItemNo());
		
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

	@GetMapping("/item/detail")
	public void detail(@RequestParam("itemNo") int itemNo,
			 			Model model) { //@RequestParam(defaultValue = "-1")int pageNo,
		
		ItemDto item = adminService.itemDetail(itemNo);
		
//		if(item == null) {
//			return "redirect:list";
//		}
		
		//log.info("/detail");
		
		model.addAttribute("item", item);
		

	}

	
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
	
	
	@PostMapping("/item/edit")
	public String edit(ItemDto item ) {
		
		adminService.editItem(item);
		
		return String.format("redirect:detail?itemNo=%d", item.getItemNo());
	}
	
	
	@GetMapping("/delete/{itemNo}")
	public String delete(@PathVariable("itemNo") int itemNo) {
		
		adminService.deleteItem(itemNo);
				
		return "redirect:/admin/item/list";
	}
	
}
