package com.rentalproject.controller;



import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.rentalproject.dto.ItemDto;
import com.rentalproject.service.ItemService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@GetMapping("/list")
	public void list(Model model) {

		log.info("list");

		model.addAttribute("list", itemService.getList());
	}
	
	@GetMapping("/write")
	public String itemWriteForm() {
		
		
		
		return "item/write";
	}
	
	@PostMapping("/write")
	public String write(ItemDto item, RedirectAttributes rttr) {

		log.info("register: " + item);

		itemService.writeItem(item);
		rttr.addAttribute("result", item.getItemNo());

		return "redirect:/item/list";
	}

	@GetMapping("/get")
	public void get(@RequestParam("itemNo") int itemNo, Model model) {

		log.info("/get");
		
		model.addAttribute("item", itemService.get(itemNo));

	}

}
