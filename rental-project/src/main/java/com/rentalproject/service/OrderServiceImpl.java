package com.rentalproject.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public void order(OrderDto od) {
		MemberDto member = accountMapper.getMemberInfo(od.getMemberNo());
		List<OrderItemDto> ords = new ArrayList<>();
		for(OrderItemDto orid : od.getOrders()) {
			OrderItemDto orderItem = orderMapper.getOrderInfo(orid.getItemNo());
			
			// 수량
			orderItem.setItemCount(orid.getItemCount());
			
			ords.add(orderItem);
		}
		od.setOrders(ords);
		
		// orderId 만들기
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMemberNo() + format.format(date);
		od.setOrderId(orderId);
		
		
		orderMapper.registerOrder(od); 
		for(OrderItemDto orid : od.getOrders()) {
			orderMapper.registerOrderItem(orid);
		}
		
		for(OrderItemDto orid : od.getOrders()) {
			ItemDto item = itemMapper.getItemsInfo(orid.getItemNo());
			item.setItemStock(item.getItemStock() - orid.getItemCount());
			
			orderMapper.minusStock(item);
		}
		
		// 찜 제거
		for(OrderItemDto orid : od.getOrders()) {
			ZzimDto zzim = new ZzimDto();
			zzim.setMemberId(od.getMemberId());
			zzim.setItemNo(orid.getItemNo());
			
			zzimMapper.deleteOrderZzim(zzim);
		}
	}

//	@Override
//	public RentalOrderDto rentalMemberInfo(int memberNo) {
//		
//		return orderMapper.rentalMemberInfo(memberNo);
//	}
	
}
