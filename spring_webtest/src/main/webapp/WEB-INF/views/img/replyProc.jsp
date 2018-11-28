<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 


 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function ilist(){
	var url = "list";
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
 
<DIV class="title">답변처리</DIV>
<div class="content">
<c:choose>
	<c:when test="${flag }">답변이 등록되었습니다
	<input type='button' value='목록' onclick="ilist()">
	</c:when>
	<c:otherwise>답변등록을 실패했습니다
	<input type='button' value='등록' onclick="location.href='create'">
	</c:otherwise>

</c:choose>

</div>
  
  

</body>
<!-- *********************************************** -->
</html>