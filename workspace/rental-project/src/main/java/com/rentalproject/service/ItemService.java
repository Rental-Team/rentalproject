package com.rentalproject.service;

import com.rentalproject.dto.ItemDto;

import java.util.List;

public interface ItemService {
	
	public void writeItem(ItemDto item);

	public ItemDto get(int itemNo);

	public List<ItemDto> getList();


}
