package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.ItemQnaDto;
import com.rentalproject.dto.PrivateQnaDto;

@Mapper
public interface ItemQnaMapper {

	
	
	
	@Insert("INSERT INTO ItemQnA (itemNo, itemQnaWriter, itemqnaContent, itemqnaDate, parents, sequence, depth ) " +
	        "VALUES (#{itemNo}, #{itemQnaWriter}, #{itemqnaContent}, NOW(), 0, 1, 0)")
	@Options(useGeneratedKeys = true, keyProperty = "itemqnaNo", keyColumn = "itemqnaNo")
	void insertItemQna(ItemQnaDto itemQna);
	
	@Update("update ItemQnA "
			+ "set parents = #{parents} "
			+ "where itemqnaNo = #{itemqnaNo} ")
		void updateParents(@Param("parents")int parents, @Param("itemqnaNo") int itemqnaNo);
	
	
	
	
	
		/*
		 * @Select("SELECT * " + "FROM ItemQnA " + "where itemNo = #{itemNo} " +
		 * "ORDER BY itemqnaNo DESC") List<ItemQnaDto> selectItemQnaByItemNo(int
		 * itemNo);
		 */
	
	
	
	@Select("SELECT * "
	        + "FROM ItemQnA "
			+ "where itemNo = #{itemNo} "
	        + "ORDER BY parents DESC, sequence asc")
	List<ItemQnaDto> selectItemQnaByItemNo(int itemNo);
	
	
	/*
	 * @Select("SELECT * " + "FROM ItemQnA " + "where itemNo = #{itemqnaNo} " +
	 * "ORDER BY itemqnaNo DESC") ItemQnaDto selectItemQnaByItemqnaNo(int
	 * itemqnaNo);
	 */
	
	
	
	@Update("update ItemQnA "
			+ "set itemqnaDelete = true "
			+ "where itemqnaNo = #{itemqnaNo}")
	  void deleteItemQna(@Param("itemqnaNo") int itemqnaNo);
	
	
	@Update("update ItemQnA "
	        + "set itemqnaContent = #{itemqnaContent} "
	        + "where itemqnaNo = #{itemqnaNo}")
	void editItemQna(ItemQnaDto itemQna);


	@Select("select itemqnaNo, itemNo, itemQnaWriter, itemqnaContent, itemqnaDate, itemqnaDelete, Parents, Sequence, depth "
	        + "from ItemQnA "
	        + "where itemqnaNo = #{itemqnaNo} and itemqnaDelete = false")
	ItemQnaDto selectItemQnaByItemQnaNo(int itemqnaNo);

	
	@Update("update ItemQnA "
	        + "set sequence = sequence + 1 "
	        + "where parents = #{parents} and sequence >= #{parents} ")
	void updateSequence(ItemQnaDto itemQna);
	

	@Insert("INSERT INTO ItemQnA (itemNo, itemQnaWriter, itemqnaContent, parents, sequence, depth) "
			+"VALUES (#{itemNo}, #{itemQnaWriter}, #{itemqnaContent}, #{parents}, #{sequence}, #{depth})")

	void insertReplyQna(ItemQnaDto itemQna);














	
	
	
}
