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
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.dto.ZzimDto;

@Mapper
public interface AdminMapper {

	

	
	@Insert( "insert into itemAttach (attachNo, itemNo, userFileName, savedFileName) "
			+ "values (#{attachNo}, #{itemNo}, #{userFileName}, #{savedFileName}) ") 
	public void insertItemAttach(ItemAttachDto attach);
	
	@Insert( "insert into Item ( itemDetail , itemCode, itemName, itemPrice, categoryName, itemPhoto, deleted ) "
			+ "values ( #{ itemDetail }, #{ itemCode }, #{ itemName }, #{ itemPrice }, #{categoryName }, #{itemPhoto}) ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	public void insertItem(ItemDto item);
	
	@Select("select memberId, userName, phoneNo, regDate "
			+ "from Member ")
	public List<MemberDto> allMemberList();
	
	
	@Select("select itemNo, itemName, viewCount, itemDate, deleted "
			+ "from Item "
			+ "order by itemNo desc")
	public List<ItemDto> allItemList();
	
	
	@Select("select  itemNo, itemName, itemCode, itemDate, itemPrice, itemDetail, categoryName, itemPhoto " +
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
	
	////////////////////////////////////////////
	// notice
	@Insert("insert into Notice (noticeTitle, noticeContent) "         
			+ "values (#{ noticeTitle }, #{ noticeContent })")
	
	@Options(useGeneratedKeys = true, keyProperty = "noticeNo")             
	
	void writenotice(NoticeDto notice);
	
	
	
	@Select("select noticeNo, noticeTitle, ViewCount, noticeDate "     
			+ " from Notice "
			+ "order by noticeNo desc")
	
	List<NoticeDto> selectAllnotice();
	
	@Select("select noticeNo, noticeTitle, noticeDate, ViewCount, noticeContent " 
			+ "from Notice "
			+ "where noticeNo = #{ noticeNo }")
	NoticeDto selectnoticeBynoticeNo(@Param("noticeNo") int noticeNo);
	
		
	@Update("update Notice "
			+ "set noticeTitle = #{ noticeTitle }, noticeContent = #{ noticeContent } "  
			+ "where noticeNo = #{ noticeNo }")  
	void updatenotice(NoticeDto notice);
	
	
	@Update("update Notice "                                      
			+ "set noticeDelete = true "
			+ "where noticeNo = #{ noticeNo }")
	void deleteNotice(@Param("noticeNo") int noticeNo);  
	
	@Update("update Notice "
	        + "set viewCount = viewCount + 1 " 
	        + "where noticeNo = #{noticeNo}")
	void updateviewCount(@Param("noticeNo") int noticeNo);
}