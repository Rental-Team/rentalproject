package com.rentalproject.dto;

import lombok.Data;

@Data
public class ItemAttachDto {
	
	private int attachNo;
	private String userFileName;
	private String savedFileName;
	
	private int itemNo;
}

