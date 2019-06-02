<%@page import="members.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <jsp:include page="/default/header.jsp"/> -->
<c:import url="/default/header.jsp"/>
<jsp:useBean id="mb" class="members.MemberDAO"/>
<%--
MemberDTO dto =  mb.memberView(request.getParameter("id"));
--%>
<c:set var="dto" value="${mb.memberView(param.id) }"/>
<div align="center">
<form action="changeUser.jsp" method='post'>
<input type="hidden" name="id" value="${dto.getId() }"><br>
<input type="hidden" name="pwd" value="${dto.pwd }"><br>
이 름 <input type="text" name="name" value="${dto.name }"><br>
주 소 <input type="text" name="addr" value="${dto.addr }"><br>
전화번호 <input type="text" name="tel" value="${dto.tel }"><br>
<input type="submit" value="완료">
<input type="reset" value="취소" onclick="javascript:history.back();">
</form>
</div>
<c:import url="/default/footer.jsp"/>
<!-- <jsp:include page="/default/footer.jsp"/> -->
</body>
</html>