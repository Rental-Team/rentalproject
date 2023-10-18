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
	
	@Insert("insert into FreeBoardReview (freeBoardNo, replyWriter, replyWriterImage, replyContent, replyParents, replySequence, replyDepth) "
			+ "values (#{freeBoardNo}, #{ replyWriter }, #{ replyWriterImage }, #{replyContent}, 0, 1, 0)")
	// 먼저 insert 하고, select 하게 하는 구문 : 자동증가된 컬럼(freeBoardReplyNo)의 값을 DTO 에 넣기
	@Options(useGeneratedKeys = true, keyProperty = "freeBoardReplyNo", keyColumn = "freeBoardReplyNo")
	
	void insertFreeBoardReview(FreeBoardReviewDto freeBoardReview);
	
	// 댓글리스트 조회
	@Select("select freeBoardReplyNo, freeBoardNo, replyWriter, replyWriterImage, replyContent, replyCreateDate, replyDelete, replyParents, replySequence, replyDepth "
			+ "from FreeBoardReview "
			+ "where freeBoardNo = #{freeBoardNo} "
			+ "order by replyParents desc, replySequence asc")
	List<FreeBoardReviewDto> selectFreeBoardReviewByFreeBaordNo(@Param("freeBoardNo") int FreeBoardNo);
	
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
