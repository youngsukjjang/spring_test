<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>


  
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function iread(){
	var url = "read";
	url += "?no=${no}";
	location.href = url;
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
 
<DIV class="title">정보수정</DIV>
 
 
  <DIV class="content">
  <c:choose>
  	<c:when test="${!flag }">비밀번호가 틀립니다.
  	<input type='button' value='정보확인' onclick="iread()">
  	</c:when>
  	<c:when test="${flag2 }">정보가 수정되었습니다.
  	<input type='button' value='목록' onclick="location.href='list'">
  	</c:when>
  	<c:otherwise>정보 수정에 실패했습니다.
  	<input type='button' value='다시시도' onclick="history.back()">    
    <input type='button' value='목록' onclick="location.href='list'">
  	</c:otherwise>
  
  
  </c:choose>
 
  </DIV>

</body>
<!-- *********************************************** -->
</html> 