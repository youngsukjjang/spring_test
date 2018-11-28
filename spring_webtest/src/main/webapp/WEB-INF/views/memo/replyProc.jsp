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
	url = "./list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
</script>

</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">처리 결과</DIV>

<div class="content">
<c:choose>
<c:when test="${flag }">답변이 등록되었습니다.
<input type='button' value='목록' onclick="mlist()">
</c:when>
<c:otherwise>답변 등록이 실패했습니다.
<input type='button' value='등록' onclick="location.href='./create'">
</c:otherwise>

</c:choose>
</div>
 
</body>
<!-- *********************************************** -->
</html>