package com.rentalproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AccountService;

@Controller
@RequestMapping(path= {"/account"})
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Qualifier("accountService")
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	// 회원가입 창
	@GetMapping(path= {"/register"})
	public String registerForm(@ModelAttribute("member") MemberDto member) { // = model.addAttribute("member", member);
		
		return "account/register";
	}
	
	// 회원가입 등록
	@PostMapping(path= {"register"})
	public String register(@ModelAttribute("member") MemberDto member) {
		
		accountService.register(member);
		return "redirect:/account/login";
	}
	
	// 로그인 창
	@GetMapping(path= {"/login"})
	public String loginForm(@ModelAttribute("member") MemberDto member) {
		
		return "account/login";
	}
	
	// 로그인 실행
	@PostMapping(path= {"/login"})
	public String login(MemberDto member, HttpSession session, Model model) { //model: view(jsp)로 데이터를 보내주는 통로
		
		// MemberDto의 변수 값들을 
		MemberDto loginMember = accountService.findLoginMember(member);
		
		if(loginMember != null) { // 회원가입해서 들어있는 정보가 null이 아니라면 = 회원가입된 유저라면
			session.setAttribute("loginuser", loginMember); //loginuser라는 이름으로 loginMember의 데이터를 세션에 저장
			return String.format("redirect:/home?memberId=%s", member.getMemberId());
		} else {
			model.addAttribute("loginfail", "x");
			return "account/login";
		}
	}
	
	// 로그아웃 실행
	@GetMapping(path= {"/logout"})
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginuser");
		return "redirect:/home";
	}
	
	
}
