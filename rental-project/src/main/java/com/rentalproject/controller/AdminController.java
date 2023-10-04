package com.rentalproject.controller;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.rentalproject.dto.ItemThumbDto;
import com.rentalproject.dto.MemberDto;
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
	
//	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<List<ItemThumbDto>> uploadAjaxActionPost(MultipartFile[] uploadFile,  HttpServletRequest req) {
//		
//		// 이미지 파일 체크
//		for(MultipartFile multipartFile: uploadFile) {
//			
//			File checkfile = new File(multipartFile.getOriginalFilename());
//			String type = null;
//			
//			try {
//				type = Files.probeContentType(checkfile.toPath());
//				//logger.info("MIME TYPE : " + type);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			if(!type.startsWith("image")) {
//				
//				List<ItemThumbDto> list = null;
//				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
//				
//			}
//			
//		}
//		
//		
//		String uploadFolder = req.getServletContext().getRealPath("/resources/upload/");
//		
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		Date date = new Date();
//		
//		String str = sdf.format(date);
//		
//		String datePath = str.replace("-", File.separator);
//		
//		// 폴더 생성
//		File uploadPath = new File(uploadFolder, datePath);
//		
//		if(uploadPath.exists() == false) {
//			uploadPath.mkdirs();
//		}
//		
//		List<ItemThumbDto> thumbList = new ArrayList();
//		
//		for(MultipartFile multipartFile : uploadFile) {
//			
//			// 이미지 정보 
//			ItemThumbDto thumb = new ItemThumbDto();
//			
//			// 파일 이름
//			String uploadFileName = multipartFile.getOriginalFilename();
//			thumb.setFileName(uploadFileName);
//			thumb.setUploadPath(datePath);
//			
//			String uuid = UUID.randomUUID().toString();
//			thumb.setUuid(uuid);
//			
//			// 파일 위치
//			File saveFile = new File(uploadPath, uploadFileName);
//			
//			// 파일 저장
//			try {
//				multipartFile.transferTo(saveFile);
//				
//				// 썸네일(ImageIO)
//				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
//				
//				BufferedImage bo_image = ImageIO.read(saveFile);
//				
//					double ratio = 3;
//					
//					int width = (int) (bo_image.getWidth()/ratio);
//					int height = (int) (bo_image.getHeight()/ratio);
//					
//				// 방법 1
//				
//				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//				
//				Graphics2D graphic = bt_image.createGraphics();
//				
//				graphic.drawImage(bo_image, 0, 0, width, height, null);
//				
//				ImageIO.write(bt_image, "jpg", thumbnailFile);
//				
//				// 대체 가능
////				Thumbnails.of(saveFile)
////				.size(width, height)
////				.toFile(thumbnailFile);
//				
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			
//			thumbList.add(thumb);
//			
//		}
//		
//		ResponseEntity<List<ItemThumbDto>> res = new ResponseEntity<List<ItemThumbDto>>(thumbList, HttpStatus.OK);
//		
//		return res;
//	}
	
	private ArrayList<ItemAttachDto> handleUploadFile(MultipartFile attach, String uploadDir) {
		ArrayList<ItemAttachDto> attachList = new ArrayList<>();
		if (!attach.isEmpty()) {
			try {
				String savedFileName = Util.makeUniqueFileName(attach.getOriginalFilename());
				
				attach.transferTo(new File(uploadDir, savedFileName)); // 파일을 컴퓨터에 저장
				
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
		
		ItemAttachDto attach = adminService.findItemAttachByAttachNo(itemNo);
		ItemDto item = adminService.itemDetail(itemNo);
		
		
//		if(item == null) {
//			return "redirect:list";
//		}
		
		//log.info("/detail");
		
		model.addAttribute("attach", attach);
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
