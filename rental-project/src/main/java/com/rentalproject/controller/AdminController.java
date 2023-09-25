package com.rentalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AdminService;

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
	
}
