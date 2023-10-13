package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	
	
	


	@Insert("insert into Notice (noticeTitle, noticeContent) "         
			+ "values (#{ noticeTitle }, #{ noticeContent })")
	
	@Options(useGeneratedKeys = true, keyProperty = "noticeNo")             
	
	void writenotice(NoticeDto notice) throws Exception;
	
	
	
	@Select("select noticeNo, noticeTitle, ViewCount, noticeDate "     
			+ " from Notice "
			+ "order by noticeNo desc")
	
	List<NoticeDto> selectAllnotice();
	
	@Select("select noticeNo, noticeTitle, noticeDate, ViewCount, noticeContent " 
			+ "from Notice "
			+ "where noticeNo = #{ noticeNo }")
	NoticeDto selectnoticeBynoticeNo(@Param("noticeNo") int noticeNo);
	
		
	@Update("update Notice "
			+ "set noticeTitle = #{ noticeTitle }, noticeContent = #{ noticeContent } "  
			+ "where noticeNo = #{ noticeNo }")  
	void updatenotice(NoticeDto notice);
	
	
	@Update("update Notice "                                      
			+ "set noticeDelete = true "
			+ "where noticeNo = #{ noticeNo }")
	void deleteNotice(@Param("noticeNo") int noticeNo);  
	
	@Update("update Notice "
	        + "set viewCount = viewCount + 1 " 
	        + "where noticeNo = #{noticeNo}")
	void updateviewCount(@Param("noticeNo") int noticeNo);



	@Select("select count(*) "
			+ "from Notice")
	int selectNoticeCount();
	
	
	@Select("select noticeNo, noticeTitle, viewCount, noticeDate "
			+ "from Notice "
			+ "order By noticeNo desc "
			+ "limit #{from}, #{count}")
	List<NoticeDto> selectNoticeByPage(@Param("from") int from, @Param("count") int count);

	
	}
