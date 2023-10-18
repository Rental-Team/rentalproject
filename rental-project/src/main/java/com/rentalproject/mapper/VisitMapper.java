package com.rentalproject.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.VisitDto;

@Mapper
public interface VisitMapper {
	// 오늘의 방문 기록 조회
    @Select("Select visitDate from Visit " +
    		"where date_format(visitDate, '%Y%m%d') = date_format(now(), '%Y%m%d')")
    VisitDto selectTodayVisitCheck(Date date);

    // 오늘의 첫 방문 기록 추가
    @Insert("insert into Visit (visitDate, visitNumber) " + 
    		"values (date_format(now(), '%Y%m%d'), 1)")
    void insertTodayFirstVisit(VisitDto visit);

    // 오늘의 방문 기록 업데이트
    @Update("update Visit set visitCount = visitCount" + 
    		"where date_format(visitDate, '%Y%m%d') = date_format(now(), '%Y%m%d')")
    void updateTodayAddVisit(VisitDto visit);
}
