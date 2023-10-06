package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface ProfileService {

	MemberDto profileByMemberId(MemberDto member);

	void updateProfile(MemberDto member);
	
	void deleteUser(String memberId);

	MemberDto selectProfile(MemberDto member);

	

	

	

}
