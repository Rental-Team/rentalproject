package com.rentalproject.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.Session;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.service.PrivateQnaService;

@Controller
@RequestMapping(path = {"/privateboard"})
public class PrivateQnaController {

	@Autowired
	private PrivateQnaService privateQnaService;

	@GetMapping(path= {"/privateqnalist"}) //리스트
	public String list(Model model, HttpSession session ) {


		/// 본인이 쓴글만 볼수있게하기 



		MemberDto loggedInUser = (MemberDto) session.getAttribute("loginuser");

		if (loggedInUser == null) { return "redirect:/account/login"; }

		int currentUserId = loggedInUser.getMemberNo(); 
		List<PrivateQnaDto> qnaBoardList = privateQnaService.listBoardByUserId(currentUserId);




		////


		//모든 리스트 조회해오는거 // 나중에 관리자가 써야할수도 
		//		 List<PrivateQnaDto>qnaBoardList = privateQnaService.listBoard(); 


		/////// 작성자 조회 부분 
		for (PrivateQnaDto qna : qnaBoardList) {
			String memberId = privateQnaService.getMemberIdByQnaNo(qna.getQnaNo());
			qna.setMemberId(memberId);
		}
		//////////



		//////////////////////////////////////////////  답변 여부 
		for (PrivateQnaDto qna : qnaBoardList) {
			boolean answered = privateQnaService.getAnswerStatus(qna.getQnaNo());
			qna.setAnswered(answered);
		}
		//////////////////////////////////////////	 



		model.addAttribute("qnaBoardList",qnaBoardList);

		if (session.getAttribute("loginuser") == null) { 

			return "redirect:/account/login";

		}

		return "privateboard/privateqnalist";
	}


	@GetMapping(path = {"/privateqnawrite"}) //롸이트
	public String showWriteForm(HttpSession session) {

		/* MemberDto member =(MemberDto)session.getAttribute("loginuser"); */ 


		if (session.getAttribute("loginuser") == null) { 
			return"redirect:/account/login";
		}
		return "privateboard/privateqnawrite";


	}

	@PostMapping(path= {"/privateqnawrite"}) //롸이트,업로드
	public String write(PrivateQnaDto privateqna, MultipartFile attach, HttpServletRequest req) {


		if(!attach.isEmpty()) {
			try {
				String uploadDir = req.getServletContext().getRealPath("/resources/upload");	
				attach.transferTo(new File(uploadDir, attach.getOriginalFilename()));

			}catch (Exception ex) {
				ex.printStackTrace();
			}		

		}


		/////////////////////로그인 사용자 정보로 글 작성자 설정 
		HttpSession session = req.getSession();
		int memberNo = ((MemberDto) session.getAttribute("loginuser")).getMemberNo();
		privateqna.setMemberNo(memberNo);
		////////////////

		// System.out.println(privateqna); 

		privateQnaService.writeBoard(privateqna);


		return String.format("redirect:privateqnalist?memberNo=%d", privateqna.getMemberNo());

	}
	@GetMapping(path= {"/privateqnadetail"}) //디테일
	public String detail(@RequestParam(defaultValue ="-1") int qnaNo, Model model ,HttpSession session, HttpServletRequest request) {

		if(qnaNo == -1) {
			return "redirect:privateqnalist";
		}
		PrivateQnaDto privateqna = privateQnaService.findQnaBoardByQnaNo(qnaNo);
		// System.out.println(privateqna);

		//////////////////////////////////////////////////////

		/* 나중에 디테일에서 답변여부 필요하면 쓸수도 있음
		 * boolean answered = privateQnaService.getAnswerStatus(qnaNo);
		 * privateqna.setAnswered(answered);
		 */
		///////////////////////////////////////



		int memberNo = -1;
		MemberDto loggedInUser = (MemberDto) session.getAttribute("loginuser");
		if (loggedInUser != null) {
			memberNo = loggedInUser.getMemberNo();
		}



		//  privateqna 객체에 로그인한 사용자의 회원 번호를 추가
		privateqna.setMemberNo(memberNo);
		/////

		request.setAttribute("memberNo", memberNo); // 필요함  privateqnadetail JSP 페이지에서 memberNo 속성을 사용 
													// JSP 페이지에서 ${requestScope.memberNo}를 사용

		/* model.addAttribute("privateqna", privateqna); */
		
		
		String memberId = privateQnaService.getMemberIdByQnaNo(qnaNo); //qnaNo에 해당하는 회원의 아이디를 가져와 privateqna 객체에 설정
		privateqna.setMemberId(memberId);


		///////////////////////////////////////////////// 
		model.addAttribute("privateqna",privateqna);

		return "privateboard/privateqnadetail";
	}









}




