<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">번호삭제 처리</DIV>
 
<div class="content">
<c:choose>
	<c:when test="${rflag }">답변 있는 부모글은 삭제할 수 없습니다.</c:when>
	<c:when test="!pflag">비밀번호가 틀렸습니다.</c:when>
	<c:when test="flag">삭제되었습니다.</c:when>
	<c:otherwise>삭제를 실패했습니다.</c:otherwise>

</c:choose>



</div>
  
  <DIV class='bottom'>
  <c:choose>
  	<c:if test="${flag || rflag }">
  	<input type='button' value='목록' onclick="location.href='./list'">
  	</c:if>
  	<c:otherwise>
  	<input type='button' value='다시시도' onclick="history.back()">
    <input type='button' value='목록' onclick="location.href='./list'">
  	</c:otherwise>
  </c:choose>
  
  </DIV>

 </body>
<!-- *********************************************** -->
</html> 