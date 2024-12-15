package com.matching.plaform.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginMag");
		
		if(request.getSession().getAttribute("isLogin") == null) {
			response.sendRedirect("loginView");
			session.setAttribute("loginMsg", "로그인이 필요한 서비스");
			return false;
			}
			return true;
		
	}
	
}
