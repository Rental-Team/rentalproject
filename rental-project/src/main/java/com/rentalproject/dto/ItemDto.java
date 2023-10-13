package com.rentalproject.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ItemDto {
	
	
	private int itemNo;
	private String itemName;
	private Date itemDate;
	private int itemPrice;
	private boolean itemRentalCheck;
	private boolean itemReserv;
	private String itemDetail;
	private int viewCount;
	private boolean deleted;
	private String cateCode;
	private int itemStock;
	private String cateName;
	private String memberId;
	private String thumbnail;
	
	private List<ItemReviewDto> itemReviewList;
	
	// item 테이블과 ItemAttach 테이블 간의 1대 N, 한 상품에 여러개의 상품 첨부파일이 들어갈 수 있다.
	private List<ItemAttachDto> itemAttachList;
	
	// 한 상품은 여러 유저가 주문할 수 있다.
	private List<OrderDetailDto> orderDetailList;

	private List<ZzimDto> zzimDtoList;

}
