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
</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">아이디 및 이메일 중복 확인</DIV>

<div class="content">
${str}
</div>
  
  <DIV class='bottom'>
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>
 
 
</body>
<!-- *********************************************** -->
</html>