package com.rentalproject.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FreeBoardDto { 
	
	private int freeBoardNo; 
	private int adminId;  
	private int memberNo;
	private String memberId;
	private String memberImage;
	private String freeBoardTitle;
	private String freeBoardContent;
	private Date freeBoardDate;
	private int freeBoardViewCount; 
	private boolean freeBoardDelete;
	
	//검색 기능 구현
	private String type;
	private String keyword;
	
	private List<FreeBoardReviewDto> freeBoardReviewList;
	private List<FreeBoardAttachDto> freeBoardAttachList; 
}
