package com.rentalproject.dto;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private int orderItemNo;
	private String orderId;
	private int itemNo;
	private int itemCount;
	private int itemPrice;
	
}
