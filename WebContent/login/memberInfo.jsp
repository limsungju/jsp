<%@page import="java.util.ArrayList"%>
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
<div align="center" >
<h1>회원 목록</h1>

<jsp:useBean id="mb" class="members.MemberDAO"/>
<c:set var="memberList" value="${mb.memberView() }"/>
<c:choose>
	<c:when test="${memberList == '' || memberList == null }">
		<h1>등록된 값이 없습니다.</h1>
	</c:when>
	<c:otherwise>
		<table border='1'>
		<tr>
		<th width="100">이름</th><th width="100">주소</th><th width="100">전화번호</th>
		</tr>
		<c:forEach var="dto" items="${memberList }">
		<tr>
		<td style="cursor:pointer"
		onclick="location.href='/jsp02/login/userInfo.jsp?id=${dto.id}'">
		${dto.name }</td><td>${dto.addr }</td><td>${dto.tel }</td>
		</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

<%
ArrayList<MemberDTO> memberList = mb.memberView();
if(memberList == null || memberList.size() == 0 ){
	out.print("<h1>등록된 값이 없습니다.</h1>");
}else{%>
	<table border='1'>
	<tr>
	<th width="100">이름</th><th width="100">주소</th><th width="100">전화번호</th>
	</tr>
	<%	for(MemberDTO dto:memberList){	%>
	<tr>
	
	<td style="cursor:pointer"
	onclick="location.href='userInfo.jsp?id=<%=dto.getId()%>'">
	<%=dto.getName()%>
	</td>
	
	<td><%=dto.getAddr()%></td><td><%=dto.getTel()%></td>
	</tr>
	<%	}out.print("</table>"); } %>
</div>
<c:import url="/default/footer.jsp"/>
<!-- <jsp:include page="/default/footer.jsp"/> -->

</body>
</html>
