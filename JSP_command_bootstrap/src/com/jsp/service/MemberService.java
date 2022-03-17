package com.jsp.service;

import java.util.List;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberService {

	public List<MemberVO> getMemberList() throws Exception;
	public List<MemberVO> getMemberList(Criteria cri) throws Exception;
	
	public MemberVO getMember(String id) throws Exception;
	
	// 회원등록
	public void regist(MemberVO member) throws Exception;
	
	// 회원수정
	void modify(MemberVO member) throws Exception;
	
	// 회원삭제
	void remove(String id) throws Exception;
	
	// 회원 상태변경
	void enabled(String id, int enabled)throws Exception;
	
}
	