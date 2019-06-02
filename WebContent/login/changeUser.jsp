<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
<%-- request.setCharacterEncoding("UTF-8"); --%>
<%-- response.setContentType("text/html;UTF-8"); --%>
<jsp:useBean id="check" class="members.MemberDAO"/>
<jsp:useBean id="dto" class="members.MemberDTO"/>
<jsp:setProperty property="*" name="dto"/>
<c:set var="result" value="${check.changeUser(dto) }"/>
<c:choose>
	<c:when test="${result eq 1 }">
		<script>
			alert("변경되었습니다");
			location.href="userInfo.jsp?id=${param.id}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("문제가 발생하였습니다. 관리자에게 문의하세요");
			location.href="userInfo.jsp?id=${param.id}";
		</script>
	</c:otherwise>
</c:choose>
<%--
String id = request.getParameter("id");
int result = check.changeUser(dto);
if(result == 1){
	out.print("<script>alert('변경 되었습니다.');"
				+"location.href='userInfo.jsp?id="+id+"';</script>");
}else{
	out.print("<script>alert('문제가 발생하였습니다. 관리자에게 문의하세요.');"
			+"location.href='userInfo.jsp?id="+id+"';</script>");
}
--%>
</body>
</html>