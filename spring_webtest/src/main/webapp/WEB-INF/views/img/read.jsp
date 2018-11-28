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
.img {
	text-align: center;
	border-style: solid;
	border-width: 1px;
	padding: 10px;
	margin: 20px auto;
}
.centerImg {
	text-align: center;
	border-style: solid;
	border-width: 1px;
	width: 330px;
	padding: 10px;
	margin: 20px auto;
}
.info {
	text-align: center;
	border-style: solid;
	border-width: 1px;
	width: 45%;
	height: 40%;
	padding: 10px;
	margin: 20px auto;
}
.now {
	margin-right:0;
	border-style:solid;
	border-width: 2px;
	border-color: red;
	width: 100px;
	height: 100px;
}
.tableTH {
	width: 20%;
	text-align: center;
}
</style> 

<script type="text/javascript">
function iupdate() {
	var url = "./update"
	url += "?no=${dto.no}";
	location.href = url;
}
function idelete() {
	var url = "./delete"
	url += "?no=${dto.no}";
	url += "&oldfile=${dto.fname}";  
	location.href = url;
}

function ilist() {
	var url = "./list"
	location.href = url;
}

function iread(no) {
	var url = "./read"
	url += "?no=" + no;
	location.href = url;
}

function ireply() {
	var url = "./reply"
		url += "?no=${dto.no}";
	location.href = url;
}
</script>

</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">이미지 정보</DIV>

<div class="centerImg">
 <img style="width:300px; height:300px;" src="${root}/storage/${dto.fname}">
</div>

<br>

  <TABLE style="width:30%; height:20%; margin: auto; text-align: center;">
    <tr>
    
<c:forEach var="r" begin="0"  end="4">
<c:choose>
	<c:when test="${empty fArr[i] }">
		<td class="img">
	   	<img src="./storage/null.png" width="100px" height="100px">
	 	 </td>
	</c:when>
	
	<c:otherwise>
		<c:choose>
			<c:when test="${noArr[i] == dto.no }">
				<td class="img">
			   <a href="javascript:iread('${noArr[i]})">
		  		<img class="now" src="./storage/${fArr[i]}">
		  	   </a>
			  </td>				
					
			</c:when>
			<c:otherwise>
				<td class="img">
	   			<a href="javascript:iread('${noArr[i]}')">
  				<img src="./storage/${fArr[i]}" width="100px" height="100px">
  	   			</a>
	  			</td>				
			
			
			
			</c:otherwise>
		
		
		</c:choose>

	
	</c:otherwise>

</c:choose>


</c:forEach>

    </tr>
 </table>
 <br>
   
 <table class="info" >
    <TR>
      <TH class="tableTH">제 &nbsp;목</TH>
      <TD colspan="4">${dto.title}</TD>
    </TR>
    <TR>
      <TH class="tableTH">내 &nbsp;용</TH>
      <TD colspan="4">${dto.content}</TD>
    </TR>
    <TR>
      <TH class="tableTH">작성일</TH>
      <TD colspan="4">${dto.wdate}</TD>
    </TR>
    <TR>
      <TH class="tableTH">조회수</TH>
      <TD colspan="4">${dto.viewcnt}</TD>
    </TR>
  </TABLE>
  
  <DIV style="text-align: center; margin: auto;">
    <input type='button' value='수정' onclick="iupdate()">
    <input type='button' value='삭제' onclick="idelete()">
    <input type='button' value='목록' onclick="ilist()">
    <input type='button' value='답변' onclick="ireply()">
  </DIV>
 
 
</body>
<!-- *********************************************** -->
</html>