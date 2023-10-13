package com.rentalproject.mapper;
 

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.FreeBoardReportDto;

@Mapper
public interface FreeBoardReportMapper {
	
	@Insert("insert into FreeBoardReport (memberNo, freeBoardNo) "   
		    + "values (#{memberNo}, #{freeBoardNo})")
		public int addFreeBoardReport(FreeBoardReportDto freeboardReport);            // 신고하기 
	
	@Select("select * "
			+ "from FreeBoardReport "
			+ "where memberNo = #{memberNo} and freeBoardNo = #{freeBoardNo}")
	public FreeBoardReportDto checkFreeBoardReport(FreeBoardReportDto freeboardReport);    // 신고하기 조회 
	
	@Select("select count(*) "
			+ "from FreeBoardReport "
			+ "where freeBoardNo=#{freeBoardNo}")
	public int reportcount(@Param("freeBoardNo") int freeBoardNo);
}
