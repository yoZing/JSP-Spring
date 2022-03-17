package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("Get 요청처리");
		
		
		// 입력
		int dan = 2;
		int gopp = 1;
		int target;
		String output = "";
		
//		Scanner scann = new Scanner(System.in);
//		int target = scann.nextInt();
		
		dan = Integer.parseInt(request.getParameter("dan"));
		gopp = Integer.parseInt(request.getParameter("gop"));
		target = Integer.parseInt(request.getParameter("target"));
		
		
		// 처리
		for (; dan < target; dan++) {
			output += dan + "단" + "<br/>";
			
//			int gop = 1;
			for (int gop = 1; gop < gopp; gop++) {
				output += dan + " * " + gop + " = " + (dan * gop) + "<br/>";
			}
			output += "<br/>";
		}
		
		
		// 출력
//		System.out.println(output);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(output);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
