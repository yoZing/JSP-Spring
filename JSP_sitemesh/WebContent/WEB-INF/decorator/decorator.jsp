<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> <decorator:title default="kim" /> </title>

<decorator:head />
</head>
<body>

<%@ include file="/header.jsp" %>

	decorator. jsp.jsp <br/>
	header.jsp : message : ${message }

<hr/>

<decorator:body />

<%@ include file="/footer.jsp" %>

</body>
</html>