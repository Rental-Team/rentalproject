package com.rentalproject.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rentalproject.common.Util;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.KakaoService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(path= {"/account"})
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private KakaoService ks;
	
	
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
		return String.valueOf(valid); // true, false를 문자열로 반환
	}
	
	// 회원가입 닉네임 중복검사
	@GetMapping("/check-nickname")
	@ResponseBody
	public String checkNicknameDuplication(String nickname) {
		
		boolean valid = accountService.checkRegisterNickname(nickname);
		return String.valueOf(valid);
	}
	
	// 이메일 인증
	@GetMapping("/check-email")
	@ResponseBody
	public String emailCheck(String email) {
		
//		System.out.println("이메일 인증 요청이 들어옴!");
//		System.out.println("이메일 인증 이메일 : " + email);
		
		return accountService.emailContent(email);
	}
	
	// 회원가입 등록
	@PostMapping(path= {"register"})
	public String register(@RequestParam("email1") String email1, @RequestParam("email2") String email2, 
						   @ModelAttribute("member") @Valid MemberDto member, HttpSession session, HttpServletRequest req, 
						   @RequestParam("imageName") MultipartFile memberImage, BindingResult result) {
		
		// register.jsp의 email1 과 email2를 email 완전체로 합친다
		String combinedEmail = email1 + "@" + email2;
		member.setEmail(combinedEmail);
		
		// 이미지 첨부파일(프로필 사진)
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/");
		String uploadedImageFileName = handleUploadFile(memberImage, uploadDir);
		if (uploadedImageFileName != null) {
	        member.setMemberImage(uploadedImageFileName);
	    }
		 
        // 회원 정보 등록
        accountService.register(member);
        return "redirect:/account/login";
	}
	private String handleUploadFile(MultipartFile attach, String uploadDir) {
		if (!attach.isEmpty()) {
			try {
				String savedFileName = Util.makeUniqueFileName(attach.getOriginalFilename());

				attach.transferTo(new File(uploadDir, savedFileName)); // 파일을 컴퓨터에 저장
				
				// 썸네일 생성
				File thumbnailFile = new File(uploadDir, "thumbnail_" + savedFileName);
				Thumbnails.of(new File(uploadDir, savedFileName))
							.size(100, 100) // 썸네일 크기
							.toFile(thumbnailFile);
				
				return savedFileName;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
    
	// 로그인 창
	@GetMapping(path= {"/login"})
	public String loginForm(@RequestParam(defaultValue = "/home")String returnUrl, 
							@ModelAttribute("member") MemberDto member,
							@RequestParam(name = "code", required = false) String code,
							Model model, HttpSession session) throws IOException {
		
		
		// 카카오 로그인
        if (code != null) { // 코드가 있다면
        	System.out.println("성공: " + code);
        	String access_token = ks.getToken(code); 
        	Map<String, Object> userInfo = ks.getUserInfo(access_token);
        	
        	String email = (String) userInfo.get("email");
        	String nickname = (String) userInfo.get("nickname");

        	MemberDto existingMember = accountService.findKakaoMember(member);
        	
        	if (existingMember == null) {
                // 이미 회원이 아닌 경우, 새로운 회원으로 등록
                member.setMemberId(email);
                member.setPassword(code);
                member.setPasswordConfirm(code);
                member.setUserName(nickname);
                member.setNickname(nickname);
                member.setPhoneNo("");
                member.setEmail(email);
                member.setAddressCode("");
                member.setAddress("");
                member.setKakao(1);

                accountService.register(member);
            }
        	
        	model.addAttribute("code", code);
            model.addAttribute("access_token", access_token);
            model.addAttribute("userInfo", userInfo);
            
            if (existingMember != null) {
                session.setAttribute("loginuser", existingMember);
            } else {
                session.setAttribute("loginuser", member);
            }
            
            return String.format("redirect:/home?memberId=%s", member.getMemberId());
        }

        model.addAttribute("returnUrl", returnUrl.replace("!", "&")); // 로그인 시 !가 있다면 &으로 바꿔준다
	    return "account/login";
	}
	
	
	// 로그인 실행
	@PostMapping(path= "/login", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> login( MemberDto member, HttpSession session, String returnUrl) {
        Map<String, Object> response = new HashMap<>();

        MemberDto loginMember = accountService.findLoginMember(member);

        if (loginMember != null) { // 회원 가입된 유저라면
            if (loginMember.isDeleteCheck()) { // 삭제된 계정인지 확인
            	response.put("check", 0);
            } else {
            	response.put("check", 1);
            	session.setAttribute("loginuser", loginMember);
                if(returnUrl.contains("/home")) {
                	response.put("redirectUrl", "/home?memberId=" + loginMember.getMemberId());
    	        } else {
    	        	response.put("redirectUrl", returnUrl);
    	        }
            }
        } else {
            response.put("check", 2);
        }
        return response;
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
	@PostMapping(path= "/find-id", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> findUserId(@RequestParam("userName") String userName, @RequestParam("phoneNo") String phoneNo) {
	    Map<String, Object> response = new HashMap<>();
	    MemberDto findIdMember = accountService.findLoginId(userName, phoneNo);

	    if (findIdMember != null && !findIdMember.isDeleteCheck()) {
	        response.put("check", 0);
	        response.put("memberId", findIdMember.getMemberId());
	    } else {
	        response.put("check", 1);
	    }

	    return response;
	}
	
	// 비밀번호 찾기
	@GetMapping(path= {"/findpw"})
	public String findPasswordForm() {
		return "account/findpw";
	}
	
	// 비밀번호 찾기 구현
	@PostMapping(path= "/find-pw", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> findUserPw(@RequestParam("memberId") String memberId, @RequestParam("email") String email) {
		Map<String, Object> response = new HashMap<>();
		
		MemberDto findPwMember = accountService.findLoginPw(memberId, email);
		
		if (findPwMember != null && !findPwMember.isDeleteCheck()) {
	        response.put("check", 0);
	        accountService.emailContentForTemporaryPw(email); // 이메일로 임시 비밀번호 전송
	        String newPassword = accountService.emailContentForTemporaryPw(email); // 임시 비밀번호 newPassword에 저장
//	        String hashedPasswd = Util.getHashedString(newPassword,"SHA-256"); // 해쉬코드로 변경
	        accountService.updateLoginPw(memberId, newPassword); // 해당 아이디에 패스워드를 변경
	    } else {
	        response.put("check", 1);
	    }
	    return response;
	}
	
	@PostMapping(path= {"editpw"})
	public String newUserPw(MemberDto member, RedirectAttributes rttr) {
		
		return "account/editpw";
	}
}

























