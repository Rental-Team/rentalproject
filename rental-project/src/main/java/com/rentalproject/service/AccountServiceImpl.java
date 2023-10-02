package com.rentalproject.service;

import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.AccountMapper;

import lombok.Setter;

public class AccountServiceImpl implements AccountService {
	
	
	@Setter
	private AccountMapper accountMapper;
	
	@Override // 회원가입
	public void register(MemberDto member) {
		accountMapper.insertMember(member);
		
	}
	
	@Override // 로그인
	public MemberDto findLoginMember(MemberDto member) {
		
		MemberDto loginMember = accountMapper.selectMemberByIdAndPw(member);
		return loginMember;
	}
	
	@Override // 아이디 찾기
	public MemberDto findLoginId(MemberDto member) {
		MemberDto userId = accountMapper.findIdByNameAndPhoneNo(member);
		return userId;
	}
	
	@Override // 비밀번호 찾기
	public MemberDto findLoginPw(MemberDto member) {
		MemberDto userPassword = accountMapper.findePwByIdAndNameAndPhoneNo(member);
		return userPassword;
	}
	
	@Override // 비밀번호 수정
	public MemberDto updateLoginPassword(MemberDto member) {
		MemberDto userNewPassword = accountMapper.updatepassword(member);
		return userNewPassword;
	}
	
}
