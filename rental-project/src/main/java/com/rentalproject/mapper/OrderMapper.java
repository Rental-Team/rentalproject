package com.rentalproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderPageDto;

@Mapper
public interface OrderMapper {
	
	// 주문 정보
	@Insert("insert into rentalOrder (orderId, addressUser, memberNo, address, addressDetail ) "
			+ "values ( #{ orderId }, #{ addressUser}, #{ memberNo }, #{ address }, #{ addressDetail }  ) ")
	@Options(useGeneratedKeys = true, keyProperty = "orderId")
	void rentalOrder(RentalOrderPageDto order);
	
	// 주문 상세 정보
	@Insert("insert into OrderDetail ( orderId, itemNo, itemCount, itemPrice ) "
			+ "values (  #{orderId}, #{itemNo}, #{itemCount}, #{itemPrice} ) ")
	@Options(useGeneratedKeys = true, keyProperty = "orderItemNo")
	void orderDetail(OrderDetailDto orderDetail);
	
	// 주문 후 찜 목록에서 삭제하기
	@Delete("delete from Zzim "
			+ "where zzimNo = #{zzimNo}")
	public int deleteZzim(@Param("zzimNo") int zzimNo);
	

	// 상품 정보 가져오기
	@Select("select itemPrice ,itemNo, itemName " 
			+ "from Item "
			+ "where itemNo = #{itemNo}")
	OrderDetailDto rentalItemInfo(@Param("itemNo") int itemNo);
	
	// 주문 정보 가져오기(리스트) - 관리자에서 가져감.
	@Select("select ro.orderId,(select od.orderItemNo from OrderDetail od where od.orderId = ro.orderId) orderItemNo, "
			+ "ro.orderDate, ro.orderState "
			+ "from rentalOrder ro ")
	List<RentalOrderPageDto> orderListInfo();
	

}
