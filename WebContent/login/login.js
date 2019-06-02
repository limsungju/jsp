function pwchk(){ 
	pw1 = document.getElementById("pwd1").value;
	pw2 = document.getElementById("pwd2").value;
	if(pw1 == pw2){
		document.getElementById("label").innerHTML="확인!!";
	}else{
		document.getElementById("label").innerHTML="불일치!!";
		document.getElementById("pwd1").value="";
		document.getElementById("pwd2").value="";
	}
}
function chkValidate(){ 
	if(document.getElementById('id').value == ""){
		alert('아이디는 필수 입력 사항입니다.');	return;
	}else if(document.getElementById('pwd1').value == ""){
		alert('비밀번호는 필수 입력 사항입니다.');	return;
	}else{ document.form.submit(); }
}



