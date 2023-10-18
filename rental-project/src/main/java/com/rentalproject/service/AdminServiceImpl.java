package com.rentalproject.service;

import java.util.List;

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
	
	// 회원 리스트 한번에 조회
	@Override
	public List<MemberDto> MemberList() {
		
		return adminMapper.allMemberList();
	}
	
	@Override // 부분 목록 조회
	public List<MemberDto> listMemberByPage(int from, int count) { // 페이지 데이터를 읽는 작업
		List<MemberDto> boardList = adminMapper.selectMemberByPage(from, count); // mapper 불러온것
		return boardList;
	}
	
	@Override // 페이저
	public int getMemberCount() {
		int count = adminMapper.selectMemberCount();
		return count;
	}
	
	// 회원 상세 조회
	@Override
	public MemberDto selectMemberDetail(int memberNo) {
		MemberDto selectMemberDetail = adminMapper.selectMemberDetail(memberNo);
		return selectMemberDetail;
	}
	
	// 카테고리 리스트
	@Override
	public List<CategoryDto> cateList() {
		
		return adminMapper.cateList();
	};
	
	// 상품 리스트 출력
	@Override
	public List<ItemDto> ItemList() {

		return adminMapper.allItemList();
	}
	
	// 상품 등록
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
	
	// 상품 상세
	@Override
	public ItemDto itemDetail(int itemNo) {
		
		ItemDto item = adminMapper.read(itemNo);
		
		if(item!=null) {
			List<ItemAttachDto> itemList = adminMapper.selectItemAttachByItemNo(itemNo);
			item.setItemAttachList(itemList);
		}
		
		return item;
	}
	
	
	// 상품 갯수
	@Override
	public int getItemCount() {
		
		int count = adminMapper.selectItemCount();
		
		return count;
	}
	
	// 상품 리스트 (페이징 기능)
	@Override
	public List<ItemDto> listItemByPage(int from, int count) {
		
		List<ItemDto> itemList = adminMapper.selectItemByPage(from, count);
		
		return itemList;
		
	}


	// 첨부 파일 정보 조회(첨부파일 번호로)
	@Override
	public ItemAttachDto findItemAttachByAttachNo(int attachNo) {
		ItemAttachDto attach = adminMapper.selectItemAttachByAttachNo(attachNo);
		return attach;
	}

	// 상품 수정
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
	
	// 상품 삭제
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