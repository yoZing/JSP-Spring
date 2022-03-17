package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.NoticeService;

public class NoticeRemoveAction implements Action {

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "notice/remove_success";
		
		int nno = Integer.parseInt(req.getParameter("nno"));
		
		noticeService.remove(nno);
		
		return url;
	}
}
