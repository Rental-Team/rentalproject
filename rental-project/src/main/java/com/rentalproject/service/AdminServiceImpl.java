package com.rentalproject.service;

import java.util.List;

import javax.mail.FetchProfile.Item;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemAttachDto;
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
	public ItemDto findItemByItemNo(int itemNo) {
		ItemDto item = adminMapper.read(itemNo);
		
		if (item != null) {
			List<ItemAttachDto> attachList = adminMapper.selectItemAttachByItemNo(itemNo);
			item.setItemAttachList(attachList);
		}
		
		return item;
	}


	@Override
	public ItemAttachDto findItemAttachByAttachNo(int attachNo) {
		ItemAttachDto attach = adminMapper.selectItemAttachByAttachNo(attachNo);
		return attach;
	}
	
	@Override
	public void editItem(ItemDto item) {
		
		adminMapper.updateItem(item);
		
		for (ItemAttachDto attach : item.getItemAttachList()) {
			attach.setItemNo(item.getItemNo());
			adminMapper.insertItemAttach(attach);
		}
	}
	
	
	@Override
	public void deleteItem(int itemNo) {
		
		
		adminMapper.deleteBoard(itemNo);
	}
	
	@Override
	public void writeItem(ItemDto item) {
		

		adminMapper.insertItem(item);
		
		for (ItemAttachDto attach : item.getItemAttachList()) {
			attach.setItemNo(item.getItemNo());
			adminMapper.insertItemAttach(attach);
		}
	}
	

}