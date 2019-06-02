<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript" src="../se2/js/service/HuskyEZCreator.js"
		charset="utf-8"></script>
		
<!-- Smart Editor -->
<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="../se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
 


	<script>
	$(function() {
		$("#btnSave").click(function() {
			
			var check = "${sessionScope.userId}";
			
			if(check == undefined || check == null || check == ""){
				alert("로그인 후 작성할 수 있습니다.");
				return;
			}

			
			//첨부파일의 확장자 체크
			var filename=form1.file1.value; //첨부파일의 이름
			var start=filename.lastIndexOf(".")+1; //마지막 마침표의 위치 
			if(start != -1){
				var ext=filename.substring(start, filename.length);
				if(ext=="jsp" || ext=="exe" || ext=="jar"){
					alert("업로드할 수 없는 파일입니다.");
					return;
				}
			}
			//폼데이터를 서버에 제출 
			//document.form1.submit();
			submitContents();
		});
	});

</script>

</head>
<body>
	<h2>글쓰기</h2>
	<form name="form1" method="post" id="form"
		action="${path}/board_servlet/insert.do" enctype="multipart/form-data">
		<table border="1" width="700px">
			<tr>
				<td align="center">작성자</td>
				<td><input name="writer" id="writer"
					value="${sessionScope.userId}" readonly='readonly'></td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td><input name="subject" id="subject" size="60"></td>
			</tr>
			<tr>
				<td align="center">게시판 구분</td>
				<td><select name="gubun" id="gubun">
						<option value="1">여행후기</option>
						<option value="2">QnA</option>
				</select></td>
			</tr>
			<tr>
				<td align="center">본문</td>
				<td><textarea  row="10" col="60" name="content" id="content"></textarea></td>
				<script type="text/javascript">
<!-- Smart Editor -->

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "content",
    sSkinURI: "../se2/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
 
//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
    document.getElementById("form").submit();
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
  
    try {
        elClickedObj.form.submit();
    } catch(e) {
     
    }
}
 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/uploadFolder/'+filepath+'">';
    oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
}
</script>
			</tr>
			<tr>
				<td align="center">첨부파일</td>
				<td><input type="file" name="file1"> <c:if
						test="${param.message == 'error' }">
						<span style="color: red;">업로드할 수 없는 파일입니다.</span>
					</c:if></td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" name="passwd" id="passwd"></td>
			</tr>
			<tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					value="작성하기" id="btnSave"></td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>