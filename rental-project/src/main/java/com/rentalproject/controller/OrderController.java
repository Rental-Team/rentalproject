package com.rentalproject.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto; 
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.OrderServcie;


@Controller
public class OrderController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrderServcie orderServcie; 
	
	

	@GetMapping("/rental")
	public String rentalForm(int[] itemNos, int[] itemCounts, Model model, HttpSession session){
		
//		System.out.println("memberNo : " + itemNos[0]);
//		System.out.println("rentals : " + itemCounts[0]);
				
	
				
		if (itemNos != null && itemCounts != null) {
			
			List<OrderDetailDto> orderDetails = new ArrayList<>();
			double totalOrderPrice = 0;   // 총 대여금액
			
	        for (int i = 0; i < itemNos.length; i++) {
	            
	            //model.addAttribute("rentalItem", orderServcie.getRentalItemInfo(RO.getOrderDetailList()));
	            OrderDetailDto od = orderServcie.rentalItemInfo(itemNos[i]);
	            od.setItemCount(itemCounts[i]);
	            orderDetails.add(od);
	            
	            totalOrderPrice += od.getItemPrice() * itemCounts[i];
	            
	        }
	        
	        //model.addAttribute("membeInfo", accountService.getMemberInfo(memberId));
	        model.addAttribute("orderDetails", orderDetails);
	        model.addAttribute("totalOrderPrice", totalOrderPrice); 
	        // model.addAttribute("orderList", orderServcie);
	        //MemberDto loginMember = (MemberDto)session.getAttribute("loginuser");
	        
	    } 
		return "rental/rentalRegister";
	}
	
	
	@PostMapping("/rental")
	public String rental(RentalOrderPageDto order) {          // 주문 정보 저장 
		
		System.out.println(order);
		
		orderServcie.order(order);
		//
		// orderServcie.order(ord);
		// orderServcie.insertRentalOrder(order);
		// orderServcie.insertOrderDetail(orderDetail);
		
		// 주문 후 삭제
		// orderServcie.deleteZzimAfterOrder(0);
		
		//System.out.println(ord);
		return "redirect:/home";
	}
	

	@GetMapping("/rental/rentalok")
    public String showRentalOkPage() {
		 return "rental/rentalok"; 
    }
	
	@GetMapping("/rental-project/home")
    public String ReturnHome() {
		 return "rental/home"; 
    }
	
	@GetMapping("/rental/rentalDetail")
    public String OrderDetail() {
		 return "rental/rentalDetail"; 
    }
	
}