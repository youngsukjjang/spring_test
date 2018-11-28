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
 
<DIV class="title">회원가입 처리</DIV>

<div class="content">
<c:choose>
	<c:when test="${flag }">회원 가입을 축하합니다.
	<input type='button' value='로그인' onclick="location.href='./login'">
    <input type='button' value='홈' onclick="location.href='${root}/'">
	</c:when>
	<c:otherwise>회원 가입을 실패했습니다.
	<input type='button' value='다시 시도' onclick="history.back()">
    <input type='button' value='홈' onclick="location.href='/'">
	</c:otherwise>

</c:choose>

</div>

  
</body>
<!-- *********************************************** -->
</html>