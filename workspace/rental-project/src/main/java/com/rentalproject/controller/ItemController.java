package com.rentalproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.rentalproject.dto.ItemDto;
import com.rentalproject.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	
	@GetMapping("/write")
	public String itemWriteForm() {
		
		
		
		return "item/write";
	}
	
	@PostMapping("/write")
	public String write(ItemDto item, Model model) {
		
		itemService.writeItem(item);
		
		return "redirect:home";
	}

}
