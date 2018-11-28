<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>



<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type="text/javascript">
function read(memono) {
	//alert(memono);
	 	// 잘 작동하는지 확인만 한 것
	var url = "./read";
	 	// 변수 선언 후 이동할 url주소 할당
	url = url + "?memono=" + memono;
		// url에 [?memono=숫자값]이 추가될 수 있게끔 할당
		// url 주소 안에는 빈 공간이 없게끔 해야 함
	url = url + "&col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href = url;
		// javascript에서 url을 요청 보내는 문법
}
</script>

<style type="text/css"> 
/* *{ 
  font-family: gulim; 
  font-size: 20px; 
} 

div {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}

table {
	width: 60%;
	margin: 0 auto;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
}

a:LINK {
color:#000000;
text-decoration: none;
}
a:VISITED {
color:#000000;
text-decoration: none;
}
a:HOVER {
color:#ff0000;
text-decoration: none;
}
a:ACTIVE {
color:#000000;
text-decoration: none;
} */

.search {
	margin: 5px auto;
	text-align: center;
}
</style> 

</head> 

<body> 

<div>메모 목록</div>

<div class="search">

<form method="post" action="./list">
 <select name="col">
  <option value="title"
  <c:if test="${col=='title'}">selected</c:if>
  >제목</option>
  <option value="content"
  <c:if test="${col=='content'}">selected</c:if>
  >내용</option>
  <option value="total">전체 출력</option>
 </select>
 
 <input type="text" name="word">
 <button>검색</button>
 <button type="button" onclick="location.href='./create'">등록</button>
</form>

</div>

<div class="container">

<h2><span class="glyphicon glyphicon-th-list"></span>
메모장 목록</h2>

<table class="table table-hover">
 <thead>
 <tr>
  <th>번호</th>
  <th>제목</th>
  <th>날짜</th>
  <th>조회수</th>
  <th>grpno</th>
  <th>indent</th>
  <th>ansnum</th>
 </tr>
 </thead>
 <c:choose>
 <c:when test="${empty list }">
 <tbody>
 <tr>
  <td colspan="7">등록된 메모가 없습니다.</td>
 </tr>
 </tbody>
 
 </c:when>
 <c:otherwise>
 <c:forEach var="dto" items="${list}">
  <tbody>
 <tr>
  <td>${dto.memono}</td>
  <td>
  <c:forEach begin="1" end="${dto.indent}">&nbsp;&nbsp;</c:forEach>
    	<c:if test="${dto.indent>0}"><img src='../images/re.jpg'   style="width: 30px;  height: 30px;">    </c:if>
   <a href="javascript:read('${dto.memono}')">
    ${dto.title}
   </a>
  </td>
  <td>${dto.wdate}</td>
  <td>${dto.viewcnt}</td>
  <td>${dto.grpno}</td>
  <td>${dto.indent}</td>
  <td>${dto.ansnum}</td>
 </tr>
 </tbody>

</c:forEach>
 
 </c:otherwise>
 
 </c:choose>

</table>

<div>
 ${paging}
</div>

</div>

</body> 
</html>