package com.rentalproject.service;

import java.util.List;


import javax.mail.FetchProfile.Item;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;
import com.rentalproject.mapper.AdminMapper;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<MemberDto> MemberList() {
		
		
		return adminMapper.allMemberList();
	}
	
	// 카테고리 리스트
	@Override
	public List<CategoryDto> cateList() {
		
		return adminMapper.cateList();
	};
	
	@Override
	public List<ItemDto> ItemList() {
		
		
		return adminMapper.allItemList();
	}
	
	@Override
	public void writeItem(ItemDto item) {
		
		// 상품을 저장해준다. (o)
		adminMapper.insertItem(item);
		
		// 첨부 파일을 저장해준다. (o)
		for (ItemAttachDto attach : item.getItemAttachList()) {
			attach.setItemNo(item.getItemNo());
			adminMapper.insertItemAttach(attach);
		}

		//log.info("write....." + item);
	}
	
	@Override
	public ItemDto itemDetail(int itemNo) {
		
		
		return adminMapper.read(itemNo);
	}
	
	@Override
	public int getItemCount() {
		
		int count = adminMapper.selectItemCount();
		
		return count;
	}
	
	@Override
	public List<ItemDto> listItemByPage(int from, int count) {
		
		List<ItemDto> itemList = adminMapper.selectItemByPage(from, count);
		
		return itemList;
		
	}

	
	@Override
	public ItemDto findItemByItemNo(int itemNo) {
		
		// 상품을 조회한다.
		ItemDto item = adminMapper.read(itemNo);
		
		if (item != null) {
			List<ItemAttachDto> attachList = adminMapper.selectItemAttachByItemNo(itemNo);
			item.setItemAttachList(attachList);
		}
		
		return item;
	}


	@Override
	public ItemAttachDto findItemAttachByAttachNo(int attachNo) {
		ItemAttachDto attach = adminMapper.selectItemAttachByAttachNo(attachNo);
		return attach;
	}


	@Override
	public void editItem(ItemDto item) {
		
		// db 수정
		adminMapper.updateItem(item);
		
		// 이미지 저장
		for (ItemAttachDto attach : item.getItemAttachList()) {
			attach.setItemNo(item.getItemNo());
			adminMapper.insertItemAttach(attach);
		}
	}
	
	
	@Override
	public void deleteItem(int itemNo) {
		
		
		adminMapper.deleteBoard(itemNo);
		
	}
	
	// ================== 공지사항 ====================

	@Override
	public void writeNotice(NoticeDto notice) {
		adminMapper.writenotice(notice);
		
	}

	@Override
	public List<NoticeDto> listNotice() {
		List<NoticeDto> noticeList = adminMapper.selectAllnotice();
		return noticeList;
	}

	@Override
	public NoticeDto findNoticeByNoticeNo(int noticeNo) {
		NoticeDto notice = adminMapper.selectnoticeBynoticeNo(noticeNo);
		return notice;
	}

	@Override
	public void updateviewCount(int noticeNo) {
		adminMapper.updateviewCount(noticeNo);
		
	}

	@Override
	public void editNotice(NoticeDto notice) {
		adminMapper.updatenotice(notice);
		
	}
	

}