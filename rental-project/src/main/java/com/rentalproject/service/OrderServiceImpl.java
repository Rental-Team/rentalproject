package com.rentalproject.service;



import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.mapper.OrderMapper;

public class OrderServiceImpl implements OrderServcie {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public OrderDetailDto rentalItemInfo(int itemNo) {
		
		return orderMapper.rentalItemInfo(itemNo);
		
		
	}

	@Override
	public void insertOrder(OrderDto order) {
		orderMapper.rentalOrder(order);
		
	}
	
}
