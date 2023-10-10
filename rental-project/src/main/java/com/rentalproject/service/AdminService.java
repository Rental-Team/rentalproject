package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;

public interface AdminService {
	
	// 상품 입력
	public void writeItem(ItemDto item);
	
	// 멤버 리스트 출력
	public List<MemberDto> MemberList();
	
	// 카테고리 리스트
	public List<CategoryDto> cateList();

	// 상품 리스트
	public List<ItemDto> ItemList();
	
	public ItemDto itemDetail(int itemNo);
	
	public void editItem(ItemDto item);
	
	public void deleteItem(int itemNo);
	
	public int getItemCount();
	
	public List<ItemDto> listItemByPage(int from, int count);
		
	public ItemAttachDto findItemAttachByAttachNo(int attachNo);
	
	//public ItemDto findItemByItemNo(int itemNo);
	

	public void writeNotice(NoticeDto notice);

	public List<NoticeDto> listNotice();

	public NoticeDto findNoticeByNoticeNo(int noticeNo);

	public void updateviewCount(int noticeNo);

	public void editNotice(NoticeDto notice);

}


