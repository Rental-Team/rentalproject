package com.rentalproject.dto;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.Data;

// 대여 주문 
@Data
public class RentalOrderPageDto {
	
	// 대여 아이디
	private int orderId;
	
	// 대여 상태(대기, 완료)
	private String orderState;
	
	// 대여 날짜
	private Date orderDate;
	
	// 배송 받는 유저
	private String addressUser; 
	
	// 회원에서 받아올 값
	private String memberId; 
	private int memberNo;  
	private String address;
	private String addressDetail;
	private String email;

			
	// 대여 주문은 여러개의 상품을 대여할 수 있다. 상품 정보는 주문 상세 테이블로 받아온다.
	// 대여 상품
	List<OrderDetailDto> orderDetailList;
	
	
	public List<OrderDetailDto> getOrderDetailList() {
	    if (orderDetailList == null) {
	        return Collections.emptyList(); 
	    }
	    return orderDetailList;
	}
}
