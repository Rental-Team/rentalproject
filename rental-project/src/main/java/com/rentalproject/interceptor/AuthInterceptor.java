package com.rentalproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rentalproject.dto.MemberDto;

// interceptor 클래스는 HandlerInterceptor 인터페이스를 구현해야함
public class AuthInterceptor implements HandlerInterceptor { // implements HandlerInterceptor 인터셉터 구현

	// controller 실행 전 호출
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// System.out.println("preHandle");
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("loginuser");
		
		// 컨트롤러 호출 여부 결정 가능 (반환 값이 true면 호출, 반환 값이 false면 호출 생략)
		if (member == null) { // 세션에 로그인유저가 없다면 = 로그인 하지 않은 사용자라면 
			response.sendRedirect("/rental-project/account/login"); //로그인 화면으로 이동
			return false;
		} else {
			return true;
		}
	}
	
	// controller 실행 후 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// System.out.println("postHandle");
	}
	
	// 요청 처리가 모두 끝난 후(view까지 다 처리가 끝난 후)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// System.out.println("afterCompletrion");
	}
}
