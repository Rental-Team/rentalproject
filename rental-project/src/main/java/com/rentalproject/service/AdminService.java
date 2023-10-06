package com.rentalproject.service;

import java.util.List;


import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;

public interface AdminService {
	
	public void writeItem(ItemDto item);
	
	public List<MemberDto> MemberList();

	public List<ItemDto> ItemList();
	
	public ItemDto itemDetail(int itemNo);
	
	public void editItem(ItemDto item);
	
	public void deleteItem(int itemNo);
	
	public int getItemCount();
	
	public List<ItemDto> listItemByPage(int from, int count);
		
	public ItemAttachDto findItemAttachByAttachNo(int attachNo);
	
	public ItemDto findItemByItemNo(int itemNo);

	public void writeNotice(NoticeDto notice);

	public List<NoticeDto> listNotice();

	public NoticeDto findNoticeByNoticeNo(int noticeNo);

	public void updateviewCount(int noticeNo);

	public void editNotice(NoticeDto notice);

}


