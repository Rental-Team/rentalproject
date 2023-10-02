package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.MemberDto;

@Mapper
public interface AccountMapper {
	
	// 회원가입 정보 입력
	@Insert("INSERT INTO Member (memberId, password, passwordConfirm, " +
			"userName, nickname, phoneNo, email, address, deposite)" +
			"VALUES (#{memberId}, #{password}, #{passwordConfirm}, #{userName}, " +
			"#{nickname}, #{phoneNo}, #{email}, #{address}, #{deposite})")
	void insertMember(MemberDto member);
	
	// 로그인 = 프로필에 조회할 내용과 일치해서 따로 ProfileMapper에 만들지 않음
	@Select("select * from Member where memberId = #{memberId} and password = #{password}")
	MemberDto selectMemberByIdAndPw(MemberDto member);
	
	// 아이디 찾기
	@Select("select * from Member Where userName = #{userName} and phoneNo = #{phoneNo}")
	MemberDto findIdByNameAndPhoneNo(MemberDto member);
	
	// 비밀번호 찾기
	@Select("select * from Member where memberId = #{memberId} and userName = #{userName} and phoneNo = #{phoneNo}")
	MemberDto findePwByIdAndNameAndPhoneNo(MemberDto member); 
	
	// 비밀번호 수정
	@Update("update Member set password = #{password}, passwordConfirm =#{passwordConfirm} " + 
			"where memberId = #{memberId} ")
	MemberDto updatepassword(MemberDto member);
	
	
}
