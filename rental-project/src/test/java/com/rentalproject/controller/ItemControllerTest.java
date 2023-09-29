package com.rentalproject.controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.mapper.ItemMapper;
import com.rentalproject.service.ItemService;


class ItemControllerTest {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemMapper itemMapper;

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("ItemController 테스트")
	public void controllerTest() throws Exception {
		
		//given
		ItemDto item = new ItemDto();
		itemService.writeItem(item);
		
		//when 
		itemMapper.insertItem(item);
		
		//then
		ItemDto result = itemMapper.read(item.getItemNo());
		assertThat(result, item);
		
	}

	private void assertThat(ItemDto result, ItemDto item) {
		// TODO Auto-generated method stub
		
	}
}
