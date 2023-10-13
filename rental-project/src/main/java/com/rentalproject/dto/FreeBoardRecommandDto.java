package com.rentalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FreeBoardRecommandDto {

	private int freeBoardRecommandNo;
	private int memberNo;
	private String memberId;
	private int freeBoardNo;
	private Date recommandDate;
	
}
