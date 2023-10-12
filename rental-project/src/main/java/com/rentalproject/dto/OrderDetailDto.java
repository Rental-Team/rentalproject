package com.rentalproject.dto;


import lombok.Data;

// 주문 상품 상세 테이블
@Data
public class OrderDetailDto {

	private int OrderDetailNo;
	
	// 주문에서 받을 값
	private String orderId;  // 주문 번호
	
	// 회원에서 받을 값
	private int itemNo;  
	private int itemCount;
	private int itemPrice;
	
	
}
