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
			"userName, nickname, phoneNo, email, address, addressCode, addressDetail, deposite, memberImage)" +
			"values (#{memberId}, #{password}, #{passwordConfirm}, #{userName}, " +
			"#{nickname}, #{phoneNo}, #{email}, #{address}, #{addressCode}, #{addressDetail}, #{deposite}, #{memberImage})")
	void insertMember(MemberDto member);
	
//	// 카카오 회원 정보 입력
//	@Insert("insert into Member (memberId) values (#{memberId})")
//	void insertKakaoMember(MemberDto member);
	
	// 아이디 중복 검사
	@Select("select count(*) from Member where memberId = #{memberId}")
	int checkId(@Param("memberId") String memberId);
	
	// 닉네임 중복 검사
	@Select("select count(*) from Member where nickname = #{nickname}")
	int checkNickname(@Param("nickname") String nickname);
	
	// 로그인 = 프로필에 조회할 내용과 일치해서 따로 ProfileMapper에 만들지 않음
	@Select("select * from Member where memberId = #{memberId} and password = #{password}")
	MemberDto selectMemberByIdAndPw(MemberDto member);
	
	// 카카오 로그인 
	@Select("select * from Member where memberId = #{memberId}")
	MemberDto selectKakaoMember(MemberDto member);
	
	// 아이디 찾기
	@Select("select * from Member Where userName = #{userName} and phoneNo = #{phoneNo}")
	MemberDto findIdByNameAndPhoneNo(@Param("userName") String userName, @Param("phoneNo") String phoneNo);
	
	// 비밀번호 찾기
	@Select("select * from Member where memberId = #{memberId} and email = #{email}")
	MemberDto findPwByIdAndEmail(@Param("memberId") String memberId, @Param("email") String email ); 
	
	// 임시 비밀번호 발급 및 변경
	@Update("update Member set password = #{password} where memberId = #{memberId}")
	void updatePassword(@Param("memberId") String memberId, @Param("password") String password);
	
	// 자체 비밀번호 변경
	@Update("update Member set password = #{password}, passwordConfirm =#{passwordConfirm} " + 
			"where memberId = #{memberId} ")
	MemberDto selfUpdatePassword(MemberDto member);
	
	
	// 대여 주문 주소 정보
	@Select("select memberId, memberNo, userName ,email, address, addressDetail "
			+ "from Member "
			+ "where memberNo = #{memberNo} ")
	MemberDto getMemberInfo(int memberNo);
	
}
