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
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">회원 탈퇴 처리</DIV>

<div class="content">
<c:choose>
	<c:when test="">탈퇴 되었습니다. 자동 로그아웃 됩니다.</c:when>
	<c:otherwise>탈퇴가 실패되었습니다.</c:otherwise>

</c:choose>

</div>
  
  <DIV class='bottom'>
    <input type='button' value='홈' onclick="location.href='../index.jsp'">
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>