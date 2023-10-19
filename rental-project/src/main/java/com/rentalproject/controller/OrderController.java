package com.rentalproject.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto; 
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.OrderServcie;
import com.rentalproject.service.ZzimService;


@Controller
public class OrderController {
		
	@Autowired
	private OrderServcie orderServcie; 
	
	@Autowired
	private ZzimService zzimService;
		
	// 주문 페이지 가져오기( 바로 대여 ) 
	@GetMapping("/directRental")
	public String directRentalForm(@RequestParam("itemNo") int itemNo,
									@RequestParam("itemCount") int itemCount,
									Model model) {

		OrderDetailDto od = orderServcie.rentalItemInfo(itemNo);
		od.setItemCount(itemCount);
		
		model.addAttribute("directOrder", od);
		
	    return "rental/directRental";
	}
	
	// 주문 페이지 가져오기 ( 찜하기 에서 대여 )
	@GetMapping("/rental")
	public String rentalForm(int[] itemNos, int[] itemCounts, Model model, HttpSession session){
				
		if (itemNos != null && itemCounts != null) {
			
			List<OrderDetailDto> orderDetails = new ArrayList<>();
			double totalOrderPrice = 0;   // 총 대여금액
			
	        for (int i = 0; i < itemNos.length; i++) {
	            
	            OrderDetailDto od = orderServcie.rentalItemInfo(itemNos[i]);
	            od.setItemCount(itemCounts[i]);
	            orderDetails.add(od);
	            
	            totalOrderPrice += od.getItemPrice() * itemCounts[i];
	            
	        }
	        
	        model.addAttribute("orderDetails", orderDetails);
	        model.addAttribute("totalOrderPrice", totalOrderPrice); 

	        
	    } 
		return "rental/rentalRegister";
	}
	
	// 주문하기
	@PostMapping("/rental")
	public String rental(RentalOrderPageDto order) {          // 주문 정보 저장 
		
		orderServcie.order(order);
		
		return "redirect:rental/rentalok?orderId="+ order.getOrderId();
	}
	

	// 주문 완료 페이지
	@GetMapping("/rental/rentalok")
    public String showRentalOkPage() {
		 return "rental/rentalok"; 
    }
	
	@GetMapping("/rental-project/home")
    public String ReturnHome() {
		 return "rental/home"; 
    }
	
	// 주문 상품 상세 보기
	@GetMapping("/rental/rentalDetail")
    public String OrderDetail(@RequestParam(defaultValue = "-1") int orderId , Model model) {	
		
		List<RentalOrderPageDto> detailList = orderServcie.orderDetail(orderId);
		RentalOrderPageDto address = orderServcie.getAddress(orderId);
		
		model.addAttribute("detailLists", detailList);
		model.addAttribute("address",address);
		
		//model.addAttribute("zzim", zzimService.getZzimList());
		
		return "rental/rentalDetail";
    }
}