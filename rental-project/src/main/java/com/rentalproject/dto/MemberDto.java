package com.rentalproject.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberDto {

	private int memberNo;
	
	private String memberId;
	
	private String password;
	
	private String passwordConfirm;
	
	private String userName;

	private String nickname;
	
	private String phoneNo;
	
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	
	private String address;
	private String addressCode;
	private String addressDetail;
	
	private int deposite;
	private Date regDate;
	private boolean deleteCheck;
	private String introduce;
	private String memberImage;
	private int admin;
	private int kakao;
	
	// 한 회원은 여러개의 주문이 가능하다.
	List<RentalOrderPageDto> rentalOrderList;
	
}
