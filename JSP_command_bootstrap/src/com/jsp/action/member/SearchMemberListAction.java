package com.jsp.action.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.service.SearchMemberService;

public class SearchMemberListAction implements Action {

	private SearchMemberService searchMemberService;
	public void setSearchMemberService(SearchMemberService searchMemberService) {
		this.searchMemberService = searchMemberService;
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

			Map<String, Object> dataMap = searchMemberService.getSearchMemberList(cri);
			req.setAttribute("dataMap", dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}
