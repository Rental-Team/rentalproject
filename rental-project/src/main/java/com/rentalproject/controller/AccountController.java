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
	public String register(@ModelAttribute("member") MemberDto member, HttpSession session) {
		
//		MemberDto registerMember = accountService.findLoginMember(member);
//		String registerMemberId = registerMember.getMemberId();
//		
//		if(registerMemberId != null) {
//			return "account/register";
//		}
			
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
		
		MemberDto loginMember = accountService.findLoginMember(member);
		
//		if(loginMember != null) { // 회원가입해서 들어있는 정보가 null이 아니라면 = 회원가입된 유저라면
//			session.setAttribute("loginuser", loginMember); //loginuser라는 이름으로 loginMember의 데이터를 세션에 저장
//			return String.format("redirect:/home?memberId=%s", member.getMemberId());
//		} else {
//			model.addAttribute("loginfail", "x");
//			return "account/login";
//		}
		
		 if (loginMember != null) { // 회원 가입된 유저라면
		        if (loginMember.isDeleteCheck() != false) { // 삭제된 계정인지 확인
		            model.addAttribute("message", "이미 탈퇴한 계정입니다.");
		            return "account/login";
		        }

		        session.setAttribute("loginuser", loginMember);
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
	
	// ID 찾기 화면
	@GetMapping(path= {"/findid"})
	public String findIdForm() {
		return "account/findid";
	}
	
	// ID 찾기 수행
	@PostMapping(path= {"/findid"})
	public String findId(MemberDto member, Model model) {
		MemberDto loginMember = accountService.findLoginMember(member);
		String loginName = loginMember.getUserName();
		String loginPhoneNo = loginMember.getPhoneNo();
		
		if (loginName != null && loginPhoneNo != null) {
			model.addAttribute("userName",loginName);
		} else {
			model.addAttribute("일치하는 아이디가 없습니다", "x");
		}
		
		return "account/login";
	}
	
	// PW 찾기 화면
	@GetMapping(path= {"/findpw"})
	public String findPwForm() {
		return "account/findpw";
	}

	// PW 찾기 수행
	@PostMapping(path= {"/findpw"})
	public String findPw(MemberDto member, Model model, HttpSession session) {
		MemberDto loginMember = accountService.findLoginMember(member);
		String loginName = loginMember.getUserName();
		String loginPhoneNo = loginMember.getPhoneNo();
		String loginPassword = loginMember.getPassword();
		
		if (loginName != null && loginPhoneNo != null && loginPassword != null) {
			session.setAttribute("findpw", loginMember);
			return "redirect:account/editpw";
		} else {
			model.addAttribute("정보가 일치하지 않습니다", "x");
			return "account/findpw";
		}
		
		
	}
	
	// 비밀번호 변경 창
	@GetMapping(path= {"/editpw"})
	public String showEditPw(HttpSession session, Model model) {
		MemberDto loginUser = (MemberDto)session.getAttribute("loginuser");
		model.addAttribute("profileuser", loginUser);
		return "account/editpw";
	}
	
	// 비밀번호 변경 수행
	@PostMapping(path= {"/editpw"})
	public String editPw(MemberDto member, HttpSession session) {
		accountService.editPassword(member);
		session.setAttribute("editpw", member);
		
		return "redirect:/home";
	}
	
}
