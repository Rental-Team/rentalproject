package com.rentalproject.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderPageDto;
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
	public void insertRentalOrder(RentalOrderPageDto orderDetailList) {
		orderMapper.rentalOrder(orderDetailList);	
		
	}
	
	@Override
	public void insertOrderDetail(OrderDetailDto orderDetail) {
		orderMapper.orderDetail(orderDetail);
	}
	
	// 주문 후 삭제
	@Override
	public void deleteZzimAfterOrder (int Zzim) {
		orderMapper.deleteZzim(Zzim);
	}
	
	// 주문 리스트 띄우기(관리자에서 사용)
	@Override
	public List<OrderDto> orderList(){
		
		return orderMapper.orderListInfo();
		
	}

	@Override
	public void saveOrderDetails(List<OrderDetailDto> orderDetailList) {
		for (OrderDetailDto orderDetail : orderDetailList) {
            insertOrderDetail(orderDetail); // 주문 상세 정보를 저장
        }
	}

//	@Override
//	public RentalOrderDto rentalMemberInfo(int memberNo) {
//		
//		return orderMapper.rentalMemberInfo(memberNo);
//	}

	}
