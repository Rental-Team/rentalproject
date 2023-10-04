package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface AccountService {

	void register(MemberDto member);

	MemberDto findLoginMember(MemberDto member);

	MemberDto findLoginId(MemberDto member);

	MemberDto findLoginPw(MemberDto member);

	void newPw(MemberDto member);
	
	MemberDto selfupdatePw(MemberDto member);

	MemberDto checkRegisterId(MemberDto member);

	

}
