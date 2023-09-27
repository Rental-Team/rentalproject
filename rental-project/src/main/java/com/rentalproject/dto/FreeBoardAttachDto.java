package com.rentalproject.dto;

import lombok.Data;

@Data
public class FreeBoardAttachDto {
	
	private int attachNo;
	private int freeBoardNo;
	private String attachFileName; // 사용자가 업로드한 원래 파일 이름
	private String savedFileName;  // 실제 저장되는 파일 이름( 중복 피하기 위한 고유 파일 이름 )
	

}
