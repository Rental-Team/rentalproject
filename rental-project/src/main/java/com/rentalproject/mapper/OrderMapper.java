package com.rentalproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderDto;

@Mapper
public interface OrderMapper {
	
	// 주문 하기
	@Insert("insert into rentalOrder (orderId, addressUser, memberNo, address, addressDetail ) "
			+ "values ( #{ orderId }, #{ addressUser}, #{ memberNo }, #{ address }, #{ addressDetail }  ) ")
	void rentalOrder(OrderDto order);
	
 
	// 상품 정보 가져오기
	@Select("select itemPrice ,itemNo, itemName " 
			+ "from Item "
			+ "where itemNo = #{itemNo}")
	OrderDetailDto rentalItemInfo(@Param("itemNo") int itemNo);
	
	// 주문 정보 가져오기(리스트) - 관리자에서 가져감.
	@Select("select ro.orderId,(select od.orderItemNo from OrderDetail od where od.orderId = ro.orderId) orderItemNo, "
			+ "ro.orderDate, ro.orderState "
			+ "from rentalOrder ro ")
	List<RentalOrderDto> orderListInfo();
	
	
	// 
	@Select("select memberId, email "
			+ "from Member ")
	RentalOrderDto rentalMemberInfo(@Param("MemberNo") int memberNo);
}
