package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.ItemAttachDto;

@Mapper
public interface ItemAttachMapper {

	@Insert("insert into itemThumb (uuid, itemNo, fileName, uploadPath "
			+ "values (#{ uuid }, #{ itemNo }, #{ fileName }, #{ uploadPath } )")
	public void insert(ItemAttachDto item);
	
	@Delete("delete from itemThumb "
			+ "where uuid = #{ uuid } ")
	public void delete(String uuid);
	
	@Select("select * "
			+ "from itemAttach "
			+ "where itemNo = #{ itemNo }")
	public List<ItemAttachDto> findByItemNo(int itemNo);

	
}
