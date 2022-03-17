package com.jsp.action.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.command.CriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.controller.JSONViewResolver;
import com.jsp.service.ReplyService;

public class ReplyListAction implements Action {

	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = null;
		
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		CriteriaCommand criCMD = (CriteriaCommand) HttpRequestParameterAdapter.execute(req, CriteriaCommand.class);
		Criteria cri = criCMD.toCriteria();
		
		Map<String, Object> dataMap = replyService.getReplyList(bno, cri);
		
		JSONViewResolver.view(resp, dataMap);
		
		
		return url;
	}
}
