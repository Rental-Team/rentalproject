package com.rentalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.service.AdminService;
import com.rentalproject.ui.ThePager;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/home")
	public void adminHome() throws Exception {
		
	}
	
	@GetMapping("/member/list")
	public String getMemberList(Model model) throws Exception {
		
		List<MemberDto> memberList = adminService.MemberList();
		
		model.addAttribute("memberList", memberList);
		
		return "admin/member/list";
	}
	
	@GetMapping("/item/list")
	public String list(@RequestParam(defaultValue = "1")int pageNo, Model model) {
		
		
		//List<ItemDto> list = itemService.getList();
		//log.info(list);
		
		int pageSize = 10;
		int pagerSize = 5;
		String linkUrl = "list";
		int dataCount = adminService.getItemCount();
		
		int from = (pageNo - 1)*pageSize;
		List<ItemDto> itemList = adminService.listItemByPage(from, pageSize);
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl);
		
		//model.addAttribute("list", list);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "admin/item/list";
	}
	
	@GetMapping("/item/write")
	public String itemWriteForm(ItemDto item, Model model) {
		
		
		return "admin/item/write";
	}
	
	@PostMapping("/item/write")
	public String write(ItemDto item) {

		//log.info("register: " + item);

		adminService.writeItem(item);
//		rttr.addAttribute("result", item.getItemNo());

		return "redirect:/admin/item/list";
	}

	@GetMapping("/item/detail")
	public void detail(@RequestParam("itemNo") int itemNo,
			 			Model model) { //@RequestParam(defaultValue = "-1")int pageNo,
		
		ItemDto item = adminService.itemDetail(itemNo);
		
//		if(item == null) {
//			return "redirect:list";
//		}
		
		//log.info("/detail");
		
		model.addAttribute("item", item);
		

	}

	
	@GetMapping("/item/edit")
	public String editForm(@RequestParam("itemNo") int itemNo, Model model) {
		
		if (itemNo == -1) {
			return "redirect:list";
		}
		
		ItemDto item = adminService.itemDetail(itemNo);
		
		//log.info(item);
		
		if (item == null) {
			return "redirect:/admin/item/list";
		}
		
		
		
		model.addAttribute("item", item);
		model.addAttribute("itemNo", itemNo);
		
		
		return "admin/item/edit";
		
	}
	
	
	@PostMapping("/item/edit")
	public String edit(ItemDto item ) {
		
		adminService.editItem(item);
		
		return String.format("redirect:detail?itemNo=%d", item.getItemNo());
	}
	
	
	@GetMapping("/delete/{itemNo}")
	public String delete(@PathVariable("itemNo") int itemNo) {
		
		adminService.deleteItem(itemNo);
				
		return "redirect:/admin/item/list";
	}
	
}
