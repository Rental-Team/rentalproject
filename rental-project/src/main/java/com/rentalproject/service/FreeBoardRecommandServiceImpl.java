package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardRecommandDto; 
import com.rentalproject.mapper.FreeBoardRecommandMapper; 

import lombok.Setter;

public class FreeBoardRecommandServiceImpl implements FreeBoardRecommandService {

	@Setter(onMethod_= {@Autowired})
	private FreeBoardRecommandMapper freeboardRecommandMapper;

	@Override
	public int addFreeBoardRecommand(FreeBoardRecommandDto freeboardRecommand) {

		FreeBoardRecommandDto checkRecommand = freeboardRecommandMapper.checkFreeBoardRecommand(freeboardRecommand);
		
		if (checkRecommand != null) {
			return 2;
		} try {
			return freeboardRecommandMapper.addFreeBoardRecommand(freeboardRecommand);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int recommandcount(int freeBoardNo) {
		int countRecommand = freeboardRecommandMapper.recommandcount(freeBoardNo);
		return countRecommand; 
	}  
}
