package com.rentalproject.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.mapper.ItemMapper;

import java.util.List;

//@Log4j
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;
	

	@Override
	public void writeItem(ItemDto item) {
		// TODO Auto-generated method stub

		//log.info("write....." + item);

		itemMapper.insertItem(item);
	}


	@Override
	public List<ItemDto> getList() {

		//log.info("getList......");

		return itemMapper.getList();
	}


	@Override
	public ItemDto detail(int itemNo) {
		
		ItemDto item = itemMapper.read(itemNo);
		
		
		//log.info("get....." + itemNo);

		return item;
	}
	
	@Override
	public int getItemCount() {
		
		int count = itemMapper.selectItemCount();
		
		return count;
	}
	
	@Override
	public List<ItemDto> listItemByPage(int from, int count) {
		
		List<ItemDto> itemList = itemMapper.selectItemByPage(from, count);
		
		return itemList;
		
	}


	
	
	@Override
	public void editItem(ItemDto item) {
		
		itemMapper.updateItem(item);
		
	}
	
	
	@Override
	public void deleteItem(int itemNo) {
		
		
		itemMapper.deleteBoard(itemNo);
	}
	

}
