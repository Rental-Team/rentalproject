package com.rentalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDto {
	// 대여 번호
		private String orderId;
		
		// 대여 상태(대기, 완료)
		private String orderState;
		
		// 대여 날짜
		private Date orderDate;
		
		// 배송 받는 유저
		private String addressUser; 
		

		private String memberId; 
		private int memberNo;  
		private String address;
		private String addressDetail;
		private String email;

		private int OrderDetailNo;

		private int itemNo;  
		private int itemCount;
		private int itemPrice;
		private int orderItemNo;
}
