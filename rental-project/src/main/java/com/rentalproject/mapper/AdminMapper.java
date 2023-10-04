package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;

@Mapper
public interface AdminMapper {

	
	@Insert( "insert into Item ( itemDetail , itemCode, itemName, itemPrice, categoryName ) "
			+ "values ( #{ itemDetail }, #{ itemCode }, #{ itemName }, #{ itemPrice }, #{categoryName }) ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	public void insertItem(ItemDto item);
	
	@Insert( "insert into itemAttach (attachNo, itemNo, userFileName, savedFileName) "
			+ "values (#{attachNo}, #{itemNo}, #{userFileName}, #{savedFileName}) ") 
	public void insertItemAttach(ItemAttachDto attach);
	
	@Select("select memberId, userName, phoneNo, regDate "
			+ "from Member ")
	public List<MemberDto> allMemberList();
	
	
	@Select("select itemNo, itemName, viewCount, itemDate, deleted "
			+ "from Item "
			+ "order by itemNo desc")
	public List<ItemDto> allItemList();
	
	@Select("select itemNo, itemName, itemCode, itemDate, itemPrice, itemDetail, categoryName " +
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
	
	@Select(  "select attachNo, itemNo, userFileName, savedFileName "
			+ "from itemAttach "
			+ "where itemNo = #{ itemNo }")
	List<ItemAttachDto> selectItemAttachByItemNo(@Param("itemNo") int itemNo);
	
	@Select(  "select attachNo, itemNo, userFileName, savedFileName "
			+ "from itemAttach "
			+ "where attachNo = #{ attachNo }")
	ItemAttachDto selectItemAttachByAttachNo(@Param("attachNo") int attachNo);
}