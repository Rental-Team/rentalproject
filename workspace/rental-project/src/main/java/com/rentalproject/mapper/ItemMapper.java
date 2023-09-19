package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.rentalproject.dto.ItemDto;

@Mapper
public interface ItemMapper {

	@Insert( "insert into item ( itemCode, itemName, itemDate, itemPrice "
			+ "values ( #{ itemCode }, #{ itemName }, #{ itemDate }, #{ itemPrice } ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	void insertItem(ItemDto item);
	
	
}
