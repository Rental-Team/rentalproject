package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.rentalproject.dto.ItemDto;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ItemMapper {

	@Insert( "insert into Item ( itemDetail , itemCode, itemName, itemPrice, categoryName) "
			+ "values ( #{ itemDetail }, #{ itemCode }, #{ itemName }, #{ itemPrice }, #{categoryName }) ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	public void insertItem(ItemDto item);



	@Select("select itemNo, itemName, viewCount, itemDate, deleted " +
			"from Item " +
			"order by itemNo desc")
	public List<ItemDto> getList();


	@Select("select  itemNo, itemName, itemCode, itemDate, itemPrice, itemDetail, categoryName " +
			"from Item " +
			"where itemNo = #{ itemNo }")
	public ItemDto read(int itemNo);
	
	
	@Update("update Item "
			+ "set itemName = #{ itemName }, itemDetail = #{ itemDetail } "
			+ "where itemNo = #{ itemNo } ")
	void updateItem(ItemDto item);
	
	@Update("update Item "
			+ "set deleted = true "
			+ "where itemNo = #{ itemNo } ")
	void deleteBoard(@Param("itemNo") int itemNo);
	
	
	@Select("select count(*) "
			+ "from Item")
	int selectItemCount();
	
	@Select("select itemNo, itemName, viewCount, itemDate, itemPrice, deleted " +
			"from Item " +
			"order by itemNo desc "
			+ "limit #{from}, #{count}")
	public List<ItemDto> selectItemByPage(@Param("from") int from, @Param("count") int count);
	
}
