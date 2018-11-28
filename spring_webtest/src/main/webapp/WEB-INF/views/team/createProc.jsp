
<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ include file = "/ssi/ssi.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	font-size: 20px;
}
</style>
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">
function tlist() {
	var url = "list";
	
	location.href=url;
	
}
</script>
</head>
<body>
<div class="container">
	<h2>입력 내용</h2>
	<div>
	<c:choose>
		<c:when test="${flag}">등록 성공</c:when>
		<c:otherwise>등록 실패</c:otherwise>
	
	</c:choose>
		
	</div>
	<div id="bottom">
		<button onclick="location.href='create'">다시입력</button>
		<button onclick="tlist()">목록</button>
	</div>
	</div>
</body>
</html>