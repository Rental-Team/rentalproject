package com.rentalproject.dto;

import lombok.Data;

@Data
public class ItemThumbDto {

	private String uuid;
	private String fileName;
	private String uploadPath;
	
	private int itemNo;

}
