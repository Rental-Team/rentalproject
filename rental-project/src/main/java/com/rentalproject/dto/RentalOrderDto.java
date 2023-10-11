package com.rentalproject.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

// 대여 주문 테이블
@Data
public class RentalOrderDto {
	
	private String orderId;
	private String orderState;
	private Date orderDate;
	
	// 회원에서 받아올 값
	private String addressUser;
	private int memberNo;
	private String memberAddr1;
	private String memberAddr2;
	private String memberAddr3;
	
	// 주문은 여러개의 상품을 주문할 수 있다. 상품 정보는 주문 상세 테이블로 받아온다.
	List<OrderDetailDto> orderDetailList;
}
