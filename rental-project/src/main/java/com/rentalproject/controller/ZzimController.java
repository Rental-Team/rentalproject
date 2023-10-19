package com.rentalproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.ZzimDto;
import com.rentalproject.service.ZzimService;

@Controller
public class ZzimController {

	@Autowired
	private ZzimService zzimService;
	
	@PostMapping("/item/zzim-add")
	@ResponseBody
	public String addZzimPost(ZzimDto zzim, HttpServletRequest req) {
		
		// 로그인 체크
		HttpSession session = req.getSession();
		MemberDto member = (MemberDto)session.getAttribute("loginuser");
		if(member == null) {
			return 4 + "";
		}
		
		// 찜 등록
		int result = zzimService.addZzim(zzim);
		
		return result + "";
		
	}
	
	@GetMapping("/zzim")
	public String zzimList() {
		
		
		return "zzim";
	}
	
	@GetMapping("/zzim/{memberNo}")
	public String zzimList(@PathVariable("memberNo") int memberNo, Model model ) {
		
		model.addAttribute("zzimInfo", zzimService.getZzimList(memberNo));
		
		return "zzim";
	}
	
	
	@PostMapping("/zzim/update")
	public String updateZzim(ZzimDto zzim) {
		
		
		zzimService.modifyCount(zzim);
		
		return "redirect:/zzim/" + zzim.getMemberNo();
		
	}
	
	@PostMapping("/zzim/delete")
	public String deleteZzim(ZzimDto zzim) {
		
		zzimService.deleteZzim(zzim);
		
		return "redirect:/zzim/" + zzim.getMemberNo();
		
	}
	
	
}
