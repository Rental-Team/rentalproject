package com.rentalproject.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.rentalproject.common.Util;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.ProfileService;

@Controller
@RequestMapping(path= {"/profile"})
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

	
	// 프로필 창 
	@GetMapping(path= {"/profile"})
	public String profile(HttpSession session, Model model) {
		
		MemberDto loginUser = (MemberDto)session.getAttribute("loginuser");
		
		//로그인안되어 있으면 로그인창으로
		if(loginUser == null) {
			return "redirect:/account/login";
		}
		
		model.addAttribute("profileuser", loginUser);
		// 로그인 되어 있다면 프로필창으로
		return "profile/profile";
	}
	// 프로필 수정 창
	@GetMapping(path= {"/profileedit"})
	public String showProfileEditForm(HttpSession session, Model model) { 
		
		MemberDto loginUser = (MemberDto)session.getAttribute("loginuser");
		model.addAttribute("profileuser", loginUser);

		return "profile/profileedit";
	}
	
	// 프로필 수정
	@PostMapping(path= {"/profileedit"})
	public String prifileEdit (MemberDto member, HttpSession session, MultipartFile attach, HttpServletRequest req) {
		
//		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
//		MemberDto attachImg = handleUpImg(attach, uploadDir);
		
		profileService.editProfile(member); 
		session.setAttribute("loginuser", member); 
		
		return String.format("redirect:profile?memberId=%s", member.getMemberId());
	}
	
	
//	private MemberDto handleUpImg(MultipartFile attach, String uploadDir) {
//		if (!attach.isEmpty()) {
//			try {
//				String savedFileName = Util.makeUniqueFileName(attach.getOriginalFilename());	
//				
//				attach.transferTo(new File(uploadDir, savedFileName));
//				MemberDto member = new MemberDto();
//				member.setImageName(savedFileName);
//				System.out.println(member);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return null;
//	}
	// 회원 탈퇴
	@GetMapping(path= {"/delete/{memberId}"})
	public String userDelete(@PathVariable("memberId") String memberId, HttpSession session) {
		
		profileService.deleteUser(memberId);
		session.removeAttribute("loginuser");
		return "redirect:/home";
	}
		

}