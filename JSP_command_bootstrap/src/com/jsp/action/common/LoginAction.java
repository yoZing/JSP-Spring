package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.service.LoginSearchMemberService;

public class LoginAction implements Action {

	private LoginSearchMemberService loginSearchMemberService;
	public void setLoginSearchMemberService(LoginSearchMemberService loginSearchMemberService) {
		this.loginSearchMemberService = loginSearchMemberService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "redirect:index.do";
		
		// 입력
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		try {
			loginSearchMemberService.login(id, pwd);
			
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", loginSearchMemberService.getMember(id));
			session.setMaxInactiveInterval(6 * 60 * 10);
			
		} catch (IdNotFoundException | InvalidPasswordException e) {
//			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			url = "common/login_fail";
			
		} catch (Exception e) {
			e.printStackTrace();
//			resp.sendError(resp.SC_INTERNAL_SERVER_ERROR);
			// Exception 처리...
			throw e;
		}
		
		return url;
	}

}
