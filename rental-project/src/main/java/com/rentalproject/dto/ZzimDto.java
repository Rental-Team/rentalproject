package com.rentalproject.dto;



import lombok.Data;

@Data
public class ZzimDto {
	
	
	private int zzimNo;
	private String memberId;
	private int itemNo;
	private int itemCount;
	
	// 상품 
	private String itemName;
	private int itemPrice;
	
	// 포인트
	private int point;
	private int totalPoint;
	

}
