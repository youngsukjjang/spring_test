<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("utf-8"); %> 
<%@ include file = "/ssi/ssi.jsp" %>


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
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
function read(no){
	//alert(no);
	var url = "read";
	url= url+"?no="+no;
	location.href=url;
}
function update(no){
	//alert(no);
	var url="update";
	url= url+"?no="+no;
	location.href=url;
	
}
function del(no){
	//alert(no);
	if(confirm("정말 삭제하시겠습니까?")){
		var url = "delete";
		url = url+"?no="+no;
		
		location.href=url;
	}
}
function reply(no) {
	var url= "reply";
	url= url+"?no="+no;
	
	location.href=url;
}
</script>

</head> 
<body> 
<div class="container">
<div class="title">팀 목록</div>
<div class="search">
<form method="POST" action="./list">
<select name="col">
 <option value="name">이름</option>
 <option value="address">주소</option>
 <option value="skill">보유기술</option>
 <option value="phone">전화번호</option>
 <option value="total">전체 </option>
</select>
<input type="text" name="word">
<input type="submit" value="검색">
</form>
</div>
<table >
<tr>
	<th>번호</th>
	<th>이름</th>
	<th>성별</th>
	<th>전화번호</th>
	<th>보유기술</th>
	<th>수정/삭제/답변</th>
	<th>grpno</th>
	<th>indent</th>
	<th>ansnum</th>
</tr>
<c:forEach var="dto" items="${list}">

<tr>
<%-- 	<td><a href="javascript:read('<%=rs.getInt("memono") %>')"><%=rs.getString("title") %><a/></td> --%>
	<td>${dto.no}</td>

	<td>
	<c:forEach var="r" begin="0" end = "${dto.indent}">
	&nbsp;&nbsp;
	</c:forEach>
	<c:if test="${dto.indent > 0 }">
	<img src='../images/re.jpg'>
	</c:if>
	

	<a href="javascript:read('${dto.no}')">${dto.name}</a></td>
	<td>${dto.gender}</td>
	<td>${dto.phone}</td>
	<td>${dto.skills}</td>
	<td>${dto.grpno}</td>
	<td>${dto.indent}</td>
	<td>${dto.ansnum}</td>
	<td><a href="javascript:update('${dto.no}')" >수정</a>
	/
	<a href="javascript:del('${dto.no}')"> 삭제</a>
	/<a href="javascript:reply('${dto.no}')">답변 </a>
	</td>
	<td>${dto.grpno}</td>
	<td>${dto.indent}</td>
	<td>${dto.ansnum}</td>
</tr>
</c:forEach>

</table>
<div class="bottom">
${paging }


<button type="button" onclick="location.href='./create'">등록</button>
</div>

</div>
</body> 
</html> 