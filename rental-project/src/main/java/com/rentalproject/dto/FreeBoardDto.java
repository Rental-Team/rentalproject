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
	private String freeBoardTitle;
	private String freeBoardContent;
	private Date freeBoardDate;
	private int freeBoardViewCount;
	private boolean freeBoardDelete;
	
	private List<FreeBoardReviewDto> freeBoardReviewList;
	private List<FreeBoardAttachDto> freeBoardAttachList; 
}
