package com.rentalproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rentalproject.dto.MemberDto;

// interceptor 클래스는 HandlerInterceptor 인터페이스를 구현해야 합니다.
public class AdminInterceptor implements HandlerInterceptor {
	
	// controller 실행 전 호출
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		//System.out.println("preHandle");
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("loginuser");
		// controller 호출 여부 결정 가능 ( 반환 값이 true : 호출, false : 호출 생략 )
		
		if (member == null) { // 로그인 하지 않은 경우 
			response.sendRedirect("/rental-project/account/login");
			return false;
		}
			
		if(member.getAdmin() != 2) {
			response.sendRedirect("/rental-project/account/login");
			return false;
		}
			
		return true;
	}
	
	// controller 실행 후 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//System.out.println("postHandle");
	}
	
	// 요청처리가 모두 끝난 후 호출
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("afterCompletion");
	}

}
