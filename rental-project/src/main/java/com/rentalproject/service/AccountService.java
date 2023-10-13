package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface AccountService {

	void register(MemberDto member);
	
	boolean checkRegisterId(String memberId);
	
	String emailContent(String email);
	
//	void insertKakao(MemberDto member);

	MemberDto findLoginMember(MemberDto member);

	MemberDto findKakaoMember(MemberDto member);
	
	MemberDto findLoginId(MemberDto member);

	boolean findLoginPw(String memberId, String email);

//	void newPw(MemberDto member);

	MemberDto selfUpdatePw(MemberDto member);

	





	

	MemberDto getMemberInfo(int memberNo);

	

	

	

	

}
