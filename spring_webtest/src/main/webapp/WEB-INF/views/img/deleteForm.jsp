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
</style> 
</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">삭제</DIV>
 
<FORM name='frm' method='POST' action='./delete'>
<input type="hidden" name="no" value="${param.no}">
<input type="hidden" name="oldfile" value="${param.oldfile}">
  
  <div class = "content">
  삭제를 하시면 더이상 되돌릴수 없습니다.<br>
  그래도 삭제를 원하시면 비밀번호를 입력하세요.
 <table>
 <tr>
 <th>비밀번호</th>
 <td><input type="password" name="passwd"></td>
 
 </tr>
 </table>
  
  </div>
  
  <DIV class='bottom'>
    <input type='submit' value='삭제'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
</body>
<!-- *********************************************** -->
</html> 

