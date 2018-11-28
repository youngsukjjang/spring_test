
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 


 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function use() {
	opener.frm.id.value='${id}';
	self.close();
}
</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">아이디 중복 확인</DIV>
 
<div class="content">입력된 ID: ${id}<br><br>
<c:choose>
	<c:when test="${flag }">
	중복되어서 사용할 수 없습니다. <br><br>
	</c:when>
	<c:otherwise>
	중복아님, 사용 가능합니다.<br><br>
	<input type='button' value='사용' onclick='use()'>
	</c:otherwise>

</c:choose>

</div>  
  <DIV class='bottom'>
    <input type='button' value='다시시도' onclick="location.href='id_form.jsp'">
    <input type='button' value='닫기' onclick="window.close()">
  </DIV>

 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 