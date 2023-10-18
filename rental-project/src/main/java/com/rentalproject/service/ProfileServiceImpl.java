package com.rentalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.ProfileMapper;

public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileMapper profileMapper;

	
	@Override // 프로필 날짜를 위한 조회
	public MemberDto selectRegDate(String memberId) {
		MemberDto selectRegDate = profileMapper.selectRegDate(memberId);
		
		return selectRegDate;
	}
	
	// 내가 쓴 자유게시판 글 조회
	@Override
	public int getMyFreeBoardCountByMemberNo(int memberNo) {
		int count = profileMapper.selectMyFreeBoardCountByMemberNo(memberNo);
		return count;
	}
	
	@Override
	public List<FreeBoardDto> listMyFreeBoardByMemberNo(int memberNo, int from, int count){
		List<FreeBoardDto> myFreeBoardList;
		myFreeBoardList = profileMapper.selectMyFreeBoardByMemberNo(memberNo, from, count);
		return myFreeBoardList;
	}
	
	@Override
	public String getMemberIdByMyFreeBoardNo(int freeBoardNo) {
		String memberId = profileMapper.getMemberIdByMyFreeBoardNo(freeBoardNo);
		
		return memberId;
	}
	
	
	@Override // 프로필 수정
	public void updateProfile(MemberDto member) {
		
		if(member.getMemberImage() != null) {
			profileMapper.updateProfileWithMemberImage(member);
		} else {
			profileMapper.updateProfileWithoutMemberImage(member);
		}
	}
	
	@Override // 회원 탈퇴
	public void deleteUser(String memberId) {
		profileMapper.deleteUser(memberId);
	}

	

}
