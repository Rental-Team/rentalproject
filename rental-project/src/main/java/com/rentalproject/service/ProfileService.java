package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface ProfileService {

	MemberDto profileByMemberId(MemberDto member);

	void editProfile(MemberDto member);

	

}
