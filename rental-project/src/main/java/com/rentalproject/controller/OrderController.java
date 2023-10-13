package com.rentalproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.RentalOrderDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.OrderServcie;


@Controller
public class OrderController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrderServcie orderServcie;

	@GetMapping("/rental")
	public String rentalForm(@RequestParam("memberNo") int memberNo , int[] itemNos, int[] itemCounts, Model model){
		
//		System.out.println("memberNo : " + itemNos[0]);
//		System.out.println("rentals : " + itemCounts[0]);
				
		RentalOrderDto ro = orderServcie.rentalMemberInfo(memberNo);
				
		if (itemNos != null && itemCounts != null) {
			
			List<OrderDetailDto> orders = new ArrayList<>();
			
	        for (int i = 0; i < itemNos.length; i++) {
	            
	            //model.addAttribute("rentalItem", orderServcie.getRentalItemInfo(RO.getOrderDetailList()));
	            OrderDetailDto od = orderServcie.rentalItemInfo(itemNos[i]);
	            od.setItemCount(itemCounts[i]);
	            orders.add(od);
	            
	        }
	        
	        model.addAttribute("orders", orders);
            
            System.out.println(orders);
	    }
		
		//model.addAttribute("memberInfo", accountService.getMemberInfo(memberNo));
		
		model.addAttribute("ro", ro);
		
		return "rental/rentalRegister";
	}
	
	
	@PostMapping("/rental")
	public String rental(OrderDto order) {
		
		orderServcie.insertOrder(order);
		
		return "redirect:/zzim";
	}
	
}
