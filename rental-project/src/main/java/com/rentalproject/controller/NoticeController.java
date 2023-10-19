package com.rentalproject.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.service.FreeBoardService;
import com.rentalproject.service.NoticeService;
import com.rentalproject.ui.ThePager;
import com.rentalproject.view.DownloadView;

@Controller
@RequestMapping(path = {"/notice"})
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	
	@GetMapping(path= {"/list"})
	public String list(@RequestParam(defaultValue = "1")int pageNo, Model model) {
		
		
		int pageSize = 10;
		int pagerSize = 5;
		String linkUrl = "list";
		int dataCount = noticeService.getNoticeCount();
		
		int from = (pageNo - 1)*pageSize;
		
		
		List<NoticeDto> List = noticeService.listNoticeByPage(from, pageSize);
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl);
		
		model.addAttribute("noticeList", List);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		/*
		 * model.addAttribute("noticeList", List);
		 */
		return "/notice/list";
	}
	

	@GetMapping(path= {"/notice2"})
	public String notice2(Model model) {
		
		
		int pageSize = 5;		
		List<NoticeDto> noticeList = noticeService.listNotice();
		
		List<NoticeDto> topNoticeList = noticeList.subList(0, Math.min(pageSize, noticeList.size()));
    	 
    	 model.addAttribute("noticeList", topNoticeList);
		 
		//model.addAttribute("noticeList", List);
		
		return "/notice/notice2";
	}
	
	@GetMapping("/write")
    public String showNoticeWriteForm() {
        return "/notice/write"; 
    }
	
	@PostMapping(path= {"/write"})
	
		public String writeNotice(NoticeDto notice, HttpServletRequest req) throws Exception {
		
		
		
		noticeService.writeNotice(notice);
		
		return "redirect:/notice/list";
		
	}
	

	@GetMapping(path = {"/detail"})
	public String detail(@RequestParam(defaultValue = "-1") int noticeNo, Model model, HttpServletRequest req, HttpServletResponse resp) {
		
		
		NoticeDto notice = noticeService.findNoticeByNoticeNo(noticeNo);
		
		String cookieName = "viewed_notice_" + noticeNo;
		boolean hasViewed = false;
		Cookie[] cookies = req.getCookies();
		
	    if(cookies != null) {
	    	for (Cookie cookie : cookies) {
	    		if(cookieName.equals(cookie.getName())) {
	    			hasViewed = true;
	    			break;
	    		}
	    	}
	    }
		
	    if (!hasViewed) {
	    	noticeService.updateviewCount(noticeNo); // 조회수
	    	
	    	Cookie viewedNoticeCookie = new Cookie(cookieName, "1");
	    	viewedNoticeCookie.setMaxAge(24 * 60 * 60);
	    	viewedNoticeCookie.setPath("/");
	    	resp.addCookie(viewedNoticeCookie);
	    
	    }
	    
		model.addAttribute("notice", notice);
		
		return "/notice/detail";
		
	}
	
	
	 
		@GetMapping(path = {"/edit"})
		public String showNoticeEditForm(@RequestParam(defaultValue = "-1") int noticeNo, 
											Model model) {
		
		NoticeDto notice = noticeService.findNoticeByNoticeNo(noticeNo);
		
		
		 
		model.addAttribute("notice", notice);
		
		return "/notice/edit";
	}
	
		@PostMapping(path = {"/edit"})
		public String noticeEdit(NoticeDto notice, HttpServletRequest req) {
		
		
		noticeService.editNotice(notice);
		
		return "redirect:/notice/list";
	}
	
	
	
		
}

	

	


