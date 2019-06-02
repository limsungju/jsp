<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ul li{	display:inline;	padding:0 10px; }
</style>
</head>
<body>
<% String test="값이 들어갈까"; %>
<div align="center" >
<h1 style="color:burlywood; font-size:60px; font-family:Gabriola;">CARE &nbsp; LAB</h1>
</div>
<div align="right">
<hr><ul>
<li><a href="/beans/member/quiz03/index.jsp">HOME</a></li>
<li>jstl=></li>
<c:choose>
	<c:when test="${userId  eq '' || userId eq null  }">
		<li><a href="/jsp02/login/login.jsp">회원 정보</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="/jsp02/login/memberInfo.jsp">회원 정보</a></li>
	</c:otherwise>
</c:choose>
<li><a href="/jsp02/board/index.jsp">게시판</a></li>
<c:choose>
	<c:when test="${userId  eq '' || userId eq null }">
		<li><a href="/jsp02/login/login.jsp">로그인</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="/jsp02/login/logout.jsp">로그아웃</a></li>
	</c:otherwise>
</c:choose>
<li><a href="/jsp02/board/mypage.jsp">마이페이지</a></li>
<li>jsp 스크립트릿=></li>
<%if(session.getAttribute("userId") == null){ %>
<li><a href="/jsp02/login/login.jsp">회원 정보</a></li>
<%}else {%>
	<li><a href="/jsp02/login/memberInfo.jsp">회원 정보</a></li>
<%} %>

<%if(session.getAttribute("userId") == null){ %>
<li><a href="/jsp02/login/login.jsp">로그인</a></li>
<%}else {%>
<li><a href="/jsp02/login/logout.jsp">로그아웃</a></li>
<%} %>
</ul><hr>
</div>
</body>
</html>