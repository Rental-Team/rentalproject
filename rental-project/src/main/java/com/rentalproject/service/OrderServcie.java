package com.rentalproject.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderPageDto;

public interface OrderServcie {

	// 주문 상품 정보 불러오기
	OrderDetailDto rentalItemInfo(int itemNo); 
	void insertOrderDetail(OrderDetailDto orderDetail);
	
	
	// 주문 리스트 (관리자에서 사용)
	public List<RentalOrderPageDto> orderList();

	
	// 주문
	void order(RentalOrderPageDto od);
	
	// 주문 멤버 정보 불러오기
		//RentalOrderDto rentalMemberInfo(int memberNo); 


	List<RentalOrderPageDto> orderDetail(int orderId);
		
//	RentalOrderPageDto findOrderDetailByOrderId(int orderId);
		
	RentalOrderPageDto getAddress(int orderId); 
		
}
