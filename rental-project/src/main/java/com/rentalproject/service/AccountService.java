package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;

public interface AccountService {

	void register(MemberDto member);
	
	boolean checkRegisterId(String memberId);
	
	boolean checkRegisterNickname(String nickname);
	
	String emailContent(String email);
	
//	void insertKakao(MemberDto member);

	MemberDto findLoginMember(MemberDto member);

	MemberDto findKakaoMember(MemberDto member);
	
	MemberDto findLoginId(String userName, String phoneNo);

	MemberDto findLoginPw(String memberId, String email);

	void updateLoginPw(String memberId, String password);
	
	String emailContentForTemporaryPw(String email);
	
	MemberDto selfUpdatePw(MemberDto member);

	MemberDto getMemberInfo(int memberNo);

	
	

	


	

	

	

	

	

	

}
