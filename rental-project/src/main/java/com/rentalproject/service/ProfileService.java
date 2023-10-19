package com.rentalproject.service;

import java.util.List;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.MemberDto;

public interface ProfileService {
	
	MemberDto selectRegDate(String memberId);
	
//	MemberDto selectProfile(MemberDto member);
	
	int getMyFreeBoardCountByMemberNo(int memberNo);

	List<FreeBoardDto> listMyFreeBoardByMemberNo(int memberNo, int from, int count);

	String getMemberIdByMyFreeBoardNo(int freeBoardNo);

	void updateProfile(MemberDto member, String useDefaultPhoto);
	
	void deleteUser(String memberId);

	

	

	

	

	

	

	

}
