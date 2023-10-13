package com.rentalproject.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;

@Mapper
public interface OrderMapper {
	
	@Insert("insert into rentalOrder (orderId, addressUser, memberNo, address, addressDetail ) "
			+ "values ( #{ orderId }, #{ addressUser}, #{ memberNo }, #{ address }, #{ addressDetail }  ) ")
	void rentalOrder(OrderDto order);
	
	@Select("select itemPrice "
			+ "from OrderDetail "
			+ "where itemNo = #{itemNo}")
	OrderDetailDto rentalItemInfo(int itemNo);
	
}
