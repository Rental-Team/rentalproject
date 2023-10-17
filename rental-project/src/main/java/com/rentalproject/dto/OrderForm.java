package com.rentalproject.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderForm {
	private List<OrderDetailDto> orderDetailDto;
	
	 public OrderForm() { 
	    }

	public RentalOrderPageDto toRentalOrderPageDto() {
		 RentalOrderPageDto rentalOrderPageDto = new RentalOrderPageDto();
	        // OrderForm에서 RentalOrderPageDto로 필드 복사 작업 수행
	        rentalOrderPageDto.setOrderDetailList(orderDetailDto);
	        // 다른 필드 복사 작업 추가
	        return rentalOrderPageDto;
	} 

}
