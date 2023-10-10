package com.rentalproject.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderPageDto {
	
	private List<OrderPageItemDto> orders;
	
	
	private List<OrderPageItemDto> getOrders() {
		
		return orders;
	}
	
	
}
