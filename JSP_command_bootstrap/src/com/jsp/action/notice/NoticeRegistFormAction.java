package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class NoticeRegistFormAction implements Action {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "notice/registForm";
		return url;
	}
}
