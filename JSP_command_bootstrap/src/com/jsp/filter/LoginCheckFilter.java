package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;

public class LoginCheckFilter implements Filter {

	private List<String> exURLs = new ArrayList<String>();
	
	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		
		// 제외할 url 확인
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
		if (excludeCheck(reqUrl)) {
			chain.doFilter(req, resp);
			return;
		}
		
		// login check
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// login 확인
		if (loginUser == null) { // 비로그인 상태
			httpReq.getRequestDispatcher("/WEB-INF/views/common/loginCheck.jsp").forward(httpReq, httpResp);
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		String excludeURLNames = fConfig.getInitParameter("exclude");
		
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while (st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim());
		}
		
	}
	
	private boolean excludeCheck(String url) {
		
		boolean result = false;
		
		result = result || url.length() <= 1;
		
		for (String exURL : exURLs) {
			result = result || url.contains(exURL);
		}
		return result;
		
	}
}
