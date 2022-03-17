package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeRegistAction implements Action {

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "notice/regist_success";
		
		NoticeVO notice = (NoticeVO) XSSHttpRequestParameterAdapter.execute(req, NoticeVO.class, true);
		
		notice.setContent(req.getParameter("content"));
		
		noticeService.regist(notice);
		
		return url;
	}
}
