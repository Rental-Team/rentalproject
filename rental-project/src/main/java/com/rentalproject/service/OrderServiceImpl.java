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
		public void order(RentalOrderPageDto od) {
			MemberDto member = accountMapper.getMemberInfo(od.getMemberNo());
			List<OrderDetailDto> ords = new ArrayList<>();
			for(OrderDetailDto orid : od.getOrderDetailList()) {
				OrderDetailDto orderItem = orderMapper.getOrderInfo(orid.getItemNo());           // 여러개의 주문 항목 저장하고 DB에서 주문 항목 정보 불러오기
				
				// 수량
				orderItem.setItemCount(orid.getItemCount());
				
				ords.add(orderItem);                                 // 주문 항목을 리스트에 담고
			}
			od.setOrderDetailList(ords);
			
//			// orderId 만들기
//			Date date = new Date();
//			SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
//			String orderId = member.getMemberNo() + format.format(date);
//			od.setOrderDetailList(orderId);
//			
			
			orderMapper.registerOrder(od);                                    // 주문 정보 DB에 저장
			for(OrderDetailDto orid : od.getOrderDetailList()) {
				orderMapper.registerOrderItem(orid);
			}
			
			for(OrderDetailDto orid : od.getOrderDetailList()) {                          // 주문 항목에 대한 상품정보 가져와서
				ItemDto item = itemMapper.getItemsInfo(orid.getItemNo());       // 재고 감소 시키고 
				item.setItemStock(item.getItemStock() - orid.getItemCount());      
				 
				orderMapper.minusStock(item);                                       // 감소한 재고 만큼을 DB에 업데이트 
			}
			
			// 찜 제거
			for(OrderDetailDto orid : od.getOrderDetailList()) {
				ZzimDto zzim = new ZzimDto();
				zzim.setMemberId(od.getMemberId());
				zzim.setItemNo(orid.getItemNo());
				
				zzimMapper.deleteOrderZzim(zzim);
			}
		} 

//		@Override
//		public RentalOrderDto rentalMemberInfo(int memberNo) {
//			
//			return orderMapper.rentalMemberInfo(memberNo);
//		}
		
	}