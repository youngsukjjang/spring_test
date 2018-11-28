<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<c:set var="title" value="나의 webtest"/>
<c:if test="${not empty sessionScope.id && sessionScope.grade == 'A' }">
<c:set var ="title" value ="관리자 페이지">
</c:set>
</c:if>
<c:choose>
	<c:when test=""></c:when>



</c:choose>
<html>
<head>
	<title>Home</title>
</head>
<body>





<div class="container">
<P>  The time on the server is ${serverTime}. </P>
<h2>${title }</h2>
 

<h1>${str }</h1>
<img src="${pageContext.request.contextPath }/images/main.jpg" width="35%"><br><br><br>

 
<c:choose>
	<c:when test="${empty sessionScope.id }">
	<button onclick="location.href='./member/login.do'">로그인</button>
	
	</c:when>
	
	
	
	
	<c:otherwise>
	<button onclick="location.href='./member/logout.do'">로그아웃</button>
	
	</c:otherwise>


</c:choose>




<h1>${param.name}님 안녕하세요!</h1>
</div>
</body>
</html>
