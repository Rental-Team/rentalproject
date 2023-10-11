package com.rentalproject.dto;

import lombok.Data;

@Data
public class OrderPageItemDto {
	
	// 뷰에서 전달 받을 값
	private int itemNo;
	private int itemCount;
	
	// DB에서 가져올 값
	private String itemName;
	private int itemPrice;
	
	// 만들어 낼 값
	private int totalPrice;
	private int point;
	private int totalPoint;
	
	public void initTotal() {
		this.totalPrice = this.totalPrice*itemCount;
		this.point = (int)(Math.floor(this.totalPoint*0.05));
		this.totalPoint = this.point*this.itemCount;
	}
	
}
