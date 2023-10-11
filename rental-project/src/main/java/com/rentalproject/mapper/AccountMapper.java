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
	@Insert("insert into Member (memberId, password, passwordConfirm, " +
			"userName, nickname, phoneNo, email, address, addressDetail, deposite)" +
			"values (#{memberId}, #{password}, #{passwordConfirm}, #{userName}, " +
			"#{nickname}, #{phoneNo}, #{email}, #{address}, #{addressDetail}, #{deposite})")
	void insertMember(MemberDto member);
	
	// 카카오 회원 정보 입력
	@Insert("insert into Member (memberId) values (#{memberId})")
	void insertKakaoMember(MemberDto member);
	
	// 아이디 중복 검사
	@Select("select count(*) from Member where memberId = #{memberId}")
	int checkId(@Param("memberId") String memberId);
	
	// 로그인 = 프로필에 조회할 내용과 일치해서 따로 ProfileMapper에 만들지 않음
	@Select("select * from Member where memberId = #{memberId} and password = #{password}")
	MemberDto selectMemberByIdAndPw(MemberDto member);
	
	// 아이디 찾기
	@Select("select * from Member Where userName = #{userName} and phoneNo = #{phoneNo}")
	MemberDto findIdByNameAndPhoneNo(MemberDto member);
	
	// 비밀번호 찾기
	@Select("select * from Member where memberId = #{memberId} and email = #{email}")
	MemberDto findePwByIdAndNameAndPhoneNo(MemberDto member); 
	
	// 임시 비밀번호로
	@Update("update Member set password = #{password}" +
			"where memberId = #{memberId} and email = #{email}")
	void newPassword(MemberDto member);
	
	// 비밀번호 변경
	@Update("update Member set password = #{password}, passwordConfirm =#{passwordConfirm} " + 
			"where memberId = #{memberId} ")
	MemberDto selfupdatepassword(MemberDto member);
	
	
}
