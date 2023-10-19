package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;

public interface AdminService {
	
	// 회원 리스트 한번에 조회
	public List<MemberDto> MemberList();
	
	// 회원 리스트 부분 조회
	List<MemberDto> listMemberByPage(int from, int count);
	
	// 회원 페이저
	int getMemberCount();
	
	// 회원 상세
	MemberDto selectMemberDetail(int memberNo);
	
	// 상품 입력
	public void writeItem(ItemDto item);
	
	// 카테고리 리스트
	public List<CategoryDto> cateList();

	// 상품 리스트
	public List<ItemDto> ItemList();
	
	public ItemDto itemDetail(int itemNo);
	
	public void editItem(ItemDto item);
	
	public void deleteItem(int itemNo);
	
	public int getItemCount();
	
	public List<ItemDto> listItemByPage(int from, int count);
		
	public ItemAttachDto findItemAttachByAttachNo(int attachNo);
	
	//public ItemDto findItemByItemNo(int itemNo);
	

	public void writeNotice(NoticeDto notice);

	public List<NoticeDto> listNotice();

	public NoticeDto findNoticeByNoticeNo(int noticeNo);

	public void updateviewCount(int noticeNo);

	public void editNotice(NoticeDto notice);
	
	// 자유게시판
	void writeFreeBoard(FreeBoardDto freeboard) throws Exception;
	
	List<FreeBoardDto> listFreeBoard();   // 자유게시판의 어떤 글이 이동하는지 인터페이스 등록
	
	List<FreeBoardDto> listFreeBoardByPage(int from, int count); // 페이징
	
	int getFreeBoardCount(); //페이징 - 총 게시물 개수를 db에서 가지고 오기 
	
	int getSearchFreeBoardCount(String keyword);  // 검색한 게시물 개수를 db에서 가지고 오기 ???
	int getSearchByTitleCount(String keyword); 
	int getSearchByContentCount(String keyword); 
	int getSearchByMemberIdCount(String keyword);
	
	FreeBoardDto findFreeBoardByFreeBoardNo(int freeBoardNo); // 프리보드 넘버로 보드 찾기

	void editFreeBoard(FreeBoardDto freeBoard);
	
	void deleteFreeBoard(int freeBoardNo);

	void updateFreeBoardviewCount(int freeBoardNo); // 자유게시판 조회수 증가  

	FreeBoardAttachDto selectFreeBoardAttachByAttachNo(int attachNo);  // 첨부번호를 이용해 첨부파일 찾기 

	String getMemberId(int freeBoardNo);  //freeboardNo의 memberNo로 memberId 불러오기 
	
	String getMemberImage(int freeBoardNo); //freeboardNo의 memberNo로 memberImage 불러오기 

	List<FreeBoardDto> selectSearchFreeBoard(String keyword, int from, int count);  // 검색어 전체 조회

	List<FreeBoardDto> selectSearchByTitle(String keyword, int from, int count); // 검색어 - 제목으로 조회

	List<FreeBoardDto> selectSearchByContent(String keyword, int from, int count); // 검색어 - 내용으로 조회

	List<FreeBoardDto> selectSearchByMemeberId(String keyword, int from, int count); // 검색어 - 작성자아이디로 조회   
	
	List<FreeBoardDto> selectReportedFreeBoard();  // 신고된 게시글 조회
	
	void WriteFreeBoardReview(FreeBoardReviewDto freeBoardReview);

	void deleteFreeBoardReview(int freeBoardReplyNo);

	void editFreeBoardReview(FreeBoardReviewDto freeBoardReview);

	List<FreeBoardReviewDto> getReviewListByFreeBoardNo(int freeboardNo);

	FreeBoardReviewDto findFreeBoardReviewByFreeBoardReplyNo(int freeBoardReplyNo);
	
	void updateReplySequence(FreeBoardReviewDto freeBoardReview);
	
	void writeRereply(FreeBoardReviewDto freeBoardReview);
	

	

	

	

}


