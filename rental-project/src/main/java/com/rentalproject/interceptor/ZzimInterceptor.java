package com.rentalproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.rentalproject.dto.MemberDto;

public class ZzimInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
				HttpSession session = request.getSession();
				
				MemberDto member = (MemberDto)session.getAttribute("loginuser");

				// controller 호출 여부 결정 가능 ( 반환 값이 true : 호출, false : 호출 생략 )
				if (member == null) { // 로그인 하지 않은 경우 
					response.sendRedirect("/rental-project/account/login");
					return false;
				} else {
					return true;
				}
	}

	
	
}
