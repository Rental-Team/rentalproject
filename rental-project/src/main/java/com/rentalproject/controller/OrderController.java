package com.rentalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rentalproject.dto.OrderPageDto;

@Controller
public class OrderController {

	@GetMapping("/order/{memberNo}")
	public void orderPage(@PathVariable("memberNo") int memberNo, OrderPageDto order, Model model) {
		
		
	}
}
