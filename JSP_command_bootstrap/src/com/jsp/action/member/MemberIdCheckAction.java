package com.jsp.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberIdCheckAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = null;
		String resultStr = "";
		
		String id = req.getParameter("id"); 
		
		try {
			MemberVO member = memberService.getMember(id);
			if (member != null) {
				resultStr = "DUPLICATED";
			}
			
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(resultStr);
			out.close();
			
		} catch (Exception e) {
			resp.sendError(resp.SC_INTERNAL_SERVER_ERROR);
		}
		
		return url;
	}
	
}
