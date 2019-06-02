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
<jsp:useBean id="check" class="members.MemberDAO"/>
<jsp:useBean id="dto" class="members.MemberDTO"/>
<jsp:setProperty property="*" name="dto"/>
<c:set var="result" value="${check.userCheck(dto.id,dto.pwd) }"/>
<c:if test="${result eq -1 }">
	<c:set var="howRow" value="${check.register(dto) }"/>
</c:if>
<c:choose>
	<c:when test="${howRow == 1 }">
		<script>
			alert("회원가입을 축하합니다.");
			location.href="login.jsp";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("문제가 발생하였습니다. 관리자에게 문의하세요.");
			location.href="register.jsp";
		</script>
	</c:otherwise>
</c:choose>

<%--
int howRow = 0 ;
int result = check.userCheck(dto.getId(), dto.getPwd());
if(result == -1)
	howRow = check.register(dto);
if(howRow == 1){
	out.print("<script>alert('회원가입을 축하합니다.');"
				+"location.href='login.jsp';</script>");
}else{
	out.print("<script>alert('문제가 발생하였습니다. 관리자에게 문의하세요.');"
			+"location.href='register.jsp';</script>");
}
--%>

</body>
</html>