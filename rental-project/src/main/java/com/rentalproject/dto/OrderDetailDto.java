package com.rentalproject.dto;


import lombok.Data;

// 주문 상품 상세 
//주문 상품 상세 
@Data
public class OrderDetailDto {
	
	// 주문 상세 번호
	private int OrderItemNo;
	
	// 주문에서 받을 값
	private int orderId;  // 대여 아이디(주문 번호)
	
	// 상품에서 받을 값
	private int itemNo;  
	private int itemCount;
	private int itemPrice;
	private String itemName;
	private String thumbnail;
}