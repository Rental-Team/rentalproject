package com.rentalproject.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.ZzimDto;
import com.rentalproject.mapper.AdminMapper;
import com.rentalproject.mapper.ItemMapper;

import java.util.List;

//@Log4j
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void writeItem(ItemDto item) {
		// TODO Auto-generated method stub

		//log.info("write....." + item);

		itemMapper.insertItem(item);
	}


	@Override
	public List<ItemDto> getList() {

		//log.info("getList......");

		List<ItemDto> list = itemMapper.getList();
		
		return list;
	}

	@Override
	public ItemDto detail(int itemNo) {
		
		ItemDto item = itemMapper.read(itemNo);
		
		if(item!=null) {
			List<ItemAttachDto> itemList = itemMapper.selectItemAttachByItemNo(itemNo);
			item.setItemAttachList(itemList);
		}

		return item;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void updateItemViewCount(int itemNo) {
		itemMapper.itemViewCount(itemNo);	
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
	
	@Override
	public void zzim(ZzimDto zzim) {
		
		itemMapper.insertZzim(zzim);
	}


	@Override
	public List<ItemDto> searchItems(String keyword, int from, int count) {
		
		
		return itemMapper.searchItems(keyword, from, count);
	}
	
	@Override
	public ItemDto getItemsInfo(int itemNo) {
		ItemDto itemsInfo = itemMapper.getItemsInfo(itemNo);
		
		return itemsInfo;
	}

}
