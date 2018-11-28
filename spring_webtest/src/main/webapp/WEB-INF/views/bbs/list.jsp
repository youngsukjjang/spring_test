<%@ page contentType="text/html; charset=UTF-8"%>
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
function bread(bbsno) {
	var url = "read";
	url = url + "?bbsno=" + bbsno;
	url = url + "&col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href = url;
}

function filedown(filename) {
	var url = "${root}/download";
	url += "?filename=" + filename;
	url += "&dir=/bbs/storage";
	location.href = url;
}
</script>

</head> 
<!-- *********************************************** -->
<body>

<div class="search">

 <form name="frm" method="post" action="./list">
  <select name="col">
   <option value="wname"
   <c:if test="${col=='wname'}">selected</c:if>
   >성명</option>
   <option value="title"
   <c:if test="${col=='title'}">selected</c:if>
   >제목</option>
   <option value="content"
   <c:if test="${col=='content'}">selected</c:if>
   >내용</option>
   <option value="total">전체 출력</option>  
  </select>

  <input type="text" name="word" value="${word}">
  
  <button>검색</button>
  <button type="button" onclick="location.href='./create">등록</button>
 </form>

</div>

<div class="container">

<h2><span class="glyphicon glyphicon-th-list"></span>
게시판 목록</h2>

  <table class="table table-hover">
   <thead>
    <TR>
      <TH>번호</TH>
      <TH>성명</TH>
      <TH>제목</TH>
      <TH>조회수</TH>
      <TH>등록일</TH>
      <TH>파일명</TH> 
    </TR>
   </thead>
<c:choose>
<c:when test="${empty list}">

 
   <tbody>
	<tr>
	 <td colspan="6">
	 등록된 글이 없습니다.
	 </td>
	</tr> 
   </tbody>
   </c:when>
   <c:otherwise>   
   <c:forEach var="dto" items="${list}">

   <tbody>
    <TR>
    	<td>${dto.bbsno}</td>
    	<td>${dto.wname}</td>
    	<td>
    	
    	 <c:set var="rcount" value="${util:rcount(dto.bbsno,rdao) }"/>
		 <a href="javascript:bread('${dto.bbsno}')">${dto.title}</a>
		  <c:if test="${rcount>0 }">
            <span style="color:red;">(${rcount})</span>
          </c:if>
		 <c:if test="${util:newImg(dto.wdate) }">
		  </c:if>
		 <img src=' ${root }/images/new.jpg'>
		 <c:if test="${dto.indent > 0}">
		 <img src=' ${root }/images/re.jpg'>
		
		 </c:if>
		 
    	</td>
    	<td>${dto.wname}</td>
    	<td>${dto.viewcnt}</td>
    	<td>${dto.wdate}</td>
    	<td>
    	<c:choose>
    	<c:when test="${not empty dot.filename }">
    	<a href="javascript:filedown('${dto.filename}')">
    		${dto.filename }
    	</a>
    	</c:when>
    	<c:otherwise>파일없음</c:otherwise>
    	
    	</c:choose>
    
    	</td>
    </TR> 
   </tbody>    
</c:forEach>
</c:otherwise>
</c:choose>  
  </TABLE>
  
  
   ${paging}
  </DIV>


</body>

</html>