package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeDetailAction implements Action {

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "notice/detail";
		
		int nno = Integer.parseInt(req.getParameter("nno"));
		String from = req.getParameter("from");
		
		NoticeVO notice = null;
		
		if (from != null && from.equals("list")) {
			notice = noticeService.getNotice(nno);
		} else {
			notice = noticeService.getNoticeForModify(nno);
		}
		
		req.setAttribute("notice", notice);
		
		return url;
	}
	
}
