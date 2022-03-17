package com.jsp.action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberListAction implements Action {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "member/list";
		
		String pageParam = req.getParameter("page");
		String perPageNumParam = req.getParameter("perPageNum");
		String searchTypeParam = req.getParameter("searchType");
		String keywordParam = req.getParameter("keyword");
		
		Criteria cri = new Criteria();
		cri.setSearchType(searchTypeParam);
		cri.setKeyword(keywordParam);
		
		try {
			if (pageParam != null && !pageParam.isEmpty()) cri.setPage(Integer.parseInt(pageParam));
			if (perPageNumParam != null && !perPageNumParam.isEmpty()) cri.setPerPageNum(Integer.parseInt(perPageNumParam));
		} catch (Exception e) {
			resp.sendError(Response.SC_BAD_REQUEST);
			return null;
		}
		
		req.setAttribute("cri", cri);
		
		try {

			List<MemberVO> memberList = memberService.getMemberList(cri);
			req.setAttribute("memberList", memberList);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}
}
