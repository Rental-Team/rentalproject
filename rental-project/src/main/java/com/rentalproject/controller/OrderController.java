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
	public String rentalForm(int[] itemNos, int[] itemCounts, Model model){
		
//		System.out.println("memberNo : " + itemNos[0]);
//		System.out.println("rentals : " + itemCounts[0]);
				
		
				
		if (itemNos != null && itemCounts != null) {
			
			List<OrderDetailDto> orders = new ArrayList<>();
			double totalOrderPrice = 0;   // 총 대여금액
			
	        for (int i = 0; i < itemNos.length; i++) {
	            

	            //model.addAttribute("rentalItem", orderServcie.getRentalItemInfo(RO.getOrderDetailList()));
	            OrderDetailDto od = orderServcie.rentalItemInfo(itemNos[i]);
	            od.setItemCount(itemCounts[i]);
	            orders.add(od);
	            
	            totalOrderPrice += od.getItemPrice() * itemCounts[i];
	            
	        }
	        
	        model.addAttribute("orders", orders);
	        model.addAttribute("totalOrderPrice", totalOrderPrice); 
            
            System.out.println(orders);
	    }
		
		//model.addAttribute("memberInfo", accountService.getMemberInfo(memberNo));
		
		
		
		return "rental/rentalRegister";
	}
	
	
	@PostMapping("/rental")
	public String rental(RentalOrderDto order) {
		
		
		
		return "redirect:/zzim";
	}
}
