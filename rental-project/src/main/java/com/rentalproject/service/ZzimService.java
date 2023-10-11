package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.ZzimDto;

public interface ZzimService {

	
	// 찜 추가
	int addZzim(ZzimDto zzim);
	
	// 찜 리스트
	List<ZzimDto> getZzimList(int memberNo);
}
