package com.rentalproject.dto;

import java.util.Date;

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
	private String email;
	private String address;
	private int deposite;
	private Date regDate;
	private boolean deleteCheck;
	private String introduce;
	private String imageName;
	private int admin;
}
