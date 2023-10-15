package com.rentalproject.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalproject.dto.VisitDto;
import com.rentalproject.mapper.VisitMapper;

@Service
public class VisitServiceImpl implements VisitService{
    
	@Autowired
	private VisitMapper visitMapper;

    @Override
    public VisitDto getVisitDataByDate(Date date) {
        return visitMapper.selectTodayVisitCheck(date);
    }

    @Override
    public void addVisit(Date date) {
        VisitDto visit = visitMapper.selectTodayVisitCheck(date);
        if (visit != null) {
            // 이미 방문 기록이 있는 경우 업데이트
            visit.setVisitCount(visit.getVisitCount() + 1);
            visitMapper.updateTodayAddVisit(visit);
        } else {
            // 방문 기록이 없는 경우 새로 추가
            visit = new VisitDto();
            visit.setVisitDate(date);
            visit.setVisitCount(1);
            visitMapper.insertTodayFirstVisit(visit);
        }
    }

    @Override
    public List<VisitDto> getWeeklyVisitData() {
        List<VisitDto> weeklyData = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            Date date = Date.valueOf(currentDate.minusDays(i));
            VisitDto visit = visitMapper.selectTodayVisitCheck(date);
            if (visit != null) {
                weeklyData.add(visit);
            } else {
                VisitDto zeroVisit = new VisitDto();
                zeroVisit.setVisitDate(date);
                zeroVisit.setVisitCount(0);
                weeklyData.add(zeroVisit);
            }
        }

        return weeklyData;
    }
    
    public int getVisitCountToday() {
        Date currentDate = Date.valueOf(LocalDate.now());
        VisitDto visit = visitMapper.selectTodayVisitCheck(currentDate);
        if (visit != null) {
            return visit.getVisitCount();
        } else {
            return 0;
        }
    }
}