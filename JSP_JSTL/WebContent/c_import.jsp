<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
	<c:import url="https://www.naver.com" var="naver" />
	<c:import url="/c_remove.jsp" var="remove" />
<body>

	<div style="width:800px; height:400px; overflow:scroll;">
		${naver }
	</div>
	<div style="width:800px; height:400px; overflow:scroll;">
		${remove }
	</div>
	
</body>
</html>