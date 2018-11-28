<%@ page contentType="text/html; charset=UTF-8" %> 
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

div {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}

table {
	width: 50%;
	margin: 0 auto;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style> 

<script type="text/javascript">
function input(f) {
	if (f.title.value == "") {
		alert("제목을 입력하세요");
		f.title.focus();
		return false;
	}
	
	if(f.content.value == "") {
		alert("내용을 입력하세요");
		f.content.focus();
			// 경고창이 뜨고 확인버튼을 누르면 내용 입력란으로 커서가 이동됨
		return false;
	}	
}
</script>

</head> 
<body> 

<div>수정</div>

<form name='frm' method='post' action='./update' onsubmit="return input(this)">
 <input type="hidden" name="memono" value="${dto.memono}">
 <input type="hidden" name="col" value="${param.col }">
 <input type="hidden" name="word" value="${param.word }">
 <input type="hidden" name="nowPage" value="${param.nowPage }">
 
 <table>
  <tr>
   <th>제목</th>
   <td> 
    <input type='text' name='title' size='30' value='${dto.title}'>
   </td>
  </tr>
  <tr>
   <th>내용</th>
   <td>
    <textarea rows='10' cols='30' name='content'>${dto.content}</textarea>
   </td>
  </tr>  
 </table>
 
 <div>
  <input type='submit' value='수정'>
 </div>

</form>

</body> 
</html>