package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;

public class LogoutAction implements Action {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String url = "redirect:"; 
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		return url;
	}
	
}