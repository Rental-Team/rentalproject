package com.rentalproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.OrderDetailDto;
import com.rentalproject.dto.OrderDto;
import com.rentalproject.dto.OrderForm;
import com.rentalproject.dto.RentalOrderPageDto;
import com.rentalproject.service.AccountService;
import com.rentalproject.service.OrderServcie;


@Controller
public class OrderController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrderServcie orderServcie; 
	
//	@GetMapping("/directRental")
//	public String directRentalForm(@ModelAttribute("RentalDto") RentalOrderPageDto rentalOrder, HttpSession session) {
//		
//		List<RentalOrderPageDto> directOrder = new ArrayList<>();
//		directOrder.add(rentalOrder);
//		MemberDto memberInfo = (MemberDto)session.getAttribute("memberInfo");
//		session.setAttribute("directOrder", directOrder);
//		session.setAttribute("orderer", memberInfo);
//		System.out.println(memberInfo);
//		
//		return "/rental/directRental";
//	}
	

	@GetMapping("/rental")
	public String rentalForm(int[] itemNos, int[] itemCounts, Model model, HttpSession session){
		
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
	        
	        //MemberDto loginMember = (MemberDto)session.getAttribute("loginuser");
	        
            
            System.out.println(orders);
	    }
		
		//model.addAttribute("memberInfo", accountService.getMemberInfo(memberNo)); 
		
		return "rental/rentalRegister";
	}
	
	
	@PostMapping("/rental")
	public String rental(RentalOrderPageDto orderDetailList, OrderDetailDto orderDetail) {
		
		
		
		orderServcie.insertRentalOrder(orderDetailList);
		orderServcie.insertOrderDetail(orderDetail); 
		 
		
		orderServcie.deleteZzimAfterOrder(0);
		
		return "redirect:/home";
	}
	
	//@GetMapping("/rental/rentalok")
    //public String showRentalOkPage() {
	//	 return "rental/rentalok"; 
   // }
	
	@GetMapping("/rental-project/home")
    public String ReturnHome() {
		 return "rental/home"; 
    }
	
	@GetMapping("/rental/rentalDetail")
    public String OrderDetail() {
		 return "rental/rentalDetail"; 
    }
	
	@GetMapping("/rental/rentalok")
	public String submitOrderForm(Model model) {
	    OrderForm orderForm = new OrderForm();
	    model.addAttribute("orderForm", orderForm);
	    return "/rental/rentalDetail";
	}

	@PostMapping("/rental/submitOrder")
	
	public String submitOrder(@ModelAttribute("orderForm") OrderForm orderForm) {
	    // OrderForm에서 RentalOrderPageDto로 변환
	    RentalOrderPageDto rentalOrderPageDto = orderForm.toRentalOrderPageDto();

	    // 데이터베이스에 주문 정보 저장 (기본 정보)
	    orderServcie.insertRentalOrder(rentalOrderPageDto);

	    // 주문 상세 정보를 orderDetailList에 추가
	    List<OrderDetailDto> orderDetailList = new ArrayList<>(); // 이 리스트는 어디에서 생성되는지에 따라 수정이 필요할 수 있음
	    for (OrderDetailDto orderDetail : rentalOrderPageDto.getOrderDetailList()) {
	        orderDetailList.add(orderDetail);
	    } 
	    
	    orderServcie.saveOrderDetails(orderDetailList);
	    
	    return "redirect:/rental/rentalok"; // 주문 확인 페이지로 이동
	} 
	}
	
	

