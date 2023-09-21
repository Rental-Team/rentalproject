package com.rentalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ItemDto {
	
	private int itemNo;
	private String categoryName;
	private String itemCode;
	private String itemName;
	private Date itemDate;
	private int itemPrice;
	private boolean itemRentalCheck;
	private boolean reserv;
	private String itemDetail;
	private String itemPhoto;
	private String itemsort;
	private int viewCount;
	private boolean deleted;
	
}
