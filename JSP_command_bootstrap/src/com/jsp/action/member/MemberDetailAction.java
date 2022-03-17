package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberDetailAction implements Action {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "member/detail";
		
		String id = req.getParameter("id");
		String from = req.getParameter("from");
		
		try {
			MemberVO member = memberService.getMember(id);

			if (from != null && from.equals("list")) {
				req.setAttribute("from", true);
			}
			
			req.setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return url;
	}
}