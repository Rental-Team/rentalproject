package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.MemberDto;

@Mapper
public interface AccountMapper {
	
	// 회원가입 정보 입력
	@Insert("insert into Member (memberId, password, userName, " + 
			"nickname, phoneNo, email, address, deposite, regDate) " + 
			"values (#{memberId}, #{password}, #{userName}, " + 
			"#{nickname}, #{phoneNo}, #{email}, #{address}, #{deposite}, #{regDate})")
	
	void insertMember(MemberDto member);
	
	// 로그인 = 프로필에 조회할 내용과 일치해서 따로 ProfileMapper에 만들지 않음
	@Select("select memberNo, memberId, password, userName, nickname, " + 
			"phoneNo, email, address, deposite, regDate, deleteCheck, introduce, imageName " + 
			"from Member where memberId = #{memberId} and password = #{password}")
	MemberDto selectMemberByIdAndPw(MemberDto member);
	
	// 비밀번호 수정
	@Update("update Member set password = #{password} " + 
			"where memberId = #{memberId} and userName = #{userName} and phoneNo = #{phoneNo}")
	void updatepassword(MemberDto member);
	
	
}
