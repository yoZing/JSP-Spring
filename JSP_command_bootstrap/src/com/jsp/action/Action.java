package com.jsp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception; 
	
}
