package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.FreeBoardAttachDto;
import com.rentalproject.dto.FreeBoardDto;

@Mapper
public interface FreeBoardMapper {
	
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
 
	 
	
}

	