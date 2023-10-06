package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.PrivateQnaDto;

public interface PrivateQnaMapper {

	
	/*
	 * @Insert( "insert into PrivateQ (QnaType, QnaTitle, QnaContent, QnaDate) " +
	 * "values (#{qnaType}, #{qnaTitle}, #{qnaContent}, now())") //얘가 dto
	 */	 	
	
    @Insert("INSERT INTO PrivateQ (QnaType, QnaTitle, QnaContent, QnaDate, memberNo) " +
	            "VALUES (#{qnaType}, #{qnaTitle}, #{qnaContent}, NOW(), #{memberNo})")
	@Options(useGeneratedKeys = true, keyProperty = "qnaNo")
	void insertBoard(PrivateQnaDto privateqna);
	
	
    //관리자만 볼수있는거
    @Select( "SELECT QnANo, QnaType, QnATitle, QnAContent, QnaDate, memberNo "
			+ "FROM PrivateQ "
			+ "ORDER BY QnANo DESC "
			+ "LIMIT #{from}, #{count}")
			List<PrivateQnaDto>selectAllBoard(@Param("from")int from,@Param("count")int count );
	
	@Select( "SELECT QnaNo, QnAType, QnAtitle, QnaContent, QnaDate, memberNo "
			+ "FROM PrivateQ " 
			+ "WHERE QnaNo = #{ qnaNo } ")
	PrivateQnaDto selectQnaBoardByQnaNo(@Param("qnaNo")int qnaNo);
	
	//
	@Select("SELECT QnANo, QnaType, QnaTitle,QnAContent,QnaDate,memberNo, answered " 
			+ "FROM PrivateQ " 
			+ "ORDER BY QnANo desc "
			+ "limit #{from}, #{count}")
	
	List<PrivateQnaDto> selectPrivateQnaByPage(@Param("from") int from,@Param("count") int count);
	 
	
	@Select("select count(*) from PrivateQ") //관리자 페이징 
	int selectPrivateQnaCount();
	
	@Select("SELECT count(*) FROM PrivateQ WHERE memberNo = #{memberNo}") //일반 회원 페이징 
	int selectPrivateQnaCountByMemberNo(@Param("memberNo") int memberNo);
	
	
	/////////////////////
	
	@Update("UPDATE PrivateQ " 
			+ "SET answered = #{answered} " 
			+ "WHERE QnaNo = #{qnaNo}")
	 void updateAnswerStatus(@Param("qnaNo") int qnaNo, @Param("answered") boolean answered);
	
	 
	
	@Select("SELECT answered " 
			+ "FROM PrivateQ " 
			+ "WHERE QnaNo = #{qnaNo}")
	boolean getAnswerStatus(@Param("qnaNo") int qnaNo); // 답변여부 
	
	
	
	
	@Select("SELECT memberId "
	        + "FROM Member "
	        + "WHERE memberNo = (SELECT memberNo "
	        + "FROM PrivateQ "
	        + "WHERE QnaNo = #{qnaNo})")
	String getMemberIdByQnaNo(@Param("qnaNo") int qnaNo);
	
	
	/*
	 * @Select("SELECT * " + "FROM PrivateQ " + "WHERE memberNo = #{userId} " +
	 * "ORDER BY QnANo DESC") List<PrivateQnaDto>
	 * selectBoardByUserId(@Param("userId") int userId);
	 */
	
	// 자기기 쓴글만 볼수있는거 
	@Select("SELECT * "
	        + "FROM PrivateQ "
	        + "WHERE memberNo = #{memberNo} "
	        + "ORDER BY QnANo DESC "
	        + "LIMIT #{from}, #{count}")
	List<PrivateQnaDto> selectBoardByMemberNo(@Param("memberNo") int memberNo, @Param("from")int from , @Param("count")int count);
	
}
