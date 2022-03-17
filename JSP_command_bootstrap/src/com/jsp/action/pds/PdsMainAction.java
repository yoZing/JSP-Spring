package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class PdsMainAction implements Action {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "pds/main";
		return url;
	}
	
}
