<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function(){
	$("#btnWrite").click(function(){
		location.href="${path}/board/write.jsp";
	});
});
//페이지 이동 함수 
function list(page){
	location.href="${path}/board_servlet/list.do?curPage="+page;
}
</script>
</head>
<body>
<h2>게시판</h2>
	<a href="${path}/board_servlet/list.do">전체보기</a>
	<a href="${path}/board_servlet/travleList.do">여행후기</a>
	<a href="${path}/board_servlet/qnaList.do">QnA</a>
<form name="form1" method="post" 
	action="${path}/board_servlet/search.do">

<select name="search_option">
	<option value="writer">작성자</option>
	<option value="subject">제목</option>
	<option value="content">내용</option>
	<option value="all">작성자+제목+내용</option>
</select>
<input name="keyword">
<button id="btnSearch">검색</button>
</form>

<button type="button" id="btnWrite">글쓰기</button>

<table border="1" width="900px">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>제목</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
<c:forEach var="dto" items="${list}"> <!-- var="개별값" items="집합" -->
	<tr align="center">
		<td>${dto.num}</td> <!-- 실제로는 dto.getNum()이 호출됨 -->
		<td>${dto.writer}</td>
		<td align="left">
<!-- 답변 들여쓰기 -->		
		<c:forEach var="i" begin="1" end="${dto.re_level}">
			&nbsp;&nbsp;
		</c:forEach>
		
<a href="${path}/board_servlet/view.do?num=${dto.num}">
${dto.subject}</a>
			<c:if test="${dto.comment_count > 0 }"> 
				[ ${dto.comment_count} ]
			</c:if>
		</td>
		<td>${dto.reg_date}</td>
		<td>${dto.readcount}</td>
		
	</tr>
</c:forEach>	
<%-- <%@ page import="java.util.List" %>
<%@ page import="board.dao.BoardDAO" %>
<%@ page import="board.dto.BoardDTO" %>
<%
List<BoardDTO> list=(List<BoardDTO>)request.getAttribute("list");
for(BoardDTO dto : list){
%>
	<tr>
		<td><%=dto.getNum()%></td>
	</tr>
<%	
}
%> --%>
</table>

<!-- 페이지 네비게이션 출력 -->
<table width="900px">
	<tr>
		<td align="center">
			<c:if test="${page.curBlock > 1}">
				<a href="#" onclick="list('1')">[처음]</a>
			</c:if>
			<c:if test="${page.curBlock > 1}">
				<a href="#" onclick="list('${page.prevPage}')">[이전]</a>
			</c:if>
			<c:forEach var="num" begin="${page.blockStart}"
				end="${page.blockEnd}">
				<c:choose>
					<c:when test="${num == page.curPage}">
<!-- 현재 페이지인 경우 -->					
						<span style="color:red;">${num}</span> | 
					</c:when>
					<c:otherwise>
<!-- 현재 페이지가 아닌 경우 -->					
						<a href="#" onclick="list('${num}')">${num}</a> |					
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.curBlock < page.totBlock}">
				<a href="#" onclick="list('${page.nextPage}')">[다음]</a>
			</c:if>
			<c:if test="${page.curPage < page.totPage}">
				<a href="#" onclick="list('${page.totPage}')">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>

</body>
</html>









