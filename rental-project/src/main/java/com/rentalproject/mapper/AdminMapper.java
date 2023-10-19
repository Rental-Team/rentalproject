package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.CategoryDto;
import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.FreeBoardReviewDto;
import com.rentalproject.dto.ItemAttachDto;
import com.rentalproject.dto.ItemDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.dto.NoticeDto;

@Mapper
public interface AdminMapper {
	
	// 회원 리스트 한번에 조회
	@Select("select * from Member order by regDate desc")
	List<MemberDto> allMemberList();
	
	// 회원 리스트 부분 조회
	@Select("select * from Member order by regDate desc limit #{from}, #{count}")
	List<MemberDto> selectMemberByPage(@Param("from") int from, @Param("count") int count);
	
	// 페이저 (데이터 갯수 조회)
	@Select("select count(*) from Member ") 
	int selectMemberCount();

	// 회원 상세 조회
	@Select("select * from Member where memberNo = #{memberNo}")
	MemberDto selectMemberDetail(@Param("memberNo") int memberNo);
	
	// 상품 등록을 위한 메서드
	@Insert( "insert into Item ( itemDetail , itemName, itemPrice, cateCode, itemStock ) "
			+ "values ( #{ itemDetail }, #{ itemName }, #{ itemPrice }, #{ cateCode }, #{itemStock}) ")
	@Options(useGeneratedKeys = true, keyProperty = "itemNo")
	void insertItem(ItemDto item);

	// 상품 이미지 저장을 위한 메서드
	@Insert( "insert into itemAttach (attachNo, itemNo, userFileName, savedFileName) "
			+ "values (#{attachNo}, #{itemNo} ,#{userFileName}, #{savedFileName}) ") 
	void insertItemAttach(ItemAttachDto attach);
	
	// 카테고리 리스트
	@Select("select * "
			+ "from itemCate "
			+ "order by cateCode ")
	public List<CategoryDto> cateList();
	
	// 상품 게시판 리스트
	@Select("select i.itemNo, i.itemName, i.viewCount, i.itemDate, i.deleted, i.itemStock, (select iA.savedFileName from itemAttach iA where iA.itemNo = i.itemNo) thumbnail "
			+ "from Item i "
			+ "order by i.itemNo desc")
	List<ItemDto> allItemList();
	
	// 페이징이 적용된 상품 게시판 
	@Select("select i.itemNo, i.itemPrice , i.itemName, i.viewCount, i.itemDate, i.deleted, i.itemStock, (select MAX(iA.savedFileName) from itemAttach iA where iA.itemNo = i.itemNo) thumbnail "
			+ "from Item i "
			+ "order by i.itemNo desc " + 
			"limit #{from}, #{count}")
	List<ItemDto> selectItemByPage(@Param("from") int from, @Param("count") int count); 
	
	// 상품 갯수
	@Select("select count(*) "
			+ "from Item")
	int selectItemCount();
	
//	select itemNo, itemName, (select cateName from itemCate where cateCode = itemCate.cateCode) cateName, 
//	cateCode, itemPrice, itemStock, itemDetail
//	from Item 
//	where itemNo = #{itemNo}

	// 상세 보기
	@Select("select itemNo, itemName, (select cateName from itemCate where cateCode = Item.cateCode) cateName, "
			+ " cateCode, itemPrice, itemStock, itemDetail, itemDate , deleted " +
			"from Item " +
			"where itemNo = #{ itemNo } and deleted = false")
	ItemDto read(@Param("itemNo") int itemNo);
	
	// 상품 수정
	@Update("update Item "
			+ "set itemName = #{ itemName } , itemDetail = #{ itemDetail }, itemPrice = #{ itemPrice } "
			+ "where itemNo = #{ itemNo } ")
	void updateItem(ItemDto item);
	
	// 상품 삭제
	@Delete("delete from Item "
			+ "where itemNo = #{itemNo} ")
	void deleteBoard(@Param("itemNo") int itemNo);
	
	
	// 상품 첨부파일(특정 상품 이미지에 있는) 다운로드
	@Select(  "select attachNo, itemNo, userFileName, savedFileName "
			+ "from itemAttach "
			+ "where itemNo = #{ itemNo }")
	List<ItemAttachDto> selectItemAttachByItemNo(@Param("itemNo") int itemNo);
	
	// 상품 이미지 정보를 조회
	@Select(  "select attachNo, itemNo, userFileName, savedFileName "
			+ "from itemAttach "
			+ "where attachNo = #{ attachNo }")
	ItemAttachDto selectItemAttachByAttachNo(@Param("attachNo") int attachNo);
	
	
	
	////////////////////////////////////////////
	// notice
	@Insert("insert into Notice (noticeTitle, noticeContent) "         
			+ "values (#{ noticeTitle }, #{ noticeContent })")
	
	@Options(useGeneratedKeys = true, keyProperty = "noticeNo")             
	
	void writenotice(NoticeDto notice);
	
	
	
	@Select("select noticeNo, noticeTitle, ViewCount, noticeDate "     
			+ " from Notice "
			+ "order by noticeNo desc")
	
	List<NoticeDto> selectAllnotice();
	
	@Select("select noticeNo, noticeTitle, noticeDate, ViewCount, noticeContent " 
			+ "from Notice "
			+ "where noticeNo = #{ noticeNo }")
	NoticeDto selectnoticeBynoticeNo(@Param("noticeNo") int noticeNo);
	
		
	@Update("update Notice "
			+ "set noticeTitle = #{ noticeTitle }, noticeContent = #{ noticeContent } "  
			+ "where noticeNo = #{ noticeNo }")  
	void updatenotice(NoticeDto notice);
	
	
	@Update("update Notice "                                      
			+ "set noticeDelete = true "
			+ "where noticeNo = #{ noticeNo }")
	void deleteNotice(@Param("noticeNo") int noticeNo);  
	
	@Update("update Notice "
	        + "set viewCount = viewCount + 1 " 
	        + "where noticeNo = #{noticeNo}")
	void updateviewCount(@Param("noticeNo") int noticeNo);
	
	// 자유게시판
	@Insert("insert into FreeBoard (freeBoardTitle, memberNo, freeBoardContent) "        // 자유게시판 게시글 작성 데이터 
			+ "values (#{ freeBoardTitle }, #{ memberNo }, #{ freeBoardContent })")
	@Options(useGeneratedKeys = true, keyProperty = "freeBoardNo")             // insert 후 생성된 자동증가 번호 DTO에 저장
	
	void writeFreeBoard(FreeBoardDto freeboard) throws Exception;
	
	@Insert("insert into FreeBoardAttach (attachNo, freeBoardNo, attachFileName, savedFileName) "  // 자유게시판 첨부파일 첨부 데이터 
			+ "values (#{attachNo}, #{freeBoardNo}, #{attachFileName}, #{savedFileName}) ")
	void insertFreeBoardAttach(FreeBoardAttachDto freeBoardAttach);
	
	
	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "     // 자유게시판 게시글 list에 모든 게시글 자료 가져오기
			+ " from FreeBoard "
			+ "order by freeBoardNo desc") 
	List<FreeBoardDto> selectAllFreeBoard();
	
	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "     // 페이징
			+ " from FreeBoard "
			+ "order by freeBoardNo desc "
			+ "limit #{from}, #{count}") 
	List<FreeBoardDto> selectFreeBoardByPage(@Param("from") int from, @Param("count") int count);
	
	@Select("select count(*) from FreeBoard")
	int selectFreeBoardCount();
	
	@Select("SELECT COUNT(*) FROM FreeBoard "
		    + "WHERE freeBoardDelete = 0 "
		    + "AND ("
		    + "freeBoardTitle LIKE CONCAT('%', #{keyword}, '%') " 
		    + "OR freeBoardContent LIKE CONCAT('%', #{keyword}, '%') " 
		    + "OR memberNo IN (SELECT memberNo FROM Member WHERE memberId LIKE CONCAT('%', #{keyword}, '%')))")
		int selectFreeBoardSearchCount(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM FreeBoard "
		    + "WHERE freeBoardDelete = 0 "
		    + "AND ("
		    + "freeBoardTitle LIKE CONCAT('%', #{keyword}, '%')) ")
		int selectFreeBoardTitleSearchCount(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM FreeBoard "
		    + "WHERE freeBoardDelete = 0 "
		    + "AND ("
		    + "freeBoardContent like concat('%', #{keyword}, '%'))")
		int selectFreeBoardContentSearchCount(@Param("keyword") String keyword);
	
	@Select("SELECT COUNT(*) FROM FreeBoard "
		    + "WHERE freeBoardDelete = 0 "
		    + "AND ("
		    + "memberNo in(select memberNo from Member where memberId like concat('%', #{keyword}, '%')))")
		int selectFreeBoardMemberSearchCount(@Param("keyword") String keyword);
	
	
	@Select("select freeBoardNo, freeBoardTitle, memberNo, freeBoardDate, freeBoardViewCount, freeBoardContent " // 자유게시판 게시글 상세보기
			+ "from FreeBoard "
			+ "where freeBoardNo = #{ freeBoardNo }")
	FreeBoardDto selectFreeBoardByFreeBoardNo(@Param("freeBoardNo") int freeBoardNo);
	
	@Select("select attachNo, freeBoardNo, attachFileName, savedFileName "                           // 특정 자유게시판 글의 첨부파일 다운로드 하기 
			+ "from FreeBoardAttach "
			+ "where freeBoardNo = #{ freeBoardNo } ")
	List<FreeBoardAttachDto> selectFreeBoardAttachByFreeBoardNo(@Param("freeBoardNo") int freeBoardNo);
	
	@Select("select attachNo, freeBoardNo, attachFileName, savedFileName "                           // 첨부파일 조회 하기 
			+ "from FreeBoardAttach "
			+ "where attachNo = #{ attachNo } ")
	FreeBoardAttachDto selectFreeBoardAttachByAttachNo(@Param("attachNo") int attachNo);

	
	
	@Update("update FreeBoard "
			+ "set freeBoardTitle = #{ freeBoardTitle }, freeBoardContent = #{ freeBoardContent } " // 자유게시판 게시글 수정하기 
			+ "where freeBoardNo = #{ freeBoardNo }")  
	void updateFreeBoard(FreeBoardDto freeBoard);
	
	
	@Update("update FreeBoard "                                      // 자유게시판 게시글 삭제하기 ( 삭제이지만 데이터 남겨야하니깐 update로 )
			+ "set freeBoardDelete = true "
			+ "where freeBoardNo = #{ freeBoardNo }")
	void deleteFreeBoard(@Param("freeBoardNo") int freeBoardNo);  
	
	
	@Update("update FreeBoard "                                     // 자유게시판 조회수 증가 
			+ "set freeBoardViewCount = freeBoardViewCount + 1 "
			+ "where freeBoardNo = #{ freeBoardNo }")
	void updateFreeBoardviewCount(int freeBoardNo);  
	
	@Select("select memberId "
			+ "from Member "
			+ "where memberNo = "
			+ "(select memberNo "
			+ "from FreeBoard "
			+ "where freeBoardNo = #{ freeBoardNo })")
	String getMemberId(@Param("freeBoardNo") int freeBoardNo);  // memberNo로 memberId 찾아오기 
	
	@Select("select memberImage "
			+ "from Member "
			+ "where memberNo = "
			+ "(select memberNo "
			+ "from FreeBoard "
			+ "where freeBoardNo = #{ freeBoardNo })")
	String getMemberImage(@Param("freeBoardNo") int freeBoardNo);  // memberNo로 memberImage 찾아오기 
	
	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "   // 전체 검색 데이터가져오기
			+ "from FreeBoard "
			+ "where freeBoardDelete = 0 "
			+ "and ("
			+ "freeBoardTitle like concat('%', #{keyword}, '%') " 
			+ "or freeBoardContent like concat('%', #{keyword}, '%') " 
			+ "or memberNo in(select memberNo from Member where memberId like concat('%', #{keyword}, '%'))) "
			+ "limit #{from}, #{count}")
	List<FreeBoardDto> selectSearchFreeBoard(@Param("keyword") String keyword, @Param("from") int from, @Param("count") int count);

	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "   // 제목 검색 데이터가져오기
			+ "from FreeBoard "
			+ "where freeBoardDelete = 0 "
			+ "and ("
			+ "freeBoardTitle like concat('%', #{keyword}, '%')) "
			+ "limit #{from}, #{count} ") 
	List<FreeBoardDto> selectSearchByTitle( @Param("keyword") String keyword, @Param("from") int from, @Param("count") int count);

	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "   // 내용 검색 데이터가져오기
			+ "from FreeBoard "
			+ "where freeBoardDelete = 0 "
   			+ "and (" 
			+ "freeBoardContent like concat('%', #{keyword}, '%')) "
			+ "limit #{from}, #{count} ")
	List<FreeBoardDto> selectSearchByContent( @Param("keyword") String keyword, @Param("from") int from, @Param("count") int count);
	
	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "   // 작성자 검색 데이터가져오기
			+ "from FreeBoard "
			+ "where freeBoardDelete = 0 "
			+ "and (" 
			+ "memberNo in(select memberNo from Member where memberId like concat('%', #{keyword}, '%')))"
			+ "limit #{from}, #{count}")
	List<FreeBoardDto> selectSearchByMemeberId( @Param("keyword") String keyword,@Param("from") int from, @Param("count") int count);
 
	
	@Select("select distinct fb.freeBoardNo, fb.memberNo, fb.freeBoardTitle, fb.freeBoardViewCount, fb.freeBoardDate, fb.freeBoardDelete " +
	        "from FreeBoard fb " +
	        "inner join FreeBoardReport fbr ON fb.freeBoardNo = fbr.freeBoardNo " +
	        "order by fb.freeBoardNo DESC")
	List<FreeBoardDto> selectReportedFreeBoard();
	
	// 댓글리스트 조회
		@Select("select freeBoardReplyNo, freeBoardNo, replyWriter, replyWriterImage, replyContent, replyCreateDate, replyDelete, replyParents, replySequence, replyDepth "
				+ "from FreeBoardReview "
				+ "where freeBoardNo = #{freeBoardNo} "
				+ "order by replyParents desc, replySequence asc")
		List<FreeBoardReviewDto> selectFreeBoardReviewByFreeBaordNo(@Param("freeBoardNo") int FreeBoardNo);
		
		@Insert("insert into FreeBoardReview (freeBoardNo, replyWriter, replyWriterImage, replyContent, replyParents, replySequence, replyDepth) "
				+ "values (#{freeBoardNo}, #{ replyWriter }, #{ replyWriterImage }, #{replyContent}, 0, 1, 0)")
		// 먼저 insert 하고, select 하게 하는 구문 : 자동증가된 컬럼(freeBoardReplyNo)의 값을 DTO 에 넣기
		@Options(useGeneratedKeys = true, keyProperty = "freeBoardReplyNo", keyColumn = "freeBoardReplyNo") 
		void insertFreeBoardReview(FreeBoardReviewDto freeBoardReview); 
		
		// 부모 댓글과 대댓글의 관계??? 표현 하는 거 
		@Update("update FreeBoardReview "
				+ "set replyParents = #{replyParents} "
				+ "where freeBoardReplyNo = #{freeBoardReplyNo} ")
		void updateReplyParents (@Param("replyParents") int replyParents, @Param("freeBoardReplyNo") int freeBoardReplyNo);
		
		// 댓글 삭제 ( 업데이트로 실제 지우는 것이 아니라 삭제 표시만 하는 거! )
		@Update("update FreeBoardReview " 
				+ "set replyDelete = true "
				+ "where freeBoardReplyNo = #{ freeBoardReplyNo }")
		void deleteFreeBoardReview(@Param("freeBoardReplyNo") int freeBoardReplyNo);
		
		// 댓글 수정
		@Update("update FreeBoardReview "
				+ "set replyContent = #{ replyContent } "
				+ "where freeBoardReplyNo = #{ freeBoardReplyNo }")
		void editFreeBoardReview(FreeBoardReviewDto freeBoardReview);
		
		@Select("select freeBoardReplyNo, freeBoardNo, replyWriter, replyWriterImage, replyContent, replyCreateDate, replyDelete, replyParents, replySequence, replyDepth "
		        + "from FreeBoardReview "
		        + "where freeBoardReplyNo = #{freeBoardReplyNo} and replyDelete = false")
		FreeBoardReviewDto selectFreeBoardReviewByFreeBoardNo(@Param("freeBoardReplyNo") int freeBoardReplyNo);

		@Update("update FreeBoardReview "
		        + "set replySequence = replySequence + 1 "
		        + "where replyParents = #{ replyParents } and replySequence >= #{ replyParents} ")
		void updateReplySequence(FreeBoardReviewDto freeBoardReview);

		@Insert("insert into FreeBoardReview (freeBoardNo, replyWriter, replyWriterImage, replyContent, replyParents, replySequence, replyDepth) "
		        + "values (#{freeBoardNo}, #{replyWriter}, #{replyWriterImage}, #{replyContent}, #{replyParents}, #{replySequence}, #{replyDepth})")
		void insertRereply(FreeBoardReviewDto freeBoardReview);

	 
}