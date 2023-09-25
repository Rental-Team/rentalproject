package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.AdminMapper;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<MemberDto> MemberList() {
		
		
		return adminMapper.allMemberList();
	}
	
	

}
