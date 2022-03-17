package com.jsp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

public class UpdateMemberServlet extends HttpServlet {
	
	private MemberService memberService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		String memberServiceStr = config.getInitParameter("memberService");
		String memberDAOStr = config.getInitParameter("memberDAO");
		String sqlSessionFactoryStr = config.getInitParameter("sqlSessionFactory");
		
		try {
			memberService = (MemberService) Class.forName(memberServiceStr).newInstance();
			MemberDAO memberDAO = (MemberDAO) Class.forName(memberDAOStr).newInstance();
			SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) Class.forName(sqlSessionFactoryStr).newInstance();

			if (memberService instanceof MemberServiceImpl) {
				MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberService;
				memberServiceImpl.setSqlSessionFactory(sqlSessionFactory);
				memberServiceImpl.setMemberDAO(memberDAO);
				
				System.out.println("memberService injection clear!");
			}
		} catch (Exception e) {
			System.out.println("초기화 실패입니다.");
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		String url = "";
		
		MemberVO member = new MemberVO();
		try {
			member = memberService.getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		req.setAttribute("member", member);
		
		url ="/WEB-INF/views/member/updateForm.jsp";
				 
		req.getRequestDispatcher(url).forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		MemberVO mv = new MemberVO();
		mv.setId(id);
		mv.setName(name);
		mv.setPwd(pwd);
		mv.setAddress(address);
		mv.setEmail(email);
		mv.setPhone(phone);
		
		String url = "";
		try {
			memberService.modify(mv);
			url = req.getContextPath() + "/member/detail?id=" + id;
		} catch (SQLException e) {
			url = "/WEB-INF/views/member/fail.jsp";
			e.printStackTrace();
		}
		
		
//		String redirectUrl = req.getContextPath() + url;
		resp.sendRedirect(url); // 목록조회 화면으로 리다이렉트
		
	}
}
