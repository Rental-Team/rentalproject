package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderPageDto;

public interface OrderServcie {

	// 주문 상품 정보 불러오기
	OrderDetailDto rentalItemInfo(int itemNo);

	// 주문 등록
	void insertRentalOrder(RentalOrderPageDto order);
	
	// 주문 상세
	void insertOrderDetail(OrderDetailDto orderDetail);
	
	// 주문 후 찜 목록에서 삭제
	void deleteZzimAfterOrder (int Zzim);
	
	// 주문 리스트 (관리자에서 사용)
	public List<OrderDto> orderList();
	
	// 주문 멤버 정보 불러오기
	//RentalOrderDto rentalMemberInfo(int memberNo);
}
