package com.rentalproject.service;  
import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardReportDto;
import com.rentalproject.mapper.FreeBoardReportMapper; 

import lombok.Setter;

public class FreeBoardReportServiceImpl implements FreeBoardReportService {
	
	@Setter(onMethod_= {@Autowired})
	private FreeBoardReportMapper freeboardReportMapper;

	@Override
	public int addFreeBoardReport(FreeBoardReportDto freeboardReport) {

		FreeBoardReportDto checkReport = freeboardReportMapper.checkFreeBoardReport(freeboardReport);
		
		if (checkReport != null) {
			return 2;
		} try {
			return freeboardReportMapper.addFreeBoardReport(freeboardReport);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int reportcount(int freeBoardNo) {
		int countReport = freeboardReportMapper.reportcount(freeBoardNo);
		return countReport; 
	}  

}
