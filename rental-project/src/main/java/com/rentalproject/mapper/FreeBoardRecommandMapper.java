package com.rentalproject.mapper;
 

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.FreeBoardRecommandDto; 

@Mapper
public interface FreeBoardRecommandMapper {
	
	@Insert("insert into FreeBoardRecommand (memberNo, freeBoardNo) "   
		    + "values (#{memberNo}, #{freeBoardNo})")
		public int addFreeBoardRecommand(FreeBoardRecommandDto freeboardRecommand);            
	
	@Select("select * "
			+ "from FreeBoardRecommand "
			+ "where memberNo = #{memberNo} and freeBoardNo = #{freeBoardNo}")
	public FreeBoardRecommandDto checkFreeBoardRecommand (FreeBoardRecommandDto freeboardRecommand);    
	
	@Select("select count(*) "
			+ "from FreeBoardRecommand "
			+ "where freeBoardNo = #{freeBoardNo}")
	public int recommandcount(@Param("freeBoardNo") int freeBoardNo);
}
