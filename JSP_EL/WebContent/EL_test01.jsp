<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<% String str = null; %>
<% pageContext.setAttribute("str", str); %>

null
<h1>표현식 : <%=str %></h1>
<h1>EL : ${str }</h1>

산술연산자
<h1>1 + 2 = ${1 + 2 }</h1>

문자열 연산 X
<% pageContext.setAttribute("str", "알아서"); %>
<h1><%-- ${str + '해요' } --%></h1>
<h1>${str }해요</h1>