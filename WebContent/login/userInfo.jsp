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
	<!-- <jsp:useBean id="dto" class="members.MemberDTO"/> -->
	<div align="center" style="height: 300px;">
	<h1>개인 정보</h1>
	<%--
	dto = mb.memberView(request.getParameter("id"));
	--%>
	<c:set var="dto" value="${mb.memberView(param.id) }"/>
		<table>
		<tr><th width="100">아 이 디</th><td>${dto.id }</td></tr>
		<tr><th width="100">비밀번호</th><td>${dto.pwd }</td></tr>
		<tr><th width="100">이 름</th><td>${dto.name }</td></tr>
		<tr><th width="100">주 소</th><td>${dto.addr }</td></tr>
		<tr><th width="100">전화번호</th><td>${dto.getTel() }</td></tr>
		<tr>
		<td align="center"><input type="button" value="수 정" 
		onclick='location.href="modifyInfo.jsp?id=${dto.id }"'></td>
		<td><input type="button" value="삭 제" 
		onclick='location.href="delete.jsp?id=${dto.id }"'></td>
		</tr>
		</table>
	</div>
	<c:import url="/default/footer.jsp"/>
	<!-- <jsp:include page="/default/footer.jsp"/> -->
</body>
</html>