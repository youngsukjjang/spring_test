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
 
<DIV class="title">로그인 처리</DIV>

<div class="content">
<c:choose>
<c:when test="${flag }">로그인이 성공하였습니다.</c:when>
<c:otherwise>
아이디 혹은 비밀번호가 틀렸거나, 회원이 아닙니다.<br>
다시 시도하거나 회원가입을 하세요.
</c:otherwise>


</c:choose>

</div>

<DIV class='bottom'>
    <input type='button' value='홈' onclick="location.href='${root}'">
    <input type='button' value='다시시도' onclick="history.back()">
    <input type='button' value='회원가입' onclick="location.href='./agree'">    
  </DIV>



</body>
<!-- *********************************************** -->
</html>