package com.rentalproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.MemberDto;

@Mapper
public interface ProfileMapper {
		
	// 프로필 수정
	@Update("update Member " + 
			"set userName = #{userName}, nickname = #{nickname}, phoneNo = #{phoneNo}, email = #{email}, " + 
			"address = #{address}, deposite = #{deposite}, imageName = #{imageName}, introduce = #{introduce} " + 
			"where memberId = #{memberId}")
	void updateProfile(MemberDto member);
	
	// 회원 탈퇴
	@Update("update Member set deleteCheck = true where memberId = #{memberId} ")
	void deleteUser(@Param("memberId") String memberId);

}
