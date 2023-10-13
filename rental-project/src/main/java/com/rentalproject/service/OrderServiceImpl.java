package com.rentalproject.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderDto;
import com.rentalproject.mapper.OrderMapper;

public class OrderServiceImpl implements OrderServcie {
	
	@Autowired
	private OrderMapper orderMapper;

	// 상품 정보 받기
	@Override
	public OrderDetailDto rentalItemInfo(int itemNo) {
		
		return orderMapper.rentalItemInfo(itemNo);
				
	}

	// 주문 등록
	@Override
	public void insertOrder(OrderDto order) {
		orderMapper.rentalOrder(order);		
	}
	
	// 주문 리스트 띄우기(관리자에서 사용)
	@Override
	public List<RentalOrderDto> orderList(){
		
		return orderMapper.orderListInfo();
		
	}

	@Override
	public RentalOrderDto rentalMemberInfo(int memberNo) {
		
		return orderMapper.rentalMemberInfo(memberNo);
	}
	
}
