package com.rentalproject.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.rentalproject.common.Util;
import com.rentalproject.dto.MemberDto;
import com.rentalproject.mapper.AccountMapper;

import lombok.Setter;


public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private JavaMailSender mailSender;
	private int authNumber;

	@Setter
	private AccountMapper accountMapper;
	
	@Override // 회원가입
	public void register(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		Util.getHashedString(member.getPasswordConfirm(), "SHA-256");
		member.setPasswordConfirm(hashedPasswd);
		
		accountMapper.insertMember(member);
		
	}
	
	// 인증번호 난수 발생
	public void makeRandomNumber() {
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("인증번호: " + checkNum);
		authNumber = checkNum;
	}
	
	// 이메일 보낼 양식
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "rlrkxks35@naver.com"; // email
		String toMail = email;
		String title = "회원 가입 인증 이메일입니다.";
		String content =
				"홈페이지를 방문해주셔서 감사합니다." +
				"<br><br>" + 
			    "인증 번호는 " + authNumber + "입니다." + 
			    "<br>" + 
			    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNumber);
	}
	
	// 이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message  = mailSender.createMimeMessage();
		// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public boolean checkRegisterId(String memberId) {
		
		int count = accountMapper.checkId(memberId);
		
		return count == 0;
	}
	
	@Override // 로그인
	public MemberDto findLoginMember(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		MemberDto loginMember = accountMapper.selectMemberByIdAndPw(member);

		return loginMember;
	}
	
	@Override // 아이디 찾기
	public MemberDto findLoginId(MemberDto member) {
		MemberDto userId = accountMapper.findIdByNameAndPhoneNo(member);
		return userId;
	}
	
	@Override // 비밀번호 찾기
	public MemberDto findLoginPw(MemberDto member) {
		MemberDto userPassword = accountMapper.findePwByIdAndNameAndPhoneNo(member);
		return userPassword;
	}
	
	@Override // 새 비밀번호 임시 발급 수정
	public void newPw(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		Util.getHashedString(member.getPasswordConfirm(), "SHA-256");
		member.setPasswordConfirm(hashedPasswd);
		
		accountMapper.newPassword(member);
	}
	
	@Override // 자체 비밀번호 수정
	public MemberDto selfupdatePw(MemberDto member) {
		MemberDto userNewPassword = accountMapper.selfupdatepassword(member);
		return userNewPassword;
	}
	
}
