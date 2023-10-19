package com.rentalproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import com.rentalproject.view.DownloadView;
import com.rentalproject.view.DownloadView_PrivateQna;
import com.mysql.cj.Session;
import com.rentalproject.common.Util;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.PrivateQnaAttachDto;
import com.rentalproject.dto.PrivateQnaDto;
import com.rentalproject.service.PrivateQnaService;
import com.rentalproject.ui.ThePager;

@Controller
@RequestMapping(path = {"/privateboard"})
public class PrivateQnaController {

	@Autowired
	private PrivateQnaService privateQnaService;
	
	
	//미답변 목록  
	@GetMapping(path = {"/unanswer-list"})
	public String showUnAnswerlist(Model model,HttpSession session , @RequestParam(defaultValue = "1") int pageNo) {
	
		int pageSize = 10 ;
		int pagerSize =5;
		String linkurl = "unanswer-list";
		int dataCount = privateQnaService.getUnanswerListCount();
		
		int from = (pageNo -1) * pageSize;
		
		
		
		
		MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");	
		
		int memberNo = -1;
		
		if (loginuser != null) {
			memberNo = loginuser.getMemberNo();
			
			
		}
	
		if (memberNo != 17) {
	      
	        return "redirect:/privateboard/privateqnalist"; 
	    }


		List<PrivateQnaDto> unAnswer = privateQnaService.unAnswerlist(from , pageSize);


		for (PrivateQnaDto privateqna : unAnswer) {
			String memberId = privateQnaService.getMemberIdByQnaNo(privateqna.getQnaNo());
			privateqna.setMemberId(memberId);
		}
	
		ThePager pager = new ThePager(dataCount, pageNo, pageSize , pagerSize , linkurl);
		
			model.addAttribute("unAnswer",unAnswer);
			model.addAttribute("memberNo", memberNo);
			model.addAttribute("pager",pager);
			return "privateboard/unanswer-list";
        	
	
	}
	

	




	@GetMapping(path = {"/privateqnalist"}) // 리스트
	public String list(
	        @RequestParam(defaultValue = "1") int pageNo,
	        @RequestParam(value = "qnaNo", required = false) Integer qnaNo,
	        Model model,
	        HttpSession session) {

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
	    String linkUrl = "privateqnalist";

	    int dataCount;
	   
	    List<PrivateQnaDto> qnaBoardList;
	   
	    
	    if (qnaNo != null) {
	        qnaBoardList = privateQnaService.searchByQnaNo(qnaNo);

	        dataCount = qnaBoardList.size(); // 검색 결과의 크기를 데이터 카운트로 설정
	   
	    
	    } else {
	        if (memberNo == 17) {
	            dataCount = privateQnaService.getPrivateQnaCount();
	            int from = (pageNo - 1) * pageSize;
	            qnaBoardList = privateQnaService.listBoard(from, pageSize);
	        } else {
	            dataCount = privateQnaService.getPrivateQnaCountByMemberNo(memberNo);
	            int from = (pageNo - 1) * pageSize;
	            qnaBoardList = privateQnaService.listBoardByMemberNo(memberNo, from, pageSize);
	        }
	    }


		/////// 작성자 조회 부분 
		for (PrivateQnaDto privateqna : qnaBoardList) {
			String memberId = privateQnaService.getMemberIdByQnaNo(privateqna.getQnaNo());
			privateqna.setMemberId(memberId);
		}
		//////////



		//////////////////////////////////////////////  답변 여부 
		for (PrivateQnaDto privateqna : qnaBoardList) {
			boolean answered = privateQnaService.getAnswerStatus(privateqna.getQnaNo());
			privateqna.setAnswered(answered);
		}
		//////////////////////////////////////////	 

		ThePager pager =new ThePager(dataCount,pageNo,pageSize,pagerSize,linkUrl);
			
		model.addAttribute("qnaBoardList", qnaBoardList);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("memberNo", memberNo); // 미답변 목록 조회 하기 할떄 memberNo17번만 사용해야해서 필요 
		if (session.getAttribute("loginuser") == null) { 

		return "redirect:/account/login";

		}

		return "privateboard/privateqnalist";
	}


	@GetMapping(path = {"/privateqnawrite"}) //롸이트 1대1 작성 글 불러오기 
	public String showWriteForm(HttpSession session) {

		/* MemberDto member =(MemberDto)session.getAttribute("loginuser"); */ 


		if (session.getAttribute("loginuser") == null) { 
		return"redirect:/account/login";
		}
		return "privateboard/privateqnawrite";


	}

	@PostMapping(path= {"/privateqnawrite"}) //롸이트,업로드
	public String write(PrivateQnaDto privateqna, MultipartFile attach,
										HttpServletRequest req){


		/*
		 * if(!attach.isEmpty()) { try { String uploadDir =
		 * req.getServletContext().getRealPath("/resources/upload");
		 * attach.transferTo(new File(uploadDir, attach.getOriginalFilename()));
		 * 
		 * }catch (Exception ex) { ex.printStackTrace(); }
		 * 
		 * }
		 */
		
		
		
		String uploadAttachFile= req.getServletContext().getRealPath("/resources/upload");
		ArrayList<PrivateQnaAttachDto> privateQnaAttachList = handleUploadFile(attach, uploadAttachFile);
		privateqna.setPrivateQnaAttachList(privateQnaAttachList);
		
	
		
		/////////////////////로그인 사용자 정보로 글 작성자 설정 
		HttpSession session = req.getSession();
		int memberNo = ((MemberDto) session.getAttribute("loginuser")).getMemberNo();
		privateqna.setMemberNo(memberNo);
		////////////////

		// System.out.println(privateqna); 

		privateQnaService.writeBoard(privateqna);


		return String.format("redirect:privateqnalist?memberNo=%d", privateqna.getMemberNo());

	}
	
	private ArrayList<PrivateQnaAttachDto> handleUploadFile(MultipartFile attach, String uploadAttachFile){
		ArrayList<PrivateQnaAttachDto> privateQnaAttachList = new ArrayList<>();
		if (attach != null && !attach.isEmpty()) {
			try {
				String uploadFileName = Util.makeUniqueFileName(attach.getOriginalFilename());
				
				attach.transferTo(new File(uploadAttachFile, uploadFileName));   // 첨부파일 저장 코드 
				
		PrivateQnaAttachDto privateQnaAttach = new PrivateQnaAttachDto();
		privateQnaAttach.setAttachFileName(attach.getOriginalFilename());
		privateQnaAttach.setSavedFileName(uploadFileName);
		
		privateQnaAttachList.add(privateQnaAttach);
		
		
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return privateQnaAttachList;
		
		
	}
	
	
	//첨부파일 조회 및 다운로드하기 
	@GetMapping(path = {"/download"})
	public View download(int attachNo, Model model) {
		
		
		
		// 첨부파일 조회 //
		PrivateQnaAttachDto privateQnaAttach = privateQnaService.selectPrivateQnaAttachByAttachNo(attachNo);
		
		
		
		
		model.addAttribute("attach", privateQnaAttach);
		DownloadView_PrivateQna downloadView = new DownloadView_PrivateQna();
		
		return downloadView;
		
	}
		
		

	
	@GetMapping(path= {"/privateqnadetail"}) //디테일
	public String detail(@RequestParam(defaultValue ="-1") int qnaNo,
						 @RequestParam(defaultValue = "-1") int pageNo,
						 Model model ,HttpSession session, HttpServletRequest request) {

		
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
		MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");
		
		if (loginuser != null) {
			memberNo = loginuser.getMemberNo();
			
			
		}



		//  privateqna 객체에 로그인한 사용자의 회원 번호를 추가
		privateqna.setMemberNo(memberNo);
		/////

		 //<model로 변경>
		  request.setAttribute("memberNo", memberNo); // 필요함 privateqnadetail JSP 페이지에서
		  //memberNo 속성을 사용 // JSP 페이지에서 ${requestScope.memberNo}를 사용 
		 
	
		//model.addAttribute("memberNo", memberNo); //request.setAttribute("memberNo", memberNo); 대신 사용 
		
		/* model.addAttribute("privateqna", privateqna); */
		
		
		String memberId = privateQnaService.getMemberIdByQnaNo(qnaNo); //qnaNo에 해당하는 회원의 아이디를 가져와 privateqna 객체에 설정//디테일에서도 작성자 볼수있게 
		privateqna.setMemberId(memberId);


		///////////////////////////////////////////////// 
		model.addAttribute("privateqna",privateqna);
		model.addAttribute("pageNo",pageNo);
		return "privateboard/privateqnadetail";
	}

/////////////////////////////////////
	
//	@GetMapping("/searchByMemberId")
//	public String searchByMemberId(@RequestParam("memberId") String memberId, Model model) {
//	    List<PrivateQnaDto> searchResult = privateQnaService.searchByMemberId(memberId);
//	    model.addAttribute("searchResult", searchResult);
//	    return "privateboard/searchResult"; // 검색 결과를 표시할 뷰 페이지 이름
//	}

	
	
	/*
	 * // 검색
	 * 
	 * @GetMapping(path={"/searchByQnaNo"}) public String
	 * searchByQnaNo(@RequestParam("qnaNo") int qnaNo, Model model,HttpSession
	 * session) {
	 * 
	 * 
	 * 
	 * List<PrivateQnaDto> searchResult = privateQnaService.searchByQnaNo(qnaNo);
	 * 
	 * 
	 * 
	 * /////// 작성자 조회 부분 for (PrivateQnaDto privateqna : searchResult) { String
	 * memberId = privateQnaService.getMemberIdByQnaNo(privateqna.getQnaNo());
	 * privateqna.setMemberId(memberId); }
	 * 
	 * 
	 * MemberDto loginuser = (MemberDto) session.getAttribute("loginuser");
	 * 
	 * int memberNo = -1;
	 * 
	 * if (loginuser != null) { memberNo = loginuser.getMemberNo();
	 * 
	 * 
	 * }
	 * 
	 * if (memberNo != 17) {
	 * 
	 * return "redirect:/privateboard/privateqnalist"; }
	 * 
	 * 
	 * model.addAttribute("searchResult", searchResult);
	 * model.addAttribute("memberNo",memberNo);
	 * 
	 * 
	 * 
	 * return "privateboard/searchResult"; // 검색 결과를 표시할 뷰 페이지 이름
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	
	
	
	
//	
//	
	
	/*
	 * @GetMapping(path={"/searchInquiryByMemberId"}) public String
	 * searchMember(@RequestParam("memberId") String memberId, Model model) { //
	 * privateQnaService를 사용하여 memberId로 검색한 결과를 얻음 List<PrivateQnaDto> qnaBoardList
	 * = privateQnaService.searchByMemberId(memberId);
	 * 
	 * model.addAttribute("qnaBoardList", qnaBoardList);
	 * 
	 * return "privateboard/searchResult"; // 결과를 보여줄 뷰 이름 }
	 */
	
	
	/*
	 * @GetMapping(path= {"/searchResult"}) public String searchMember(Model model)
	 * { // privateQnaService를 사용하여 memberId로 검색한 결과를 얻음
	 * 
	 * 
	 * return "privateboard/unanswer-list3"; // 결과를 보여줄 뷰 이름 }
	 */




}




