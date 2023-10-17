package com.rentalproject.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
		// 대여 주문 번호
		private int orderId;
		
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

		List<OrderItemDto> orders;
}
