<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<a href="${path}/product_servlet/list.do">상품목록</a> |
<c:if test="${sessionScope.userid != null }"> 
	<a href="${path}/cart_servlet/list.do">장바구니</a> |
</c:if>
<div style="text-align: right;">
<c:choose>
  <c:when test="${sessionScope.userid == null }">
    <a href="${path}/shop/login.jsp">로그인</a> | 
    <a href="${path}/shop/admin_login.jsp">관리자 로그인</a>     
  </c:when>
  <c:otherwise>
  	${sessionScope.name}님이 로그인중입니다.
  	<a href="${path}/login_servlet/logout.do">로그아웃</a>
  </c:otherwise>
</c:choose>
</div>
<hr>



