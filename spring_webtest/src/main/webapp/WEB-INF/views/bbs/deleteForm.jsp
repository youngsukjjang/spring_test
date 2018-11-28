<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>


<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type="text/javascript">
function bcheck(f) {
	if (f.passwd.value == "") {
		alert("비밀번호를 입력하세요");
		f.passwd.focus();
		
		return false;
	}
}

function blist() {
	url = "./list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";	
	location.href = url;
}
</script>

</head> 
<!-- *********************************************** -->
<body>

<c:choose>
<c:when test="${flag }">답변 글이 존재하여 삭제 할 수 없습니다.
<input type='button' value='목록' onclick="blist()">
</c:when>
<c:otherwise>삭제하면 복구할 수 없습니다.</c:otherwise>

</c:choose>

 
<FORM name='frm' method='POST' action='./delete'
	  onsubmit="return bcheck(this)">
<input type="hidden" name="bbsno" value="${param.bbsno}">
<input type="hidden" name="col" value="${param.col}">
<input type="hidden" name="word" value="${param.word}">
<input type="hidden" name="nowPage" value="${param.nowPage}">
<input type="hidden" name="oldfile" value="${param.oldfile}">
  <TABLE>
    <TR>
      <TH>패스워드</TH>
      <TD>
       <input type="password" name="passwd">
      </TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='확인'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>


 
 
</body>
<!-- *********************************************** -->
</html>