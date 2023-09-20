package com.rentalproject.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDto {

	private int memberNo;
	private String memberId;
	private String password;
	private String userName;
	private String nickname;
	private String phoneNo;
	private String email;
	private String address;
	private int deposite;
	private Date regDate;
	private boolean deleteCheck;
}
