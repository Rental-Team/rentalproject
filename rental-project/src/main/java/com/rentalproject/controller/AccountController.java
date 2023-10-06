package com.rentalproject.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rentalproject.common.MailUtil;
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
	@GetMapping(path= {"/register"}) //action
	public String registerForm(@ModelAttribute("member") MemberDto member) { // = model.addAttribute("member", member);
		
		return "account/register";
	}
	
	// 회원가입 아이디 중복검사
	@GetMapping(path= {"/check-id"}, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String checkIdDuplication(String memberId) {
		
		boolean valid = accountService.checkRegisterId(memberId);
		return String.valueOf(valid);
	}
	
	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		
//		System.out.println("이메일 인증 요청이 들어옴!");
//		System.out.println("이메일 인증 이메일 : " + email);
		
		return accountService.joinEmail(email);
	}
	
	
	// 회원가입 등록
	@PostMapping(path= {"register"})
	public String register(@ModelAttribute("member") MemberDto member, HttpSession session) {
			
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
	
	// 아이디 찾기
	@GetMapping(path= {"/findid"})
	public String findIdForm() {
		return "account/findid";
	}
	
	// 아이디 찾기 구현
	@PostMapping(path= {"/findid"})
	public String findUserId(MemberDto member, Model model) {
		MemberDto findIdMember = accountService.findLoginId(member);
		
		if(findIdMember != null && findIdMember.isDeleteCheck()== false) {
			model.addAttribute("check", 0);
			model.addAttribute("memberId", findIdMember.getMemberId());

		} else {
			model.addAttribute("check", 1);
		}
		
		return "account/findid";
		
	}
	
	// 비밀번호 찾기
	@GetMapping(path= {"/findpw"})
	public String findPasswordForm() {
		return "account/findpw";
	}
	
	// 비밀번호 찾기 구현
	@PostMapping(path= {"/findpw"})
	public String findUserPw(MemberDto member, Model model) throws Exception {
		
		MemberDto findPwMember = accountService.findLoginPw(member);
		String newpw = "";
		
		if(findPwMember != null && findPwMember.isDeleteCheck() == false) {
//			model.addAttribute("check", 0);
//			model.addAttribute("memberId", findPwMember.getMemberId());
			
			UUID uid = UUID.randomUUID();
			
			newpw = uid.toString().substring(0,6);
			findPwMember.setPassword(newpw);
			
			MailUtil mail = new MailUtil();
			mail.sendEmail(findPwMember);
			
			accountService.newPw(findPwMember);
			
			return "account/login";
			
		} else {
//			model.addAttribute("check", 1);
			
			return "account/findpw";
		}
	}
	
	@PostMapping(path= {"editpw"})
	public String newUserPw(MemberDto meber, RedirectAttributes rttr) {
		
		return "account/editpw";
	}
}

























