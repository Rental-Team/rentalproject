package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.FreeBoardReviewDto;

@Mapper
public interface FreeBoardReviewMapper {
	
	@Insert("insert into FreeBoardReview (freeBoardNo, replyContent, replyParents, replySequence, replyDepth) "
			+ "values (#{freeBoardNo}, #{replyContent}, 0, 1, 0)")
	// 먼저 insert 하고, select 하게 하는 구문 : 자동증가된 컬럼(freeBoardReplyNo)의 값을 DTO 에 넣기
	@Options(useGeneratedKeys = true, keyProperty = "freeBoardReplyNo", keyColumn = "freeBoardReplyNo")
	
	void insertFreeBoardReview(FreeBoardReviewDto freeboardReview);
	
	// 댓글리스트 조회
	@Select("select freeBoardReplyNo, freeBoardNo, replyContent, replyCreateDate "
			+ "from FreeBoardReview "
			+ "where freeBoardNo = #{freeBoardNo} "
			+ "order by replyParents desc, replySequence asc")
	List<FreeBoardReviewDto> selectFreeBoardReviewByFreeBaordNo(@Param("freeBoardNo") int FreeBoardNo);
	
	// 부모 댓글과 대댓글의 관계??? 표현 하는 거 
	@Update("update FreeBoardReview "
			+ "set replyParents = #{replyParents} "
			+ "where freeBoardReplyNo = #{freeBoardReplyNo} ")
	void updateReplyParents (@Param("replyParents") int replyParents, @Param("freeBoardReplyNo") int freeBoardReplyNo);
	
	

}
