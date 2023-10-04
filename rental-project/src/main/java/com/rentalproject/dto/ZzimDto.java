package com.rentalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ZzimDto {
	
	private int zzimNo;
	private String memberId;
	private int itemNo;
	private int cartStock;
	private Date addDate;
	
	
}
