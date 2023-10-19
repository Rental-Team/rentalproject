package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.FreeBoardDto;
import com.rentalproject.dto.MemberDto;

@Mapper
public interface ProfileMapper {
	
	// 프로필 조회
	@Select("select * from Member where memberId = #{memberId}")
	MemberDto selectRegDate(@Param("memberId") String memberId);
	
	//////////////////// 내가 쓴 자유 게시판 조회//////////////////////////////
	@Select("select count(*) from FreeBoard where memberNo = #{memberNo}")
	int selectMyFreeBoardCountByMemberNo(@Param("memberNo") int membrNo);
	
	@Select("select * from FreeBoard where memberNo = #{memberNo} order by freeBoardNo desc limit #{from}, #{count}")
	List<FreeBoardDto> selectMyFreeBoardByMemberNo(@Param("memberNo") int memberNo, @Param("from")int from , @Param("count")int count);
	
	// memberId 조회
	@Select("select memberId from Member where memberNo = (select memberNo from FreeBoard where freeBoardNo = #{freeBoardNo})")
	String getMemberIdByMyFreeBoardNo(@Param("freeBoardNo") int freeBoardNo);
	////////////////////내가 쓴 자유 게시판 조회//////////////////////////////
	
//	@Select("select zz.thumbnail, zz.itemName, zz.itemPrice, zz.itemCount, zz.totalPrice 'zzim' boardType " +
//			"from zzim zz inner join Member m on zz.memberNo = m.memberNo " +
//			"where m.memberNo #{memberNo} " +
//			"order by freeBoardNo desc limit 6")
//	List<FreeBoardDto> selectMyFreeBoardByMemberNo(int memberNo);

	// 프로필 수정
	@Update("update Member " + 
			"set userName = #{userName}, nickname = #{nickname}, phoneNo = #{phoneNo}, email = #{email}, " + 
			"address = #{address}, addressCode = #{addressCode}, addressDetail = #{addressDetail}, " + 
			"deposite = #{deposite}, memberImage = #{memberImage}, introduce = #{introduce} " + 
			"where memberId = #{memberId}")
	void updateProfileWithMemberImage(MemberDto member);
	
	// 프로필 수정
	@Update("update Member " + 
			"set userName = #{userName}, nickname = #{nickname}, phoneNo = #{phoneNo}, email = #{email}, " + 
			"address = #{address}, addressCode = #{addressCode}, addressDetail = #{addressDetail}, " + 
			"deposite = #{deposite}, introduce = #{introduce} " + 
			"where memberId = #{memberId}")
	void updateProfileWithoutMemberImage(MemberDto member);
	
	// 회원 탈퇴
	@Update("update Member set deleteCheck = true where memberId = #{memberId} ")
	void deleteUser(@Param("memberId") String memberId);

}
