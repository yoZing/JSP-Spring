package com.jsp.action.reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

public class ReplyRegistAction implements Action {

	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		ReplyVO reply = mapper.readValue(req.getReader(), ReplyVO.class);
		
		// XSS
		reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));
		
		// DB
		replyService.registReply(reply);
		
		// realEndPage
		int realEndPage = 1;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(new Criteria());
		pageMaker.setTotalCount(replyService.getReplyListCount(reply.getBno()));
		
		realEndPage = pageMaker.getRealEndPage();
		
		PrintWriter out = resp.getWriter();
		out.print(realEndPage);
		
		return url;
	}
}
