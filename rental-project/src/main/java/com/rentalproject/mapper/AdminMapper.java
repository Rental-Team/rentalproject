package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.dto.ZzimDto;

@Mapper
public interface AdminMapper {

	
	// 상품 등록을 위한 메서드
	@Insert( "insert into Item ( itemDetail , itemName, itemPrice, cateCode, itemStock ) "
			+ "values ( #{ itemDetail }, #{ itemName }, #{ itemPrice }, #{ cateCode }, #{itemStock}) ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	void insertItem(ItemDto item);

	// 상품 이미지 저장을 위한 메서드
	@Insert( "insert into itemAttach (attachNo, itemNo, userFileName, savedFileName) "
			+ "values (#{attachNo}, #{itemNo} ,#{userFileName}, #{savedFileName}) ") 
	void insertItemAttach(ItemAttachDto attach);
	
	// 멤버 리스트
	@Select("select memberId, userName, phoneNo, regDate "
			+ "from Member ")
	List<MemberDto> allMemberList();
	
	// 카테고리 리스트
	@Select("select * "
			+ "from itemCate "
			+ "order by cateCode ")
	public List<CategoryDto> cateList();
	
	// 상품 게시판 리스트
	@Select("select i.itemNo, i.itemName, i.viewCount, i.itemDate, i.deleted, i.itemStock, (select iA.savedFileName from itemAttach iA where iA.itemNo = i.itemNo) thumbnail "
			+ "from Item i "
			+ "order by i.itemNo desc")
	List<ItemDto> allItemList();
	
	// 상품 페이징 정보
	@Select("select i.itemNo, i.itemName, i.viewCount, i.itemDate, i.deleted, i.itemStock, (select iA.savedFileName from itemAttach iA where iA.itemNo = i.itemNo) thumbnail "
			+ "from Item i "
			+ "order by i.itemNo desc " + 
			"limit #{from}, #{count}")
	List<ItemDto> selectItemByPage(@Param("from") int from, @Param("count") int count);
	
	
	// 상품 갯수
	@Select("select count(*) "
			+ "from Item")
	int selectItemCount();
	
//	select itemNo, itemName, (select cateName from itemCate where cateCode = itemCate.cateCode) cateName, 
//	cateCode, itemPrice, itemStock, itemDetail
//	from Item 
//	where itemNo = #{itemNo}

	
	// 상세 보기
	@Select("select itemNo, itemName, (select cateName from itemCate where cateCode = Item.cateCode) cateName, "
			+ " cateCode, itemPrice, itemStock, itemDetail, itemDate , deleted " +
			"from Item " +
			"where itemNo = #{ itemNo } and deleted = false")
	ItemDto read(@Param("itemNo") int itemNo);
	
	// 상품 수정
	@Update("update Item "
			+ "set itemName = #{ itemName } , itemDetail = #{ itemDetail }, itemPrice = #{ itemPrice }, itemStock = #{ itemStock } "
			+ "where itemNo = #{ itemNo } ")
	void updateItem(ItemDto item);
	
	// 상품 삭제
	@Delete("delete from Item "
			+ "where itemNo = #{itemNo} ")
	void deleteBoard(@Param("itemNo") int itemNo);
	
	
	// 상품 첨부파일(특정 상품 이미지에 있는) 다운로드
	@Select(  "select attachNo, itemNo, userFileName, savedFileName "
			+ "from itemAttach "
			+ "where itemNo = #{ itemNo }")
	List<ItemAttachDto> selectItemAttachByItemNo(@Param("itemNo") int itemNo);
	
	// 상품 이미지 정보를 조회
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