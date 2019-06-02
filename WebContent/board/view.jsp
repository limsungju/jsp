<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function(){ //페이지가 로딩되면
	comment_list();
	//답변 버튼(답변 작성 페이지로 이동) 
	$("#btnReply").click(function(){
		document.form1.action="${path}/board_servlet/reply.do";
		document.form1.submit();
	});
	//댓글 쓰기 버튼 
	$("#btnSave").click(function(){
		comment_add();
	});
	
	$("#btnList").click(function(){ //목록 버튼을 클릭하면 
		location.href="${path}/board_servlet/list.do";
	});
	//수정,삭제 화면(편집화면)으로 이동 
	$("#btnEdit").click(function(){
		document.form1.action="${path}/board_servlet/pass_check.do";
		document.form1.submit();
	});
});
function comment_add(){
	var check = "${sessionScope.userId}";
	
	if(check == undefined || check == null || check == ""){
		alert("로그인 후 작성할 수 있습니다.");
		return;
	}
	
	// 게시물번호, 이름, 내용을 파라미터로 넘김
	var param={"board_num":${dto.num}, 
			"writer": $("#writer").val(),
			"content": $("#content").val() };
	$.ajax({
		type: "post",
		url: "${path}/board_servlet/commentAdd.do",
		data: param,
		success: function(){ //서버에서 글쓰기가 완료되면 
			$("#writer").val(""); //입력한 내용을 지움
			$("#content").val("");
			comment_list(); //댓글 목록을 새로고침 
		}
	});
}
function comment_list(){
	//비동기적인 방식으로 댓글 목록을 가져와서 div에 출력시킴 
	$.ajax({
		type: "post",
		url: "${path}/board_servlet/commentList.do",
		data: "num=${dto.num}",
		success: function(result){
			$("#commentList").html(result);
		}
	});
}
</script>
</head>
<body>
<h2>상세화면</h2>
<form name="form1" method="post">
<table border="1" width="700px">
  <tr>
  	<td width="10%" align="center">등록일자</td>
  	<td width="40%">${dto.reg_date}</td>
  	<td width="10%">조회수</td>
  	<td width="40%">${dto.readcount}</td>
  </tr>	
  <tr>
  	<td align="center">작성자</td>
  	<td colspan="3">${dto.writer}</td>
  </tr>
  <tr>
  	<td align="center">제목</td>
  	<td colspan="3">${dto.subject}</td>
  </tr>
  <tr>
  	<td align="center">본문</td>
  	<td colspan="3">${dto.content}</td>
  </tr>
<tr>
  	<td align="center">비밀번호</td>
  	<td colspan="3">
  		<input type="password" name="passwd" id="passwd">
  		<c:if test="${param.message == 'error'}">
  		  <span style="color:red">비밀번호가 일치하지 않습니다.</span>
  		</c:if>
  	</td>
  </tr>
  <tr>
  	<td align="center">첨부파일</td>
  	<td colspan="3">
  	 <c:if test="${dto.filesize > 0}">
  	 <a href="${path}/board_servlet/download.do?num=${dto.num}">
				<img src="../images/file.gif">
			</a>
    		${dto.filename} 
    		( ${dto.filesize} bytes )
	   </c:if>
  	</td>
  </tr>
  <tr>	
  	<td colspan="4" align="center">
  		<!-- 게시물번호 -->
  		<input type="hidden" name="num" value="${dto.num}">
  		<input type="button" value="수정/삭제" 
  			id="btnEdit">
  		<input type="button" value="답변"
  			id="btnReply">
  		<input type="button" value="목록"
  			id="btnList">
  	</td>
  </tr>
</table>
</form>
<!-- 댓글 입력 코드 -->
<table width="700px">
	<tr>
		<td><input id="writer" placeholder="작성자" value="${sessionScope.userId}" readonly='readonly'></td>
		<td rowspan="2">
			<button id="btnSave" type="button">확인</button>
		</td>
	</tr>
	<tr>
		<td><textarea rows="5" cols="80" id="content"
			placeholder="내용을 입력하세요."></textarea>
	</tr>
</table>

<!-- 댓글 목록을 출력할 영역 -->
<div id="commentList"></div>
</body>
</html>









