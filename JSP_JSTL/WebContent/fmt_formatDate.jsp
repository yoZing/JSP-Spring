<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
	Date today = new Date();
	out.print("toString(): " + today.toString());
	out.print("<br>");
	out.print("getTime(): " + today.getTime());

	out.print("<br>");
	String todayStr = new SimpleDateFormat("yyyy/MM/dd").format(today);
	out.print("simpleDateFormat: " + todayStr);
%>

<br>

<c:set var="today" value="<%=new Date() %>" />
JSTL fmt: <fmt:formatDate value="${today }" pattern="yyyy/MM/dd" />
