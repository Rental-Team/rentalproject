package com.rentalproject.service;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.ZzimDto;

import java.util.List;

public interface ItemService {
	
	public void writeItem(ItemDto item);

	public ItemDto detail(int itemNo);
	
	public void updateItemViewCount(int itemNo);

	public List<ItemDto> getList();

	public void editItem(ItemDto item);
	
	public void deleteItem(int itemNo);
	
	public int getItemCount();
	
	public List<ItemDto> listItemByPage(int from, int count);
	
	public void zzim(ZzimDto zzim);

	public List<ItemDto> searchItems(String keyword, int from, int count);

	public ItemDto getItemsInfo(int itemNo);
	
}
