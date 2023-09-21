package com.rentalproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.ProfileService;

@Controller
@RequestMapping(path= {"/profile"})
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	// 프로필 창 
	@GetMapping(path= {"/profile"})
	public String profile(HttpSession session, MemberDto member) {
		
		MemberDto loginUser = (MemberDto)session.getAttribute("loginuser");
		
		//로그인안되어 있으면 로그인창으로
		if(loginUser == null) {
			return "redirect:/account/login";
		}
		
		// 로그인 되어 있다면 프로필창으로
		return "profile/profile";
	}
	// 프로필 수정 창
	@GetMapping(path= {"/profileedit"})
	public String showProfileEditForm(HttpSession session, Model model) {
		
		// 기존 내용 조회
		MemberDto profileUser = (MemberDto)session.getAttribute("loginuser");
		
		model.addAttribute("profileuser", profileUser);
		return "profile/profileedit";
	}
	
	// 프로필 수정
	@PostMapping(path= {"/profileedit"})
	public String prifileEdit (MemberDto member, HttpSession session) {
		
//		MemberDto editUser = profileService.profileByMemberId(member);
//		
//		session.setAttribute("edituser", editUser);
		profileService.editProfile(member);
		
		
		return String.format("redirect:profile?memberId=%s", member.getMemberId());
	}
		
//		// 프로필 수정 창
//		@GetMapping(path= {"/profileedit"})
//		public String showForm() {
//			
//			return "account/profileedit";
//		}
		
		// 프로필 수정 실행
//		@PostMapping(path= {"/profileedit"})
//		public String (MemberDto member) {
//			
//			return "redirect:/home";
			
			// update 처리 (서비스 객체 사용)
//			accountService.editProfile(member);
			
//			return String.format("redirect:profile?memberId=%d", member.getMemberId());
//		}		

}