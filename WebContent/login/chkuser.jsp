<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="members.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="check" class="members.MemberDAO" />
	<jsp:useBean id="dto" class="members.MemberDTO" />
	<jsp:setProperty property="*" name="dto" />
	<c:set var="result" value="${check.userCheck(dto.id, dto.getPwd()) }" />
	<c:choose>
		<c:when test="${result == 0}">
			<c:set var="userId" value="${dto.id }" scope="session" />
			<c:redirect url="successLogin.jsp" />
		</c:when>
		<c:when test="${result eq 1}">
			<script>alert("비밀번호가 일치하지 않습니다.."); history.back();</script>
		</c:when>
		<c:otherwise>
			<script>alert("등록되지 않은 아이디 입니다.."); history.back();</script>
		</c:otherwise>
	</c:choose>
	<%--
	int result = check.userCheck(dto.getId(),dto.getPwd());
	if(result == 0 ){
		session.setAttribute("userId", dto.getId());
		response.sendRedirect("successLogin.jsp");
	}else if(result == 1){
		out.print("<script>alert('비밀번호가 일치하지 않습니다..');" 
				+ "history.back();</script>");
	}else{
		out.print("<script>alert('등록되지 않은 아이디 입니다.');" 
				+ "history.back();</script>");
	}
	--%>
</body>
</html>