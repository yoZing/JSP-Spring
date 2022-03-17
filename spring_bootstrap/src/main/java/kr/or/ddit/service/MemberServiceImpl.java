package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dto.MemberVO;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.service.LoginSearchMemberService;

import kr.or.ddit.dao.MemberDAOBean;

public class MemberServiceImpl implements LoginSearchMemberService {

	private MemberDAOBean memberDAOBean;
	public void setMemberDAOBean(MemberDAOBean memberDAOBeanBean) {
		this.memberDAOBean = memberDAOBeanBean;
	}

	@Override
	public MemberVO getMember(String id) throws Exception {

		MemberVO member = memberDAOBean.selectMemberById(id);
		return member;
	}

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		List<MemberVO> memberList = memberDAOBean.selectMemberList();
		return memberList;
	}

	@Override
	public Map<String, Object> getSearchMemberList(Criteria cri) throws Exception {

		Criteria searchCri = (Criteria) cri;
		Map<String, Object> dataMap = new HashMap<String, Object>();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(memberDAOBean.selectMemberListCount(searchCri));

		List<MemberVO> memberList = memberDAOBean.selectMemberList(searchCri);

		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void regist(MemberVO member) throws Exception {

		memberDAOBean.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		memberDAOBean.updateMember(member);
	}

	@Override
	public void remove(String id) throws Exception {

		memberDAOBean.deleteMember(id);

	}

	@Override
	public void enabled(String id, int state) throws Exception {

		memberDAOBean.enabledMember(id, state);
	}

	public void login(String id, String pwd) throws IdNotFoundException, InvalidPasswordException, SQLException {
		MemberVO member = memberDAOBean.selectMemberById(id);
		if (member == null)
			throw new IdNotFoundException();
		if (!pwd.equals(member.getPwd()))
			throw new InvalidPasswordException();
	}

	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		List<MemberVO> memberList = memberDAOBean.selectMemberList(cri);
		return memberList;
	}

}
