<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%-- session.invalidate(); --%>
<c:remove var="userId" scope="session"/>
<script>
alert("로그 아웃 되었습니다.");
location.href="login.jsp";
</script>
</body>
</html>