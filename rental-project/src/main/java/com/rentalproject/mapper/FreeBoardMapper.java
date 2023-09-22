package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.FreeBoardDto;

@Mapper
public interface FreeBoardMapper {
	
	@Insert("insert into FreeBoard (freeBoardTitle, freeBoardContent) "        // 자유게시판 게시글 작성 데이터 
			+ "values (#{ freeBoardTitle }, #{ freeBoardContent })")
	
	@Options(useGeneratedKeys = true, keyProperty = "freeBoardNo")             // insert 후 생성된 자동증가 번호 DTO에 저장
	
	void writeFreeBoard(FreeBoardDto freeboard) throws Exception;
	
	
	@Select("select freeBoardNo, memberNo, freeBoardTitle, freeBoardViewCount, freeBoardDate, freeBoardDelete "     // 자유게시판 게시글 list에 모든 게시글 자료 가져오기
			+ " from FreeBoard "
			+ "order by freeBoardNo desc")
	
	List<FreeBoardDto> selectAllFreeBoard();
	
	@Select("select freeBoardNo, freeBoardTitle, memberNo, freeBoardDate, freeBoardViewCount, freeBoardContent " // 자유게시판 게시글 상세보기
			+ "from FreeBoard "
			+ "where freeBoardNo = #{ freeBoardNo }")
	FreeBoardDto selectFreeBoardByFreeBoardNo(@Param("freeBoardNo") int freeBoardNo);

	
	
	@Update("update FreeBoard "
			+ "set freeBoardTitle = #{ freeBoardTitle }, freeBoardContent = #{ freeBoardContent } " // 자유게시판 게시글 수정하기 
			+ "where freeBoardNo = #{ freeBoardNo }")  
	void updateFreeBoard(FreeBoardDto freeBoard);
	
	
	@Update("update FreeBoard "                                      // 자유게시판 게시글 삭제하기 ( 삭제이지만 데이터 남겨야하니깐 update로 )
			+ "set freeBoardDelete = true "
			+ "where freeBoardNo = #{ freeBoardNo }")
	void deleteFreeBoard(@Param("freeBoardNo") int freeBoardNo);  
}
