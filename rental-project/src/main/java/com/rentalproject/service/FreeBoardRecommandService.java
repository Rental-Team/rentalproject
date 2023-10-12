package com.rentalproject.service;

import com.rentalproject.dto.FreeBoardRecommandDto;

public interface FreeBoardRecommandService {

	int addFreeBoardRecommand(FreeBoardRecommandDto freeboardRecommand);

	int recommandcount(int freeBoardNo);

}
