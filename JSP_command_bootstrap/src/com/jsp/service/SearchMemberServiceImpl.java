package com.jsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dto.MemberVO;

public class SearchMemberServiceImpl extends MemberServiceImpl
									 implements	SearchMemberService {

	@Override
	public Map<String, Object> getSearchMemberList(Criteria cri) throws Exception {
		Map<String, Object> dataMap = null;
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<MemberVO> memberList = memberDAO.selectMemberList(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(memberDAO.selectMemberListCount(session, cri));
			
			dataMap = new HashMap<String, Object>();
			dataMap.put("memberList", memberList);
			dataMap.put("pageMaker", pageMaker);
			
		} finally {
			session.close();
		}
		
		return dataMap;
	}

}
