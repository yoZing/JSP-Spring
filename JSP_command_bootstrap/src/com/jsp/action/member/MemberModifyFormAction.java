package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.MakeFileName;

public class MemberModifyFormAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "member/modifyForm";
		
		try {
			String id = req.getParameter("id");
			MemberVO member = memberService.getMember(id);
			
			String originalFileName = MakeFileName.parseFileNameFromUUID(member.getPicture(), "\\$\\$");
			
			member.setPicture(originalFileName);
			
			req.setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}
}
