package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.ZzimDto;
import com.rentalproject.mapper.ZzimMapper;

public class ZzimServiceImpl implements ZzimService{

	@Autowired
	private ZzimMapper zzimMapper;
	
	
	@Override
	public int addZzim(ZzimDto zzim) {
		
		ZzimDto checkZzim = zzimMapper.checkZzim(zzim);
		
		// 장바구니 데이터 체크
		if(checkZzim != null) {
			
			return 2;
		}
		
		// 장바구니 등록, 에러 시 0
		try {
			return zzimMapper.addZzim(zzim);
		} catch (Exception e) {
			
			return 0;
		}
		
	}


	@Override
	public List<ZzimDto> getZzimList(int memberNo) {
		List<ZzimDto> zzim = zzimMapper.getZzim(memberNo);
		
		for(ZzimDto dto : zzim) {
			dto.initSaleTotal();
		}
		
		return zzim;
	}


	@Override
	public int modifyCount(ZzimDto zzim) {
		
		return zzimMapper.modifyCount(zzim);
	}


	@Override
	public int deleteZzim(ZzimDto zzim) {
		
		return zzimMapper.deleteZzim(zzim);
	}
	
}
