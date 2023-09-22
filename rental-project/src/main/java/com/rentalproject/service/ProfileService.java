package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface ProfileService {

	MemberDto profileByMemberId(MemberDto member);

	void editProfile(MemberDto member);

//	void deleteUser(MemberDto member);

	void editPassword(String password);
	
	void deleteUser(String memberId);

	

	

}
