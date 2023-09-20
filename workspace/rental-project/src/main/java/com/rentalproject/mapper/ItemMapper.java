package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.rentalproject.dto.ItemDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {

	@Insert( "insert into Item ( itemDetail , itemCode, itemName, itemPrice, categoryName) "
			+ "values ( #{ itemDetail }, #{ itemCode }, #{ itemName }, #{ itemPrice }, #{categoryName }) ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	public void insertItem(ItemDto item);



	@Select("select itemNo, itemName, viewCount, itemDate " +
			"from Item " +
			"order by itemNo desc")
	public List<ItemDto> getList();


	@Select("select ( itemNo, itemCode, itemDate, itemPrice, itemDetail, itemPhoto) " +
			"from Item " +
			"where itemNo = #{ itemNo } ")
	public ItemDto read(int itemNo);
	
	
	
}
