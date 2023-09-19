package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.mapper.ItemMapper;

public class ItemServiceImpl {
	
	@Autowired
	private ItemMapper itemMapper;
	
	
	public void writeBoard(ItemDto item) {
		
		itemMapper.insertItem(item);
		
		
	}
	

}
