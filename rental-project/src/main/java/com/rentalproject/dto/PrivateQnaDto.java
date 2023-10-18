package com.rentalproject.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class PrivateQnaDto {
	
	private int qnaNo; // 문의번호
	private String qnaType; // 문의유형
	private String qnaTitle;//문의제목 
	private String qnaContent;//문의 내용 
	private Date  qnaDate;//문의 작성일자
	private int memberNo;//회원번호
	private String memberId; // 회원 ID
	private boolean answered; //답변 여부 
	private List<PrivateQnaAnswerDto> privateQnaAnswerList;
	private List<MemberDto> privateQnaWriteList;
	
	private List<PrivateQnaAttachDto> privateQnaAttachList;
}
