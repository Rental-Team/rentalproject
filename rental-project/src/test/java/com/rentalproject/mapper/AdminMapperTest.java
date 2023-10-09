package com.rentalproject.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentalproject.dto.ItemDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/java/com/rentalproject.config/RootConfiguration.config")
class AdminMapperTest {
	
	@Autowired
	private AdminMapper adminMapper;

	@Test
	void testInsertItem() {
		
		ItemDto item = new ItemDto();
		
		item.setItemName("상품 A1");
		item.setCateCode("100");
		item.setItemPrice(10000);
		item.setItemStock(100);
		item.setItemDetail("테스트 상품 입니다");
		
		adminMapper.insertItem(item);
	}

}
