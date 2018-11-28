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
function incheck(f) {
	if(f.title.value == "") {
			// =을 쓰면 할당이 되어버리기 때문에 등록or수정 내용이 공백으로 바뀌어버리고
			// 값이 없어졌기 때문에 not null 에러가 남
		alert("제목을 입력하세요");
		f.title.focus();
			// 경고창이 뜨고 확인버튼을 누르면 제목 입력란으로 커서가 이동됨
		return false;
	}
	
	if(f.content.value == "") {
		alert("내용을 입력하세요");
		f.content.focus();
			// 경고창이 뜨고 확인버튼을 누르면 내용 입력란으로 커서가 이동됨
		return false;
	}			
}

function mlist() {
	var url = "./list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";	
	location.href = url;
}
</script>

</head> 
<body> 

<div>답변 등록</div>

<form name='frm' method='post' action='./reply' onsubmit="return incheck(this)">
<input type="hidden" name="memono" value="${dto.memono}">
<input type="hidden" name="grpno" value="${dto.grpno}">
<input type="hidden" name="indent" value="${dto.indent}">
<input type="hidden" name="ansnum" value="${dto.ansnum}">

<input type="hidden" name="col" value="${param.col }">
<input type="hidden" name="word" value="${param.word }">
<input type="hidden" name="nowPage" value="${param.nowPage }">

 <table>
  <tr>
   <th>제목</th>
   <td>
    <!-- <input type='text' name='ti' size='30' value='두시 회의'> -->
    <input type='text' name='title' size='30' value="${dto.title}">
   </td>
  </tr>
  <tr>
   <th>내용</th>
   <td>
    <textarea rows='10' cols='30' name='content'></textarea>
   </td>
  </tr>  
 </table>
 
 <div>
  <input type='submit' value='전송'>
  <button type="button" onclick="mlist()">목록</button>
 </div>

</form>

</body> 
</html>