package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class HomeAction implements Action {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "home";
		
		return url;
	}
}
