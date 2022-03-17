<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="str1" value="<h1>안녕하세요.</h1>" />

<c:out value="${str1 }" />

<hr>

<c:out value="${str1 }" escapeXml="false"/>








