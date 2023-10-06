package com.rentalproject.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.AccountMapper;
import com.rentalproject.mapper.ProfileMapper;

import lombok.Setter;

public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileMapper profileMapper;
	
	@Setter
	private AccountMapper accountMapper;
	
	@Override // 프로필 보기
	public MemberDto profileByMemberId(MemberDto member) {
		MemberDto profile = accountMapper.selectMemberByIdAndPw(member);
	
		return profile;
	}
	
	@Override // 프로필 수정
	public void updateProfile(MemberDto member) {
		
	 	profileMapper.updateProfile(member);
		
	}
	
	@Override // 프로필 날짜를 위한 조회
	public MemberDto selectProfile(MemberDto member) {
		MemberDto selectprofile = profileMapper.selectProfile(member);
		
		return selectprofile;
	}
	
	
	@Override // 회원 탈퇴
	public void deleteUser(String memberId) {
		profileMapper.deleteUser(memberId);
	}

	

}
