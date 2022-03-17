package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class DispatcherServlet extends HttpServlet {
	
	private HandlerMapper handlerMapper;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String handlerProperty = config.getInitParameter("urlProperty");
		try {
			if (handlerProperty != null && !handlerProperty.isEmpty()) {
				handlerMapper = new HandlerMapper(handlerProperty); 
			} else {
				handlerMapper = new HandlerMapper();
			}
			System.out.println("[DispatcherServlet] handlerMapper 가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[DisparcherServlet] handlerMapper 가 준비되지 않았습니다.");
		}
//		handlerMapper=null;
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestPro(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestPro(req, resp);
	}
	
	private void requestPro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 사용한 URI 검출
		String command = req.getRequestURI(); // contextPath 포함.
		if (command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		
		// commandHandler 실행 (HandlerMapper 의뢰 handler 할당)
		Action action = null;
		String message = null;
		
		if (handlerMapper != null) {
			action = handlerMapper.getAction(command);
			if (action != null) { // 올바른 요청
				message = action.execute(req, resp);
				System.out.println("message : " + message);
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}
}
