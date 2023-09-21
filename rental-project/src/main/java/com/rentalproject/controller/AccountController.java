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
	
	private AccountService accountService;
	@Autowired
	@Qualifier("accountService")
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	// 회원가입 창
	@GetMapping(path= {"/register"})
	public String registerForm(@ModelAttribute("member") MemberDto member) {
		
		return "account/register";
	}
	
	// 회원가입 등록
	@PostMapping(path= {"register"})
	public String register(@ModelAttribute("member") MemberDto member) {
		
		accountService.register(member);
		return "redirect:/home";
	}
	
	// 로그인 창
	@GetMapping(path= {"/login"})
	public String loginForm(@ModelAttribute("member") MemberDto member) {
		
		return "account/login";
	}
	
	// 로그인 실행
	@PostMapping(path= {"/login"})
	public String login(MemberDto member, HttpSession session, Model model) {
		
		MemberDto loginMember = accountService.findLoginMember(member);
		
		if(loginMember != null) { 
			session.setAttribute("loginuser", loginMember);
			return "redirect:/home";
		} else {
			model.addAttribute("loginfail", true);
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
