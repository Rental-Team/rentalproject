package com.rentalproject.service;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;

public interface OrderServcie {

	OrderDetailDto rentalItemInfo(int itemNo);

	void insertOrder(OrderDto order);
}
