package com.rentalproject.service;

import com.rentalproject.dto.ItemDto;

import java.util.List;

public interface ItemService {
	
	public void writeItem(ItemDto item);

	public ItemDto detail(int itemNo);

	public List<ItemDto> getList();

	public void editItem(ItemDto item);
	
	public void deleteItem(int itemNo);
	
	public int getItemCount();
	
	public List<ItemDto> listItemByPage(int from, int count);

}
