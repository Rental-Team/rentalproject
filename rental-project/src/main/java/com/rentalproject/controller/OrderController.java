package com.rentalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rentalproject.dto.RentalOrderDto;


@Controller
public class OrderController {

	@GetMapping("/rental/{memberNo}")
	public String orderForm(@PathVariable("memberNo") int memberNo, RentalOrderDto rental, Model model){
		
		System.out.println("memberNo : " + memberNo);
		System.out.println("rentals : " + rental.getOrderDetailList());
		
		return "rental/rentalRegister";
	}
}
