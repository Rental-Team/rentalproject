package com.rentalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/rental")
public class OrderController {

	@GetMapping("/")
	public String orderForm(){
		
		
		return "rental/rentalRegister";
	}
}
