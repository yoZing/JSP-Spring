package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPViewResolver {
	
	public static void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = (String) req.getAttribute("viewName");
		
		if (url.indexOf("redirect:") > -1) {
			
			String contextPath = req.getContextPath();
			
			url = contextPath + "/" + url.replace("redirect:", "");
			
			resp.sendRedirect(url);
		} else {
			String prefix = "/WEB-INF/views/";
			String subfix = ".jsp";
			url = prefix + url + subfix;
			req.getRequestDispatcher(url).forward(req, resp);
		}
	}

}
