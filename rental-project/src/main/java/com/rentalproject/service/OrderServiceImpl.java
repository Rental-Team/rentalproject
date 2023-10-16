package com.rentalproject.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.mapper.OrderMapper;

public class OrderServiceImpl implements OrderServcie {
	
	@Autowired
	private OrderMapper orderMapper;
	
//	@Autowired
//	private AdminMapper adminMapper;

	// 상품 정보 받기
	@Override
	public OrderDetailDto rentalItemInfo(int itemNo) {
		
		return orderMapper.rentalItemInfo(itemNo);
				
	}

	// 주문 등록
	@Override
	public void insertRentalOrder(RentalOrderPageDto order) {
		
		
		orderMapper.rentalOrder(order);	
		
		for (OrderDetailDto orderDetail : order.getOrderDetailList()) {
			orderDetail.setOrderItemNo(order.getOrderId());
			orderMapper.orderDetail(orderDetail);
		}
		
	}
	
	
	
	// 주문 후 삭제
	@Override
	public void deleteZzimAfterOrder (int Zzim) {
		orderMapper.deleteZzim(Zzim);
	}
	
	// 주문 리스트 띄우기(관리자에서 사용)
	@Override
	public List<RentalOrderPageDto> orderList(){
		
		return orderMapper.orderListInfo();
		
	}

//	@Override
//	public RentalOrderDto rentalMemberInfo(int memberNo) {
//		
//		return orderMapper.rentalMemberInfo(memberNo);
//	}
	
}
