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
	
	@Override // 비번 변경
	public void editPassword(MemberDto member) {
		
		accountMapper.updatepassword(member);
		
	}
	
}
