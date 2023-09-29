package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.ItemAttachDto;

@Mapper
public interface ItemAttachMapper {

	@Insert("insert into itemAttach ( attachNo, itemNo, userFileName, savedFileName ) "
			+ "into #{attachNo}, #{itemNo}, #{userFileName}, #{savedFileName} ")
	public void insert(ItemAttachDto itemAttach);
	
	@Delete("delete from itemAttach where attachNo = #{attachNo} ")
	public void delete(String attachNo);
	
	@Select("select * from itemAttach "
			+ "where itemNo = #{itemNo}")
	public List<ItemAttachDto> findByItemNo(int itemNo);
	
}
