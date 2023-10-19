package com.rentalproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rentalproject.dto.ZzimDto;

@Mapper
public interface ZzimMapper {
	
	// 찜 추가
	@Insert("insert into Zzim(memberNo, itemNo, itemCount) "
			+ "values (#{memberNo}, #{itemNo}, #{itemCount}) ")
	public int addZzim(ZzimDto zzim) throws Exception;
	
	// 찜 삭제 
	@Delete("delete from Zzim "
			+ "where zzimNo = #{zzimNo}")
	public int deleteZzim(ZzimDto zzim);
	
	// 찜 수량 수정
	@Update("update Zzim "
			+ "set itemCount = #{itemCount} "
			+ "where zzimNo = #{zzimNo} ")
	public int modifyCount(ZzimDto zzim);
	
	// 찜 목록
	@Select("select z.zzimNo, (select memberId from Member where memberNo = z.memberNo) memberId, "
			+ "(select Max(iA.savedFileName) from itemAttach iA where iA.itemNo = i.itemNo) thumbnail, "
			+ "z.itemNo, z.itemCount, i.itemName, i.itemPrice "
			+ "from Zzim z left outer join Item i on z.itemNo = i.itemNo "
			+ "where memberNo = #{memberNo} ")
	public List<ZzimDto> getZzim(int memberNo);	
	
	// 찜 확인
	@Select("select * "
			+ "from Zzim "
			+ "where memberNo = #{ memberNo } and itemNo = #{ itemNo } ")
	public ZzimDto checkZzim(ZzimDto zzim);
	
	// 찜 제거 (주문 후)
	@Delete("delete from Zzim "
			+ "where memberNo = #{memberNo} and itemNo = #{itemNo} ")
	int deleteOrderZzim(ZzimDto zzim);
}
