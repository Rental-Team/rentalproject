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
	private String tempPassword;

	@Setter
	private AccountMapper accountMapper;
	
	
	// 회원가입
	@Override
	public void register(MemberDto member) {
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		Util.getHashedString(member.getPasswordConfirm(), "SHA-256");
		member.setPasswordConfirm(hashedPasswd);
		
		accountMapper.insertMember(member);
	}
	
	// 아이디 중복 검사
	@Override
	public boolean checkRegisterId(String memberId) {
		int count = accountMapper.checkId(memberId);
		boolean isZero = (count == 0);
		return isZero; // 0을 반환한다는게 아니라 count가 0인지 아닌지 true, false 를 반환하는 것
	}
	
	// 닉네임 중복 검사
	@Override
	public boolean checkRegisterNickname(String nickname) {
		int count = accountMapper.checkNickname(nickname);
		
		return count == 0;
	}
	
	
	// 인증번호 난수 발생
	public void makeRandomNumber() {
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("인증번호: " + checkNum);
		authNumber = checkNum;
	}
	
	// 회원가입시 이메일 인증 내용
	@Override
	public String emailContent(String email) {
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
		emailSend(setFrom, toMail, title, content);
		return Integer.toString(authNumber); //int를 String으로
	}
	
	// 이메일 전송 메소드
	public void emailSend(String setFrom, String toMail, String title, String content) {
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
		
//	// 카카오 회원 정보 입력
//	@Override
//	public void insertKakao(MemberDto member) {
//		
//		accountMapper.insertKakaoMember(member);
//		
//	}
	
	
	@Override // 로그인
	public MemberDto findLoginMember(MemberDto member) {
		
		String hashedPasswd = Util.getHashedString(member.getPassword(), "SHA-256");
		member.setPassword(hashedPasswd);
		
		MemberDto loginMember = accountMapper.selectMemberByIdAndPw(member);

		return loginMember;
	}
	
	@Override
	public MemberDto findKakaoMember(MemberDto member) {
		MemberDto kakaoMember = accountMapper.selectKakaoMember(member);
	
		return kakaoMember;
	}

	@Override // 아이디 찾기
	public MemberDto findLoginId(String userName, String phoneNo) {
		MemberDto userId = accountMapper.findIdByNameAndPhoneNo(userName, phoneNo);
		return userId;
	}
	
	@Override // 비밀번호 찾기
	public MemberDto findLoginPw(String memberId, String email) {
		MemberDto userPw = accountMapper.findPwByIdAndEmail(memberId, email);
		return userPw;
	}
	
	@Override // 임시 비밀번호로 수정하기 위함
	public void updateLoginPw(String memberId, String password) {
        accountMapper.updatePassword(memberId, password);
    }
	
	// 임시 비밀번호 난수 발생
	public void makeTemporaryPw() {
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	StringBuilder randomString = new StringBuilder();
	
	Random random = new Random();
    for (int i = 0; i < 6; i++) { // 6자리 랜덤 문자열 생성
        int index = random.nextInt(characters.length());
        randomString.append(characters.charAt(index));
    }
    tempPassword = randomString.toString();
    System.out.println("임시 비밀번호: " + tempPassword);
    
}

	// 비밀번호 찾을 시 이메일 임시 비밀번호 
	@Override
	public String emailContentForTemporaryPw(String email) {
		makeTemporaryPw();
		String setFrom = "rlrkxks35@naver.com";
		String toMail = email;
		String title = "임시 비밀번호 입니다.";
		String content =
			    "임시 비밀번호는 " + tempPassword + "입니다." + 
			    "<br>" + 
			    "해당 비밀번호로 다시 로그인해주세요.";
		emailSend(setFrom, toMail, title, content);
		String hashedPassword = Util.getHashedString(tempPassword, "SHA-256");
		return hashedPassword;
		
	}
	
	@Override // 자체 비밀번호 수정
	public MemberDto selfUpdatePw(MemberDto member) {
		MemberDto userNewPassword = accountMapper.selfUpdatePassword(member);
		return userNewPassword;
	}

	
	@Override
	public MemberDto getMemberInfo(int memberNo) {
		
		return accountMapper.getMemberInfo(memberNo);
	}

	
}
