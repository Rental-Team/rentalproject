package com.rentalproject.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberDto {

	private int memberNo;
	
	@NotBlank(message = "아이디를 입력해주세요") 
	private String memberId;
	
	@NotBlank(message = "비밀번호를 입력해주세요") 
	private String password;
	private String passwordConfirm;
	
	@NotBlank(message = "이름을 입력해주세요")
	private String userName;
	
	@NotBlank(message = "별명을 입력해주세요")
	private String nickname;
	
	@NotBlank(message = "전화번호를 입력해주세요")
	private String phoneNo;
	
	@NotBlank(message = "이메일을 입력해주세요")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	
	@NotBlank(message = "주소를 입력해주세요")
	private String address;
	
	private String addressDetail;
	
	private int deposite;
	private Date regDate;
	private boolean deleteCheck;
	private String introduce;
	private String imageName;
	private int admin;
}
