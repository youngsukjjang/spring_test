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
	url += "?id=${dto.id}";
	location.href = url;
}
</script>

<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">처리 결과</DIV>
 
<div class="content">
<c:choose>
	<c:when test="${flag }">회원 정보를 수정 했습니다.</c:when>
	<c:otherwise>회원 정보 수정을 실패했습니다.</c:otherwise>

</c:choose>

</div>

  <DIV class='bottom'>
    <input type='button' value='정보 확인' onclick="mread()">
    <input type='button' value='다시 시도' onclick="history.back()">
  </DIV>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>