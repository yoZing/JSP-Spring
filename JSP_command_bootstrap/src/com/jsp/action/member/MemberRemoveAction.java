package com.jsp.action.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.GetUploadPath;

public class MemberRemoveAction implements Action {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = "member/remove_success";
		
		String id = req.getParameter("id");
		
		MemberVO member = memberService.getMember(id);
		
		// 사진이미지 삭제
		String picture = member.getPicture();
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		File deletePictureFile = new File(savedPath, picture);
		if (deletePictureFile.exists()) deletePictureFile.delete();
		
		// DB 삭제
		memberService.remove(id);
		
		// loginUser 확인
		HttpSession session = req.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null && member.getId().equals(loginUser.getId()) ) {
			session.invalidate();
		}
		
		req.setAttribute("member", member);
		
		return url;
	}
}
