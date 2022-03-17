<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	body {
		background: <% out.println(request.getAttribute("color")); %>;
	}
</style>

</head>
<body>

</body>
</html>