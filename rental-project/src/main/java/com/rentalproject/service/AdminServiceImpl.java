package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.AdminMapper;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<MemberDto> MemberList() {
		
		
		return adminMapper.allMemberList();
	}
	
	@Override
	public List<ItemDto> ItemList() {
		
		
		return adminMapper.allItemList();
	}
	
	@Override
	public ItemDto itemDetail(int itemNo) {
		
		
		return adminMapper.read(itemNo);
	}
	
	@Override
	public int getItemCount() {
		
		int count = adminMapper.selectItemCount();
		
		return count;
	}
	
	@Override
	public List<ItemDto> listItemByPage(int from, int count) {
		
		List<ItemDto> itemList = adminMapper.selectItemByPage(from, count);
		
		return itemList;
		
	}


	
	
	@Override
	public void editItem(ItemDto item) {
		
		adminMapper.updateItem(item);
		
	}
	
	
	@Override
	public void deleteItem(int itemNo) {
		
		
		adminMapper.deleteBoard(itemNo);
	}
	
	@Override
	public void writeItem(ItemDto item) {
		// TODO Auto-generated method stub

		//log.info("write....." + item);

		adminMapper.insertItem(item);
	}
	

}
