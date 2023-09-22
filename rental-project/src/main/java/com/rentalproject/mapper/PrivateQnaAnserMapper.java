package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.PrivateQnaAnswerDto;

@Mapper
public interface PrivateQnaAnserMapper {

	@Insert("insert into PrivateA (QnANo , answerContent) "
			+ "values (#{qnaNo}, #{answerContent})")
	
	void insertAnswer(PrivateQnaAnswerDto privateQnaAnswer);
	
	@Select("select qnaNo , answerContent " 
			+ "from PrivateA "
			+ "where QnaNo= #{ qnaNo }"
										)
	List<PrivateQnaAnswerDto> selectPrivateQnaAnserbyQnaNo(@Param("qnaNo") int qnaNo);
	
	
}
