package com.rentalproject.dto;


import lombok.Data;

// 주문 상품 상세 
@Data
public class OrderDetailDto {
	
	// 주문 상세 번호
	private int OrderDetailNo;
	
	// 주문에서 받을 값
	private String orderId;  // 주문 번호
	
	// 상품에서 받을 값
	private int itemNo;  
	private int itemCount;
	private int itemPrice;
	
}
