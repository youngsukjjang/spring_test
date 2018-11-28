<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title>  
<script type="text/javascript">
function incheck(f){
	if(f.title.value==""){
		alert("제목을 입력하세요");
		f.title.focus();
		return false;
	}
	if(f.content.value==""){
		alert("내용을 입력하세요");
		f.content.focus();
		return false;
	}
	if(f.passwd.value==""){
		alert("비밀번호를 입력하세요");
		f.passwd.focus();
		return false;
	}
	
}
</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">답변</DIV>
 
<FORM name='frm' method='POST' action='./reply' onsubmit="return incheck(this)">
<input type="hidden" name="no" value="${dto.no}">
<input type="hidden" name="grpno" value="${dto.grpno}">
<input type="hidden" name="indent" value="${dto.indent}">
<input type="hidden" name="ansnum" value="${dto.ansnum}">
<input type="hidden" name="fname" value="${dto.fname}">

  <TABLE>
    <TR>
      <TD colspan="2" >
      <img style="width:600px" src="./storage/${dto.fname}">
      </TD>
    </TR>
    <TR>
      <TH>제목</TH>
      <TD><input type="text" name="title" value="[답변]${dto.title}"></TD>
    </TR>
    <tr>
    	<th>내용</th>
    	<td><textarea rows="10" cols="45" name="content"></textarea></td>
    </tr>
    <tr>
    	<th>비밀번호</th>
    	<td><input type="password" name="passwd"></td>
    </tr>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='등록'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
  
</body>
<!-- *********************************************** -->
</html>