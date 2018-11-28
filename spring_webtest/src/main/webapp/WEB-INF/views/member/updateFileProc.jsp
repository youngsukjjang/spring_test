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
function mread() {
	var url = "./read.jsp";	
	url = url + "?id=" + id;	
	location.href = url;
}
</script>
 
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
 
<DIV class="title">사진 변경</DIV>
<c:choose>
	<c:when test="${flag }">사진 변경을 성공했습니다.</c:when>
	<c:otherwise>사진 변경을 실패했습니다.</c:otherwise>

</c:choose>




  
  <DIV class='bottom'>
  <c:choose>
  	<c:when test="">사진변경 했습니다.
  	<input type='button' value='나의 정보' onclick="mread()">
  	</c:when>
  	<c:otherwise>사진변경을실패했습니다.
  	<input type='button' value='다시 시도' onclick="history.back()">
  	</c:otherwise>
  
  </c:choose>

  </DIV>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>