package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.rentalproject.dto.ItemDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {

	@Insert( "insert into item ( itemCode, itemName, itemDate, itemPrice "
			+ "values ( #{ itemCode }, #{ itemName }, #{ itemDate }, #{ itemPrice } ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	public void insertItem(ItemDto item);


	@Select("select ( itemNo, itemCode, itemDate, itemPrice, itemDetail, itemPhoto) " +
			"from item " +
			"where itemNo > 0")
	public List<ItemDto> getList();


	@Select("select ( itemNo, itemCode, itemDate, itemPrice, itemDetail, itemPhoto) " +
			"from item " +
			"where itemNo = #{ itemNo } ")
	public ItemDto read(int itemNo);
}
