package com.rentalproject.mapper;


import java.util.List;

import javax.mail.FetchProfile.Item;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.OrderItemDto;
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.dto.ZzimDto;

@Mapper
public interface OrderMapper {
	
	// 주문 정보
	@Insert("insert into rentalOrder (addressUser, memberNo, address, addressDetail ) " 
			+ "values (#{ addressUser }, #{ memberNo }, #{ address }, #{ addressDetail }) ")
	@Options(useGeneratedKeys = true, keyProperty = "orderId")
	void rentalOrder(RentalOrderPageDto order);
	
	// 주문 상세 정보
	@Insert("insert into OrderDetail ( orderId, itemNo, itemCount, itemPrice ) "
			+ "values (  #{ orderId }, #{ itemNo }, #{ itemCount }, #{ itemPrice } ) ")
	@Options(useGeneratedKeys = true, keyProperty = "orderItemNo") 
	void orderDetail(OrderDetailDto orderDetail);  
	
	
	// 상품 정보 가져오기
	@Select("select itemPrice ,itemNo, itemName " 
			+ "from Item "
			+ "where itemNo = #{itemNo}")
	OrderDetailDto rentalItemInfo(@Param("itemNo") int itemNo); 
	
	// 주문 처리
	@Select("select itemNo, itemPrice "
			+ "from Item where itemNo = #{itemNo} ")
	OrderDetailDto getOrderInfo(int itemNo);
	
	// 주문 등록
	@Insert("insert into rentalOrder (addressUser, memberNo, address, addressDetail, orderState ) "
			+ "values ( #{ addressUser}, #{ memberNo }, #{ address }, #{ addressDetail }, '대기중'  ) ")
	@Options(useGeneratedKeys = true, keyProperty = "orderId")
	int registerOrder(RentalOrderPageDto ord);
	
	// 주문 상품 등록
	@Insert("insert into OrderDetail ( orderId, itemNo, itemCount, itemPrice ) "
			+ "values (  #{orderId}, #{itemNo}, #{itemCount}, #{itemPrice} ) ")
	@Options(useGeneratedKeys = true, keyProperty = "orderItemNo")
	int registerOrderItem(OrderDetailDto orid);
	
	// 재고 차감
	@Update("update Item "
			+ "set itemStock = #{itemStock} "
			+ "where ItemNo = #{itemNo} ")
	int minusStock(ItemDto item);
	
	// 주문 후 찜 목록에서 삭제하기
	@Delete("delete from Zzim "
			+ "where memberNo = #{memberNo} and itemNo = #{itemNo} ")
	public int deleteZzim(ZzimDto zzim);
	
	// 주문 정보 가져오기(리스트) - 관리자에서 가져감.
	@Select("select ro.orderId,(select od.orderItemNo from OrderDetail od where od.orderId = ro.orderId) orderItemNo, "
			+ "ro.orderDate, ro.orderState "
			+ "from rentalOrder ro ")
	List<RentalOrderPageDto> orderListInfo();
	
	

}
