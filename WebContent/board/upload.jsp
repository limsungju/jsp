<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script>
	
</script>
</head>
<body>
	<!-- upload.jsp -->
	<h2>파일업로드</h2>
	<!-- method="post"
enctype="multipart/form-data" -->
	<form name="form1" method="post" enctype="multipart/form-data"
		action="upload_result.jsp">
		이름 : <input name="name"><br> 
		제목 : <input name="subject"><br>
		파일1 : <input type="file" name="file1"><br> 
		파일2 : <input type="file" name="file2"><br> 
		<input type="submit" value="업로드">
	</form>
</body>
</html>