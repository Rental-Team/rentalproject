package com.rentalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.ItemQnaDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.ZzimDto;
import com.rentalproject.service.ItemQnaService;
import com.rentalproject.service.ItemService;
import com.rentalproject.service.ItemServiceImpl;
import com.rentalproject.ui.ThePager;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.ItemQnaDto;
import com.rentalproject.dto.ItemReviewDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.ZzimDto;
import com.rentalproject.service.ItemQnaService;
import com.rentalproject.service.ItemReviewService;
import com.rentalproject.service.ItemService;
import com.rentalproject.ui.ThePager;

@Controller
//@Log4j
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemReviewService itemReviewService;

	@Autowired
	private ItemQnaService itemQnaService;

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
		
		int pageSize = 5;
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
	
	// 검색
	@ResponseBody
    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam("keyword") String keyword, @RequestParam("from") int from, @RequestParam("count") int count) {
        List<ItemDto> searchResult = itemService.searchItems(keyword, from, count);
        return searchResult;
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

	/*
	 * @GetMapping("/detail") public String detail(@RequestParam(defaultValue =
	 * "-1") int itemNo,
	 * 
	 * @RequestParam(defaultValue="-1") int pageNo, Model model) {
	 * 
	 * if (itemNo == -1 || pageNo == -1) { // 글 번호가 요청에 포함되지 않은 경우 return
	 * "redirect:/admin/item/list"; }
	 * 
	 * ItemDto item = itemService.detail(itemNo);
	 * 
	 * if (item == null) { // 조회된 상품이 없는 경우 return "redirect:/item/list"; }
	 * 
	 * model.addAttribute("item", item); model.addAttribute("pageNo", pageNo);
	 * 
	 * return "item/detail"; }
	 */
	
	
	@GetMapping("/detail")
	public String detail(@RequestParam(defaultValue = "-1") int itemNo, 
			 			@RequestParam(defaultValue="-1") int pageNo, 
			 			Model model) {
		
		if (itemNo == -1 || pageNo == -1) { // 글 번호가 요청에 포함되지 않은 경우
			return "redirect:/admin/item/list";
		}
		
		ItemDto item = itemService.detail(itemNo);
		
		if (item == null) { // 조회된 상품이 없는 경우
			return "redirect:/item/list";
		}
		

		List<ItemReviewDto> itemReviews = itemReviewService.getReviewListByItemNo(itemNo);
		
		List<ItemQnaDto> itemQna = itemQnaService.listItemQna(itemNo);
		
		
		
		model.addAttribute("itemQnas",itemQna);
		model.addAttribute("itemReviews", itemReviews);

		model.addAttribute("item", item);
		model.addAttribute("pageNo", pageNo);
		
		itemService.updateItemViewCount(itemNo);
		
		return "item/detail";
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
	
	
	
	
	@ResponseBody
	@GetMapping("/ajax-zzim")
	public String zzim(ZzimDto zzim, HttpSession session) throws Exception {
		
		int result = 0;
		
		MemberDto member = (MemberDto)session.getAttribute("loginuser");
		
		if (member != null) {
			zzim.setMemberId(member.getMemberId());
			
			
			itemService.zzim(zzim);
			result = 1;
			
		}
		
		return result + "";
		
	}
	
	
	
	

}
