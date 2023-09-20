package com.rentalproject.dao;

import com.rentalproject.dto.MemberDto;

public interface AccountDao {

	void insertMember(MemberDto member);
	
	MemberDto selectMemberByIdAndPw(String id, String pw);
}
