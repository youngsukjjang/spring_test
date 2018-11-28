<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("utf-8"); %> 
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
/* div{
text-align: center;
	margin: 10xp;
	padding:5px;
  }
table{
	margin: auto;
	width:60%;
}
table,th,td{
border:1px solid black;
	border-collapse: COLLAPSE;
	padding: 5PX; 
} */
</style> 
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
function tlist(){
	var url = "list.do";
	location.href=url;
}
</script>
</head> 
<body> 
<div class="container">
<div class="title">조회</div>
<table class="table table-borderd">
	 <tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${dto.gender}</td>
	</tr>
	<tr>
		<th>보유기술</th>
		<td>${dto.skills}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${dto.phone}</td>
	</tr>
	<tr>
		<th>취미</th>
		<td>${dto.hobby}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${dto.zipcode}</td>
	</tr> 
	 <tr>
		<th>주소</th>
		<td>${dto.address}<br>
			${dto.address2}</td>
			
	</tr> 
	
	
	
</table>
<div class="bottom">
<button type="button" onclick="tlist()">목록</button>
</div>
</div>
</body> 
</html> 