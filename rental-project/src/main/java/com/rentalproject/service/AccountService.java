package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface AccountService {

	void register(MemberDto member);

	MemberDto findLoginMember(MemberDto member);

	MemberDto findLoginId(MemberDto member);

	MemberDto findLoginPw(MemberDto member);

	MemberDto updateLoginPassword(MemberDto member);

}
