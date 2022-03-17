package com.jsp.service;

import java.util.Map;

import com.jsp.command.Criteria;

public interface SearchMemberService extends MemberService {

	Map<String, Object> getSearchMemberList(Criteria cri) throws Exception;
	
}
