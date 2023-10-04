package com.rentalproject.service;

import com.rentalproject.common.Util;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.AccountMapper;

import lombok.Setter;

public class AccountServiceImpl implements AccountService {
	
	
	@Setter
	private AccountMapper accountMapper;
	
	@Override // 회원가입
	public void register(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		Util.getHashedString(member.getPasswordConfirm(), "SHA-256");
		member.setPasswordConfirm(hashedPasswd);
		
		accountMapper.insertMember(member);
		
	}
	
	@Override
	public MemberDto checkRegisterId(MemberDto member) {
		
		MemberDto registerId = accountMapper.checkId(member.getMemberId());
		
		return registerId;
	}
	
	@Override // 로그인
	public MemberDto findLoginMember(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
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
	
	@Override // 새 비밀번호 임시 발급 수정
	public void newPw(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		Util.getHashedString(member.getPasswordConfirm(), "SHA-256");
		member.setPasswordConfirm(hashedPasswd);
		
		accountMapper.newPassword(member);
	}
	
	@Override // 자체 비밀번호 수정
	public MemberDto selfupdatePw(MemberDto member) {
		MemberDto userNewPassword = accountMapper.selfupdatepassword(member);
		return userNewPassword;
	}
	
}
