package com.rentalproject.dto;



import lombok.Data;

@Data
public class ZzimDto {
	
	
	private int zzimNo;
	private int memberNo;
	private int itemNo;
	private int itemCount;
	
	// 유저
	private String memberId;
	
	// 상품 
	private String itemName;
	private int itemPrice;
	private int totalPrice;
	
	// 포인트
	private int point;
	private int totalPoint;
	
	// 썸네일
	private String thumbnail;
	

	public void initSaleTotal() {
		this.totalPrice = this.itemPrice*this.itemCount;
		this.point = (int)Math.floor(this.totalPrice*0.05);
	}

}
