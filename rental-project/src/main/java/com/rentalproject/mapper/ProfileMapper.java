package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.MemberDto;

@Mapper
public interface ProfileMapper {
	
//	// 프로필 조회 -> AccountMapper의 로그인과 공유
//		@Select("select memberNo, memberId, userName, nickname," + 
//				" phoneNo, email, address, deposite, regDate " + 
//				"from Member where memberId = #{memberId})")
//		MemberDto selectProfileByMemberId(@Param("memberId") String MemberId);
		
	// 프로필 수정
	@Update("update Member " + 
			"set userName = #{userName}, nickname = #{nickname}, phoneNo = #{phoneNo}, email = #{email}, " + 
			"address = #{address}, deposite = #{deposite}, introduce = #{introduce}, photo = #{photo} " + 
			"where memberId = #{memberId}")
	void updateProfile(MemberDto member);
		
	// 비밀번호 수정
	@Update("update Member set password = #{password} where memberId = #{memberId})")
	void updatepassword(@Param("password") String password);
	
	// 회원 탈퇴
	@Update("update Member set deleteCheck = true where memberId = #{memberId} ")
	void deleteUser(@Param("memberId") String memberId);

}
