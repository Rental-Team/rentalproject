package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface AccountService {

	void register(MemberDto member);

	MemberDto findLoginMember(MemberDto member);

}
