package com.rentalproject.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
<<<<<<< HEAD
=======

>>>>>>> 25a984580f8841fbf3d693dfc6c1e3e13bfb4c6d

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.ProfileService;

@Controller
@RequestMapping(path= {"/profile"})
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	@Autowired
	private AccountService accountService;
	
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
	public String showProfileEditForm(HttpSession session, Model model) { //model: view(jsp)로 데이터를 보내주는 통로
		
		// 1. 기존 내용 조회(AccountController에서 세션에 저장되어있던 로그인 유저에서의 데이터를 MemberDto에 profileUser라는 이름으로 저장
		MemberDto profileUser = (MemberDto)session.getAttribute("loginuser");
		// 5. 다시 프로필 수정 창으로 들어온다면 세션에 저장된 수정되어진 로그인 유저의 데이터를 MemberDto에 profileUser라는 이름으로 저장
		
		// 2. profileUser의 데이터를 view(JSP)에서 읽을 수 있도록 profileuser라는 이름으로 변경
		model.addAttribute("profileuser", profileUser);
		// 6. 수정되어진 로그인 유저의 데이터를 머금은 profileUser를 view(JSP)에서 읽을 수 있도록 profileuser라는 이름으로 변경
		
		// 위 두줄 코드를 안쓰고 그냥 loginuser로 써도 된다.
		return "profile/profileedit";
	}
	
	// 프로필 수정
	@PostMapping(path= {"/profileedit"})
	public String prifileEdit (MemberDto member, HttpSession session, HttpServletRequest req) {
//		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
//		MemberDto photo = han
		
		
		
		// 3. 세션에 로그인 유저의 데이터가 저장되어 있는 MemberDto의 데이터를 수정하는 메서드에 대입
		profileService.editProfile(member); 
		
		// 4. 로그인된 사용자의 기존 내용 조회
		// MemberDto loginMember = accountService.findLoginMember(member);
		
		// 5. 수정되어진 로그인 유저의 데이터를 가지고 있는 MemberDto를 loginuser라는 이름으로 세션에 저장
		session.setAttribute("loginuser", member); 
		
		return String.format("redirect:profile?memberId=%s", member.getMemberId());
	}
	
//	// 비밀번호 수정
//	@GetMapping(path= {"/passwordedit"})
//	public String showPasswordEditForm(HttpSession session) {
//		return "profile/passwordedit";
//	}
	
	// 회원 탈퇴
	@GetMapping(path= {"/delete/{memberId}"})
	public String userDelete(@PathVariable("memberId") String memberId, HttpSession session) {
		
		profileService.deleteUser(memberId);
		session.removeAttribute("loginuser");
		return "redirect:/home";
	}
		

}