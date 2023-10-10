package com.rentalproject.dto;

import lombok.Data;

@Data
public class CategoryDto {
	
	private int tier;
	private String cateName;
	private String cateCode;
	private String cateParent;
	
}
