package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.PrivateQnaDto;

public interface PrivateQnaMapper {

	@Insert( "insert into PrivateQ (QnaType, QnaTitle, QnaContent, QnaDate) "
			+ "values (#{qnaType}, #{qnaTitle}, #{qnaContent}, now())") //얘가 dto
	
	@Options(useGeneratedKeys = true, keyProperty = "qnaNo")
	void insertBoard(PrivateQnaDto privateqna);
	
	
	@Select( "select QnANo, QnaType, QnATitle, QnAContent, QnaDate, memberNo "
			+ "from PrivateQ "
			+ "order by QnANo desc")
			List<PrivateQnaDto>selectAllBoard();
	
	@Select( "select QnaNo, QnAType, QnAtitle, QnaContent, QnaDate "
			+ "from PrivateQ " 
			+ "where QnaNo = #{ qnaNo } ")
	PrivateQnaDto selectQnaBoardByQnaNo(@Param("qnaNo")int qnaNo);
	
	
}
