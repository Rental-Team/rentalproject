package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;

@Mapper
public interface AdminMapper {

	
	public void InsertItem(ItemDto item);
	
	@Select("select memberId, userName, phoneNo, regDate "
			+ "from Member ")
	public List<MemberDto> allMemberList();
	
	
	
	
}
