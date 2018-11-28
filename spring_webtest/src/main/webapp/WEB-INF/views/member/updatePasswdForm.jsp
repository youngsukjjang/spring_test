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
function incheck(f) {
	if (f.oldpasswd.value == "") {
		alert("현재 비밀번호를 입력하세요.");
		f.oldpasswd.focus();
		
		return false;
	}
	if (f.newpasswd.value == "") {
		alert("새로운 비밀번호를 입력하세요.");
		f.newpasswd.focus();
		
		return false;
	}
	if (f.newpasswd2.value == "") {
		alert("새로운 비밀번호를 다시 입력하세요.");
		f.newpasswd2.focus();
		
		return false;
	}
	if (f.newpasswd.value != f.passwd2.value) {
		alert("새로운 비밀번호가 서로 일치하지 않습니다. 다시 입력해주세요.");
		f.newpasswd.focus();
		
		return false;
	}
	if (f.oldpasswd.value == f.passwd.value) {
		alert("기존의 비밀번호와 새로운 비밀번호가 같습니다. 다른 비밀번호를 입력해주세요.");
		f.newpasswd.focus();
		
		return false;
	}	
}
</script>

<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">비밀번호 변경</DIV>
 
<FORM name='frm' method='POST' action='./updatePasswd' 
	  onsubmit="return incheck(this)">
<input type="hidden" name="id" value="${param.id }">
  
<table>
 <tr>
  <th>현재 비밀번호를 입력하세요.</th>
  <td>
   <input type="password" name="oldpasswd" value="${dto.passwd}">
  </td>
 </tr>
 <tr><td colspan="2"></tr>
 <tr>
  <th>새로운 비밀번호를 입력하세요.</th>
  <td>
   <input type="password" name="passwd">
  </td>
 </tr>
 <tr>
  <th>새로운 비밀번호를 다시 입력하세요.</th>
  <td>
   <input type="password" name="passwd2">
  </td>
 </tr>
</table> 

  <DIV class='bottom'>
    <input type='submit' value='변경'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>