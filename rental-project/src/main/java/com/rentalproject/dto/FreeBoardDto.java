package com.rentalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FreeBoardDto { 
	
	private int freeBoardNo;
	private int memberNo;
	private int adminId;
	private String freeBoardTitle;
	private String freeBoardContent;
	private Date freeBoardDate;
	private int freeBoardViewCount;
	private boolean freeBoardDelete;

}
