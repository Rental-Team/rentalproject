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
import com.rentalproject.dto.ZzimDto;

@Mapper
public interface ItemMapper {

	@Insert( "insert into Item ( itemDetail , cateCode, itemName, itemPrice, itemStock ) "
			+ "values ( #{ itemDetail }, #{ cateCode }, #{ itemName }, #{ itemPrice }, #{itemStock} ) ") 
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	public void insertItem(ItemDto item);

	@Select("select itemNo, itemName, viewCount, itemDate, deleted " +
			"from Item " +
			"order by itemNo desc")
	public List<ItemDto> getList();
	
	@Update("update Item "                                     
			+ "set viewCount = viewCount + 1 "
			+ "where ItemNo = #{ itemNo }")
	void itemViewCount(int itemNo);

	
	@Select("select itemNo, itemName, (select cateName from itemCate where cateCode = Item.cateCode) cateName, "
			+ " cateCode, itemPrice, itemStock, itemDetail, itemDate , deleted " +
			"from Item " +
			"where itemNo = #{ itemNo } and deleted = false")
	ItemDto read(@Param("itemNo") int itemNo);
	
	@Insert("insert into Zzim (memberId, itemNo) "
			+ "values (#{memberId}, #{itemNo}) ")
	@Options(useGeneratedKeys = true, keyProperty = "zzimNo")
    void insertZzim(ZzimDto zzim);
		
	
	@Update("update Item "
			+ "set itemName = #{ itemName } or itemDetail = #{ itemDetail } or itemPrice = #{itemPrice} or itemStock = #{itemStock} "
			+ "where itemNo = #{ itemNo } ")
	void updateItem(ItemDto item);
	
	@Update("update Item "
			+ "set deleted = true "
			+ "where itemNo = #{ itemNo } ")
	void deleteBoard(@Param("itemNo") int itemNo);
	
	
	@Select("select count(*) "
			+ "from Item")
	int selectItemCount();
	
	// 페이징이 적용된 상품 게시판

	@Select("select i.itemNo, i.itemPrice , i.itemName, i.viewCount, i.itemDate, i.deleted, i.itemStock, (select Max(iA.savedFileName) from itemAttach iA where iA.itemNo = i.itemNo) thumbnail "
			+ "from Item i "
			+ "order by i.itemNo desc " + 
			"limit #{from}, #{count}")
	List<ItemDto> selectItemByPage(@Param("from") int from, @Param("count") int count);
	
	// 상품 첨부파일(특정 상품 이미지에 있는) 다운로드
	@Select(  "select attachNo, itemNo, userFileName, savedFileName "
			+ "from itemAttach "
			+ "where itemNo = #{ itemNo }")
	List<ItemAttachDto> selectItemAttachByItemNo(@Param("itemNo") int itemNo);
	
	// 검색
	@Select("select itemNo, itemName, viewCount, itemDate, itemPrice, deleted " +
			"from Item " +
			"where itemName like concat('%', #{keyword}, '%') " +
			"order by itemNo desc " + 
			"limit #{from}, #{count}")
	List<ItemDto> searchItems(@Param("keyword") String keyword, @Param("from") int from, @Param("count") int count);

	// 상품 정보
	@Select("select i.itemNo, i.itemName, i.itemPrice, i.itemStock, i.cateCode, c.cateName "
			+ "from Item i left outer join itemCate c on c.cateCode = i.cateCode "
			+ "where itemNo = #{itemNo} ")
	ItemDto getItemsInfo(int itemNo);
}