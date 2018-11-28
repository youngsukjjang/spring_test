<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>


<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
* { 
	font-family: gulim; 
	font-size: 20px; 
} 
</style> 

<script type="text/javascript">
function bcheck(f) {
	if (f.wname.value == "") {
		alert("성명을 입력하세요");
		f.wname.focus();
		
		return;
	}
	if (f.title.value == "") {
		alert("제목을 입력하세요");
		f.title.focus();
		
		return;
	}
	if (f.content.value == "") {
		alert("내용을 입력하세요");
		f.content.focus();
		
		return;
	}
	if (f.passwd.value == "") {
		alert("비밀번호를 입력하세요");
		f.passwd.focus();
		
		return;
	}
	
	f.submit();
}

function blist() {
	var url = "./list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>





</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">수정</DIV>
 
<FORM name='frm' method='POST' action='./update'
	  enctype="multipart/form-data">
<input type="hidden" name="bbsno" value="${dto.bbsno}">
<input type="hidden" name="col" value="${param.col}">
<input type="hidden" name="word" value="${param.word}">
<input type="hidden" name="nowPage" value="${param.nowPage}">
<input type="hidden" name="filename" value="${dto.filename}">
  <TABLE>
    <TR>
      <TH>성명</TH>
      <TD>
       <input type="text" name="wname" value="${dto.wname}">
      </TD>
    </TR>
    <TR>
      <TH>제목</TH>
      <TD>
       <input type="text" name="title" value="${dto.title}">
      </TD>
    </TR>
    <TR>
      <TH>내용</TH>
      <TD>
       <textarea rows="10" cols="45" name="content">${dto.content}</textarea>
      </TD>
    </TR>
    <TR>
      <TH>비밀번호</TH>
      <TD>
       <input type="password" name="passwd">
      </TD>
    </TR>
    <TR>
      <TH>파일</TH>
      <TD>
       <input type="file" name="filenameMF">
      </TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='button' value='수정' onclick="bcheck(this.form)">
    <input type='button' value='목록' onclick="blist()">
    <input type="reset" value="입력 취소">
  </DIV>
</FORM>
 
 
</body>
<!-- *********************************************** -->
</html>