package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.PrivateQnaAnswerDto;

@Mapper
public interface PrivateQnaAnswerMapper {

	@Insert("insert into PrivateA (QnANo , answerContent) "
			+ "values (#{qnaNo}, #{answerContent})")
	
	void insertAnswer(PrivateQnaAnswerDto privateQnaAnswer);
	
	@Select("select qnaNo , answerContent " 
			+ "from PrivateA "
			+ "where QnaNo= #{ qnaNo }"
										)
	List<PrivateQnaAnswerDto> selectPrivateQnaAnserbyQnaNo(@Param("qnaNo") int qnaNo);
	
	//답변 수정//
	@Update( "update PrivateA "
			+ "set answerContent = #{ answerContent } "
			+ "where QnaNo= #{ qnaNo } "
										)
	void updateAnswer(PrivateQnaAnswerDto privateQnaAnswer);

	

}		
