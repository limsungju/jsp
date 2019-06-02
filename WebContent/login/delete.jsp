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
<jsp:useBean id="check" class="members.MemberDAO"/>
<h1>삭제되었습니다</h1>
<c:set var="result" value="${check.delete(param.id) }"/>
<c:choose>
	<c:when test="${result == 1 }">
		<c:remove var="userId"/>
		<script>
			alert("삭제 되었습니다");
			location.href="login.jsp";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("문제가 발생 되었습니다.");
			history.back();
		</script>
	</c:otherwise>
</c:choose>
<%--
int result=check.delete(request.getParameter("id"));
if(result == 1){
	session.invalidate();
	out.print("<script>alert('삭제 되었습니다');"
			+ "location.href='login.jsp';</script>");
}else{
	out.print("<script>alert('문제가 발생 되었습니다');" 
			+ "history.back();</script>");
}
--%>
</body>
</html>