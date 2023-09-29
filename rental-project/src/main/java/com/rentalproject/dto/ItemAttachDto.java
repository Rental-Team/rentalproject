package com.rentalproject.dto;

import lombok.Data;

@Data
public class ItemAttachDto {
	
	private int attachNo;
	private int itemNo;
	private String userFileName;
	private String savedFileName;
	
}
