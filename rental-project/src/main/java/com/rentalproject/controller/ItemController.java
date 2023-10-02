package com.rentalproject.controller;



import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.rentalproject.dto.ItemDto;
import com.rentalproject.service.ItemService;
import com.rentalproject.service.ItemServiceImpl;
import com.rentalproject.ui.ThePager;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@Log4j
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

//	@GetMapping("/list")
//	public void list(Model model) {
//
//		log.info("list");
//
//		model.addAttribute("list", itemService.getList());
//	}
	
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1")int pageNo, Model model) {
		
	
		//List<ItemDto> list = itemService.getList();
		//log.info(list);
		
		int pageSize = 10;
		int pagerSize = 5;
		String linkUrl = "list";
		int dataCount = itemService.getItemCount();
		
		int from = (pageNo - 1)*pageSize;
		List<ItemDto> itemList = itemService.listItemByPage(from, pageSize);
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl);
		
		//model.addAttribute("list", list);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "item/list";
	
	}
	
	//업로드 이미지 출력
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName) {
		
		
		File file = new File("d:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("/write")
	public String itemWriteForm(ItemDto item, Model model) {
		
		
		return "item/write";
	}
	
	@PostMapping("/write")
	public String write(ItemDto item) {

		//log.info("register: " + item);

		itemService.writeItem(item);
//		rttr.addAttribute("result", item.getItemNo());

		return "redirect:list";
	}

	@GetMapping("/detail")
	public void detail(@RequestParam("itemNo") int itemNo,
			 			Model model) { //@RequestParam(defaultValue = "-1")int pageNo,
		
		ItemDto item = itemService.detail(itemNo);
		
//		if(item == null) {
//			return "redirect:list";
//		}
		
		//log.info("/detail");
		itemService.updateItemViewCount(itemNo);
		
		model.addAttribute("item", item);
		

	}

	
	@GetMapping("/edit")
	public String editForm(@RequestParam("itemNo") int itemNo, Model model) {
		
		if (itemNo == -1) {
			return "redirect:list";
		}
		
		ItemDto item = itemService.detail(itemNo);
		
		//log.info(item);
		
		if (item == null) {
			return "redirect:list";
		}
		
		
		
		model.addAttribute("item", item);
		model.addAttribute("itemNo", itemNo);
		
		
		return "item/edit";
		
	}
	
	
	@PostMapping("/edit")
	public String edit(ItemDto item ) {
		
		itemService.editItem(item);
		
		return String.format("redirect:detail?itemNo=%d", item.getItemNo());
	}
	
	
	@GetMapping("/delete/{itemNo}")
	public String delete(@PathVariable("itemNo") int itemNo) {
		
		itemService.deleteItem(itemNo);
				
		return "redirect:/item/list";
	}
	
	
	
	
	
	
	

}
