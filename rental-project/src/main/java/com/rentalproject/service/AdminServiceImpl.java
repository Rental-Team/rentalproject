package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;
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
	
	//자유게시판
	@Override
	public void writeFreeBoard(FreeBoardDto freeBoard) throws Exception {
		
		adminMapper.writeFreeBoard(freeBoard);  // 자유게시판에 게시글 저장
		
		// 자유게시판에 첨부파일 저장
		for(FreeBoardAttachDto freeBoardAttach : freeBoard.getFreeBoardAttachList()) {
			freeBoardAttach.setFreeBoardNo(freeBoard.getFreeBoardNo());
			adminMapper.insertFreeBoardAttach(freeBoardAttach); // 자동 증가 번호 생성 
		}
	}
	
	@Override
	public List<FreeBoardDto> listFreeBoard() {
		
		List<FreeBoardDto> freeBoardList = adminMapper.selectAllFreeBoard();
		
		return freeBoardList;  // 자유게시판 전체 목록 조회 
	}
	
	@Override // 페이징
	public List<FreeBoardDto> listFreeBoardByPage(int from, int count) {
		List<FreeBoardDto> freeBoardList = adminMapper.selectFreeBoardByPage(from, count);
		return freeBoardList;
	}
	

	@Override  //페이징 - 총 게시물 개수를 db에서 가지고 오기 
	public int getFreeBoardCount() {
		int count = adminMapper.selectFreeBoardCount();
		return count;
	}
	
	@Override
	public int getSearchFreeBoardCount(String keyword) {
		int searchCountAll = adminMapper.selectFreeBoardSearchCount(keyword);
		return searchCountAll;
	} 
	
	@Override
	public int getSearchByTitleCount(String keyword) {
		int searchTitleCount = adminMapper.selectFreeBoardTitleSearchCount(keyword);
		return searchTitleCount; 
	}  

	@Override
	public int getSearchByContentCount(String keyword) {
		int searchContentCount = adminMapper.selectFreeBoardContentSearchCount(keyword);
		return searchContentCount;
	}

	@Override
	public int getSearchByMemberIdCount(String keyword) {
		int searchMemberCount = adminMapper.selectFreeBoardMemberSearchCount(keyword);
		return searchMemberCount;
	}   
	
	@Override
	public FreeBoardDto findFreeBoardByFreeBoardNo(int freeBoardNo) {
		
		FreeBoardDto freeBoard = adminMapper.selectFreeBoardByFreeBoardNo(freeBoardNo); // 자유게시판 글 중 하나 클릭할때 그 게시글 조회
		
		if(freeBoard != null) {
			
			List<FreeBoardAttachDto> freeBoardAttachList = adminMapper.selectFreeBoardAttachByFreeBoardNo(freeBoardNo);
			freeBoard.setFreeBoardAttachList(freeBoardAttachList); // 첨부파일 조회
		
			List<FreeBoardReviewDto> freeBoardReviewList = adminMapper.selectFreeBoardReviewByFreeBaordNo(freeBoardNo);  
			freeBoard.setFreeBoardReviewList(freeBoardReviewList); // 자유게시판 상세보기 하단 댓글 조회
		} 
		return freeBoard;  
	}
	
	@Override
	public FreeBoardAttachDto selectFreeBoardAttachByAttachNo(int attachNo) {
		FreeBoardAttachDto freeBoardAttach = adminMapper.selectFreeBoardAttachByAttachNo(attachNo); // 첨부번호로 첨부파일 찾기 
		return freeBoardAttach;
	}
	
	@Override   // 자유게시판 게시글 수정 내용 가지고 오기
	public void editFreeBoard(FreeBoardDto freeBoard) {
		adminMapper.updateFreeBoard(freeBoard);
		
		// 첨부파일 저장
		for(FreeBoardAttachDto freeBoardAttach : freeBoard.getFreeBoardAttachList()) {
			freeBoardAttach.setFreeBoardNo(freeBoard.getFreeBoardNo());
			adminMapper.insertFreeBoardAttach(freeBoardAttach); // 자동 증가 번호 생성 
		}  
	}
	
	@Override // 자유게시판 게시글 삭제 
	public void deleteFreeBoard(int freeBoardNo) {
		adminMapper.deleteFreeBoard(freeBoardNo);
	}
	
	@Override   // 자유게시판 조회수 증가 
	public void updateFreeBoardviewCount(int freeBoardNo) {
		adminMapper.updateFreeBoardviewCount(freeBoardNo);
	}

	@Override  // 멤버번호로 멤버아이디 받아오기 
	public String getMemberId(int freeBoardNo) {
		String memberId = adminMapper.getMemberId(freeBoardNo);
		return memberId;
	}
	
	@Override  // 멤버번호로 멤버프사 받아오기 
	public String getMemberImage(int freeBoardNo) {
		String memberImage = adminMapper.getMemberImage(freeBoardNo);
		return memberImage;
	}

	@Override // 자유게시판 게시글 검색 
	public List<FreeBoardDto> selectSearchFreeBoard(String keyword, int from, int count){ 
		List<FreeBoardDto> freeBoardSearch = adminMapper.selectSearchFreeBoard(keyword, from, count);
		return freeBoardSearch;
	} 

	@Override
	public List<FreeBoardDto> selectSearchByTitle(String keyword, int from, int count) {
		List<FreeBoardDto> freeBoardSearch = adminMapper.selectSearchByTitle(keyword, from, count);
		return freeBoardSearch;
	}

	@Override
	public List<FreeBoardDto> selectSearchByContent(String keyword, int from, int count) {
		List<FreeBoardDto> freeBoardSearch = adminMapper.selectSearchByContent(keyword, from, count);
		return freeBoardSearch;
	}

	@Override
	public List<FreeBoardDto> selectSearchByMemeberId(String keyword, int from, int count) {
		List<FreeBoardDto> freeBoardSearch = adminMapper.selectSearchByMemeberId(keyword, from, count);
		return freeBoardSearch;
	}
	
	@Override
	public List<FreeBoardDto> selectReportedFreeBoard() { 
		List<FreeBoardDto> reportList = adminMapper.selectReportedFreeBoard();  
		return reportList;  // 신고된 게시글 조회 
	}
	
	@Override   // 자유게시판 댓글 작성 
	public void WriteFreeBoardReview(FreeBoardReviewDto freeBoardReview) {
		
		adminMapper.insertFreeBoardReview(freeBoardReview);
		adminMapper.updateReplyParents(freeBoardReview.getFreeBoardReplyNo(), freeBoardReview.getFreeBoardReplyNo());
		
	}
	
	@Override   // 자유게시판 댓글 삭제 
	public void deleteFreeBoardReview(int freeBoardReplyNo) {
		adminMapper.deleteFreeBoardReview(freeBoardReplyNo);
	}
	
	@Override // 자유게시판 댓글 수정 
	public void editFreeBoardReview(FreeBoardReviewDto freeBoardReview) {
		adminMapper.editFreeBoardReview(freeBoardReview);
	}
	

	
	@Override
	public List<FreeBoardReviewDto> getReviewListByFreeBoardNo(int freeBoardNo) {
		List<FreeBoardReviewDto> reviews = adminMapper.selectFreeBoardReviewByFreeBaordNo(freeBoardNo);
		return reviews;
	}

	@Override
	public FreeBoardReviewDto findFreeBoardReviewByFreeBoardReplyNo(int freeBoardReplyNo) {
		FreeBoardReviewDto freeBoardReview = adminMapper.selectFreeBoardReviewByFreeBoardNo(freeBoardReplyNo);
		return freeBoardReview;
	}

	@Override
	public void updateReplySequence(FreeBoardReviewDto freeBoardReview) {
		adminMapper.updateReplySequence(freeBoardReview);
		
	}

	@Override
	public void writeRereply(FreeBoardReviewDto freeBoardReview) {
		adminMapper.insertRereply(freeBoardReview);
		
	}

	

}