package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rentalproject.dto.MemberDto;

@Mapper
public interface AccountMapper {
	
	// 회원가입 정보 입력
	@Insert("insert into Member (memberId, password, userName, " + 
			"nickname, phoneNo, email, address, deposite, photo, introduce) " + 
			"values (#{memberId}, #{password}, #{userName}, " + 
			"#{nickname}, #{phoneNo}, #{email}, #{address}, #{deposite}, #{photo}, #{introduce})")
	
	void insertMember(MemberDto member);
	
	// 로그인 = 프로필에 조회할 내용과 일치해서 따로 ProfileMapper에 만들지 않음(상세 조회)
	@Select("select memberNo, memberId, password, userName, nickname, " + 
			"phoneNo, email, address, deposite, regDate, deleteCheck, photo, introduce, admin " + 
			"from Member where memberId = #{memberId} and password = #{password} and deleteCheck = false")
	MemberDto selectMemberByIdAndPw(MemberDto member);
	
	
}
