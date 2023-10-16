package com.rentalproject.service;

import java.sql.Date;
import java.util.List;

import com.rentalproject.dto.VisitDto;

public interface VisitService {

	VisitDto getVisitDataByDate(Date date);

	void addVisit(Date date);

	List<VisitDto> getWeeklyVisitData();

	int getVisitCountToday();
    
}
