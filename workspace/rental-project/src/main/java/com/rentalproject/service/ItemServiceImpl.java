package com.rentalproject.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.mapper.ItemMapper;

import java.util.List;

@Log4j
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;
	

	@Override
	public void writeItem(ItemDto item) {
		// TODO Auto-generated method stub

		log.info("write....." + item);

		itemMapper.insertItem(item);
	}


	@Override
	public List<ItemDto> getList() {

		log.info("getList......");

		return itemMapper.getList();
	}

	public ItemDto get(int itemNo) {

		log.info("get....." + itemNo);

		return itemMapper.read(itemNo);
	}
	

}
