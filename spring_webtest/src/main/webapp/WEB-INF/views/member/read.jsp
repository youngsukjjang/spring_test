<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
<%
	System.out.println(session.getAttribute("id"));
	System.out.println(session.getAttribute("grade"));
%>

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
	
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	
	location.href = url;
}

function updateFile() {
	var url = "./updateFile";
	
	url = url + "?id=${id}";
	url = url + "&oldfile=${dto.fname}";
	
	location.href = url;
}

function updatePasswd() {
	var url = "./updatePasswd";
	
	url = url + "?id=${id}";
	
	
	location.href = url;
}

function mupdate() {
	var url = "./update";
	url += "?id=${id}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}

function mdelete() {
	var url = "./delete";
	url = url + "?id=${id}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
</script>

<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">[${dto.mname}]의 회원정보</DIV>
 
  <TABLE>
    <TR>
      <TD colspan="2" style="text-align: center; margin: auto;">
       <img src="./storage/${dto.fname}">
      </TD>
    </TR>
    <TR>
      <TH>아이디</TH>
      <TD>${dto.id}</TD>
    </TR>
    <TR>
      <TH>성명</TH>
      <TD>${dto.mname}</TD>
    </TR>
    <TR>
      <TH>전화번호</TH>
      <TD>${dto.tel}</TD>
    </TR>
    <TR>
      <TH>이메일</TH>
      <TD>${dto.email}</TD>
    </TR>
    <TR>
      <TH>우편번호</TH>
      <TD>${dto.zipcode}</TD>
    </TR>
    <TR>
      <TH>주소</TH>
      <TD>
       ${dto.address1}
       ${dto.address2}
      </TD>
    </TR>
    <TR>
      <TH>직업</TH>
      <TD>
          직업 코드 : ${dto.job}
          (${util:jobName(dto.job)})
      </TD>
    </TR>
    <TR>
      <TH>날짜</TH>
      <TD>${dto.mdate}</TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    
    <input type='button' value='정보 수정' onclick="mupdate()">
    <input type='button' value='회원탈퇴' onclick="mdelete()">
    <c:if test="${not empty sessionScope.id && sessionScope.grade != 'A' }">
    <input type='button' value='패스워드 변경' onclick="updatePasswd()">
    <input type='button' value='사진 수정' onclick="updateFile()">
   </c:if>
   
   </DIV> 
 
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>