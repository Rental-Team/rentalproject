package com.rentalproject.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.OrderItemDto;
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.dto.ZzimDto;
import com.rentalproject.mapper.AccountMapper;
import com.rentalproject.mapper.ItemMapper;
import com.rentalproject.mapper.OrderMapper;
import com.rentalproject.mapper.ZzimMapper;

public class OrderServiceImpl implements OrderServcie {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ZzimMapper zzimMapper;
	
//	@Autowired
//	private AdminMapper adminMapper;

	// 상품 정보 받기
	@Override
	public OrderDetailDto rentalItemInfo(int itemNo) {
			
		return orderMapper.rentalItemInfo(itemNo);
					
	} 
		
	@Override
	public void insertOrderDetail(OrderDetailDto orderDetail) {
		orderMapper.orderDetail(orderDetail);
	}
		
		
	// 주문 리스트 띄우기(관리자에서 사용)
	@Override
	public List<RentalOrderPageDto> orderList(){
		
		return orderMapper.orderListInfo();
			
	}

	@Override
	@Transactional // acid
	public void order(RentalOrderPageDto od) {
		
		orderMapper.registerOrder(od);                                    // 주문 정보 DB에 저장
		for(OrderDetailDto orid : od.getOrderDetailList()) {
			orid.setOrderId(od.getOrderId());
			orderMapper.registerOrderItem(orid);
				
			ItemDto item = itemMapper.getItemsInfo(orid.getItemNo());       // 재고 감소 시키고 
			item.setItemStock(item.getItemStock() - orid.getItemCount());      
			 
			orderMapper.minusStock(item);
				
			ZzimDto zzim = new ZzimDto();
			zzim.setMemberNo(od.getMemberNo());
			zzim.setItemNo(orid.getItemNo());
				
			zzimMapper.deleteOrderZzim(zzim);
		}
			

		} 
		  
		
	@Override
	public List<RentalOrderPageDto> orderDetail(int orderId) {
		
		List<RentalOrderPageDto> orderList = orderMapper.getOrderDetail(orderId);			
			
		return orderList;
	}

	@Override
	public RentalOrderPageDto getAddress(int orderId) {
	
		RentalOrderPageDto address = orderMapper.getAddress(orderId);
		
		return address;
	}  
	
//	@Override
//	public RentalOrderPageDto getAttach(int orderId) {
//		RentalOrderPageDto attach = orderMapper.getAttach(orderId);
//		return attach;
//	}

		
	}