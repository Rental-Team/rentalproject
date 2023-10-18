package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemQnaDto;
import com.rentalproject.mapper.ItemQnaMapper;

public class ItemQnaServiceImpl implements ItemQnaService{

	@Autowired
	private ItemQnaMapper itemQnaMapper;
	
	 @Override
	    public void writeItemQna(ItemQnaDto itemQna) {
	        itemQnaMapper.insertItemQna(itemQna);
	        itemQnaMapper.updateParents(itemQna.getItemqnaNo(), itemQna.getItemqnaNo());		
	 }

	@Override
	public List<ItemQnaDto> listItemQna(int itemNo) {
		List<ItemQnaDto> itemQnaList = itemQnaMapper.selectItemQnaByItemNo(itemNo);
		
		  return itemQnaList;
	}
	 @Override
	 public void deleteItemQna(int itemqnaNo) {
		 itemQnaMapper.deleteItemQna(itemqnaNo);
		 
		 
		 
		 
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	@Override
	public void editItemQna(ItemQnaDto itemQna) {
		itemQnaMapper.editItemQna(itemQna);
		
	}

	
	
	
	
	@Override
	public ItemQnaDto findItemQnaByItemQnaNo(int itemqnaNo) {
		ItemQnaDto itemQna = itemQnaMapper.selectItemQnaByItemQnaNo(itemqnaNo);
			
		return itemQna;
	}

	

	
	
	@Override
	public void updateSeqeunce(ItemQnaDto itemQna) {
		itemQnaMapper.updateSequence(itemQna);
		
	}
	
	
	
	@Override
	public void writeReplyQna(ItemQnaDto itemQna) {
		itemQnaMapper.insertReplyQna(itemQna);
		
	}

	
	
	 
	 
	}
