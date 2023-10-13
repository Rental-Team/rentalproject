package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderDto;

public interface OrderServcie {

	// 주문 상품 정보 불러오기
	OrderDetailDto rentalItemInfo(int itemNo);

	// 주문 등록
	void insertOrder(OrderDto order);
	
	// 주문 리스트 (관리자에서 사용)
	public List<RentalOrderDto> orderList();
	
	// 주문 멤버 정보 불러오기
	RentalOrderDto rentalMemberInfo(int memberNo);
}
