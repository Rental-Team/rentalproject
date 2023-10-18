package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.ItemReviewDto;

public interface ItemReviewMapper {
	
	@Insert("insert into ItemReview (itemNo, reviewWriter, reviewContent, reviewDate, parents, sequence, depth) "
			+ "values (#{itemNo}, #{reviewWriter}, #{reviewContent}, #{reviewDate}, 0, 1, 0)")
	@Options(useGeneratedKeys = true, keyProperty = "reviewNo", keyColumn = "reviewNo") 
	void insertItemReview(ItemReviewDto itemReview);
	
	@Update("update ItemReview "
			+ "set parents = #{parents} "
			+ "where reviewNo = #{reviewNo} ")
	void updateParents (@Param("parents") int parents, @Param("reviewNo") int reviewNo);
	
	
	@Select("select reviewNo, itemNo, reviewWriter, reviewContent, itemReviewDelete, reviewDate, parents, sequence, depth "
			+ "from ItemReview where itemNo = #{itemNo} "
			+ "order by parents desc, sequence asc")
	List<ItemReviewDto> selectItemReviewByItemNo(int itemNo);

	@Update("update ItemReview "                 // 후기 삭제
			+ "set itemReviewDelete = true "
			+ "where reviewNo = #{reviewNo}")
	void deleteItemReview(@Param("reviewNo") int reviewNo);

	@Update("update ItemReview "
			+ "set reviewContent = #{ reviewContent } "  // 후기 수정
			+ "where reviewNo= #{ reviewNo }")
	void editItemReview(ItemReviewDto itemReview); 

	
	@Select("select reviewNo, itemNo, reviewWriter, reviewContent, reviewDate, itemReviewDelete, Parents, Sequence, Depth "
	        + "from ItemReview "
	        + "where reviewNo = #{reviewNo} and itemReviewDelete = false")
	ItemReviewDto selectItemReviewByReviewNo(int reviewNo);

	@Update("update ItemReview "
	        + "set sequence = sequence + 1 "
	        + "where parents = #{ parents } and sequence >= #{ parents } ")
	void updateSequence(ItemReviewDto itemReview);

	@Insert("insert into ItemReview (itemNo, reviewWriter, reviewContent, parents, sequence, depth) "
	        + "values (#{itemNo}, #{reviewWriter}, #{reviewContent}, #{parents}, #{sequence}, #{depth})")
	void insertReply(ItemReviewDto itemReview);

}
