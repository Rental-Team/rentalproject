package com.rentalproject.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rentalproject.common.Util;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.ProfileService;
import com.rentalproject.ui.ThePager;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

	
	// 프로필 창 
	@GetMapping(path= {"/profile/profile"})
	public String profile(HttpSession session, Model model, String memberId) {
		
		MemberDto loginUser = (MemberDto)session.getAttribute("loginuser");
		
		//로그인안되어 있으면 로그인창으로
		if(loginUser == null) {
			return "redirect:/account/login";
		}
		model.addAttribute("profileuser", loginUser);
		
		// 로그인 되어 있다면 프로필 창으로
		return "profile/profile";
	}
	
	// 프로필 수정 창
	@GetMapping(path= {"/profile/profileedit"})
	public String showProfileEditForm(String memberId, HttpSession session, Model model) { 
		
		MemberDto selectRegDate = profileService.selectRegDate(memberId);
		session.setAttribute("selectRegDate", selectRegDate);
		
		MemberDto loginUser = (MemberDto)session.getAttribute("loginuser"); // 기존 내용을 보여준다
		model.addAttribute("profileuser", loginUser);
		

		return "profile/profileedit";
	}
		
	// 프로필 수정
	@PostMapping(path= {"/profile/profileedit"})
	public String prifileEdit (String memberId, MemberDto member, HttpSession session, HttpServletRequest req, 
			                   @RequestParam("imageName") MultipartFile memberImage,
			                   @RequestParam("useDefaultPhoto") String useDefaultPhoto) throws Exception {
		
		// 이미지 첨부파일(프로필 사진)
		String uploadDir = req.getServletContext().getRealPath("/resources/upload/"); // 업로드 파일을 저장할 디렉토리 경로를 설정
		String uploadedImageFileName = handleUploadFile(memberImage, uploadDir); // memberImage를 uploadDir에 저장하고 저장된 파일 이름 또는 경로 반환 (업로드 처리) 그리고 uploadedImageFileName에 저장 
		if (uploadedImageFileName != null) { // uploadedImageFileName 이 있으면
	        member.setMemberImage(uploadedImageFileName); // member의 memberImage에 저장
	    }
		
		// 프로필 사진+일반 정보 
		profileService.updateProfile(member, useDefaultPhoto);
		
		
		// 회원가입 등록일 유지
		MemberDto selectRegDate = profileService.selectRegDate(memberId);
		session.setAttribute("loginuser", selectRegDate); 
	
		System.out.println(useDefaultPhoto);
		
		return String.format("redirect:profile?memberId=%s", member.getMemberId());
	}
	
	
	
	
	// 파일 처리
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
	
	// 내가 쓴 자유게시판 글 조회
	@GetMapping(path= {"/freeboard/myfreeboardlist"})
	public String listMyFreeBoard(@RequestParam(defaultValue = "1")int pageNo, Model model, HttpSession session) {	
		MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");
		
		int memberNo = -1;
		
		if (loginuser != null) {
			memberNo = loginuser.getMemberNo();
		}
		
		int pageSize = 10;
	    if (pageSize < 1) {
	        pageSize = 10; // 기본 페이지 크기를 설정하세요.
	    }
	    
	    // 페이지 번호 체크
	    if (pageNo < 1) {
	        pageNo = 1;
	    }
	    
	    int pagerSize = 5;
	    String linkUrl = "myfreeboardlist";
	    
	    int dataCount;
	    
	    List<FreeBoardDto> myFreeBoardList;
	    
	    dataCount = profileService.getMyFreeBoardCountByMemberNo(memberNo);
	    int from = (pageNo -1) * pageSize;
	    myFreeBoardList = profileService.listMyFreeBoardByMemberNo(memberNo, from, dataCount);
	    	
	    for (FreeBoardDto freeboard : myFreeBoardList) {
	    	String memberId = profileService.getMemberIdByMyFreeBoardNo(freeboard.getFreeBoardNo());
	    	freeboard.setMemberId(memberId);
	    }
	    	
	    ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl);
	    
	    model.addAttribute("myFreeBoardList", myFreeBoardList);
	    model.addAttribute("pager", pager);
	    model.addAttribute("pageNo", pageNo);
	    
	    return "profile/myfreeboardlist";
	}
	
	// 회원 탈퇴
	@GetMapping(path= {"/profile/delete/{memberId}"})
	public String userDelete(@PathVariable("memberId") String memberId, HttpSession session) {
		
		profileService.deleteUser(memberId);
		session.removeAttribute("loginuser");
		return "redirect:/home";
	}
}