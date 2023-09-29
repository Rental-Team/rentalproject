package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;

public interface AdminService {
	
	public void writeItem(ItemDto item);
	
	public List<MemberDto> MemberList();

	public List<ItemDto> ItemList();
	
	public ItemDto itemDetail(int itemNo);
	
	public void editItem(ItemDto item);
	
	public void deleteItem(int itemNo);
	
	public int getItemCount();
	
	public List<ItemDto> listItemByPage(int from, int count);
		
}
