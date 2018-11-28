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
 
<DIV class="title">결과 처리</DIV>

<div class="content">
<c:choose>
	<c:when test="${flag }">글 작성이 완료 되었습니다.
	<input type='button' value='목록' onclick="location.href='./list'">
	</c:when>
	<c:otherwise>글 작성을 실패했습니다.
	<input type='button' value='다시 시도' onclick="history.back()">
    <input type='button' value='목록' onclick="location.href='./list'">
	</c:otherwise>

</c:choose>
</div>
 
</body>
<!-- *********************************************** -->
</html>