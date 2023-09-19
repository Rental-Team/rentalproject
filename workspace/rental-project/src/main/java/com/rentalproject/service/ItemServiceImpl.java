package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.mapper.ItemMapper;

public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;
	

	@Override
	public void writeItem(ItemDto item) {
		// TODO Auto-generated method stub
		
		itemMapper.insertItem(item);
	}
	

}
