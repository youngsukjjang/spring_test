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
function mlist() {
	var url = "./list";
	location.href = url; 
}

function mread() {
	var url = "./read";
	url += "?id=${id}";
	location.href = url;
}
</script>
 
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">처리 결과</DIV>

<c:choose>
	<c:when test="${!pflag }">
	<div class="content">기존 비밀번호를 잘못 입력하셨습니다. 다시 시도하세요.</div>
 	<DIV class='bottom'>
	<input type='button' value='다시 시도' onclick="history.back()">
 </div>
	</c:when>
	<c:when test="${flag }">
	<div class="content">비밀번호를 변경 했습니다.</div>
 	<DIV class='bottom'>
	<input type='button' value='내 정보' onclick="mread()">
	<input type='button' value='목록' onclick="mlist()">
 </div>
	</c:when>
	<c:otherwise>
	<div class="content">비밀번호 변경을 실패했습니다.</div>
 	<DIV class='bottom'>
	<input type='button' value='다시 시도' onclick="history.back()">
 </div>
	
	</c:otherwise>

</c:choose>


 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>