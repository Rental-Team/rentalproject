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
	public void editProfile(MemberDto member) {
		
		profileMapper.updateProfile(member);
	}
	
	@Override // 비밀번호 수정
	public void editPassword(String password) {
		
		profileMapper.updatepassword(password);
	}
	
	@Override // 회원 탈퇴
	public void deleteUser(String memberId) {
		profileMapper.deleteUser(memberId);
	}
	

}
