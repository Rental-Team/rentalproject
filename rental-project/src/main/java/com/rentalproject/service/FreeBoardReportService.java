package com.rentalproject.service; 
 
 

import com.rentalproject.dto.FreeBoardReportDto;

public interface FreeBoardReportService {
	
	int addFreeBoardReport(FreeBoardReportDto freeboardReport);   
	
	int reportcount(int freeBoardNo);
 
}
