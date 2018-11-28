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
</style> 
<link href="${root}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">사진 수정</DIV>
 
<FORM name='frm' method='POST' action='./updateFile'
	  enctype="multipart/form-data">
	  
<input type="hidden" name="id" value="${param.id}">	  
<input type="hidden" name="oldfile" value="${param.oldfile}">	  

  <TABLE>
    <TR>
      <TD colspan="2" style="text-align: center; margin: auto;">
       <img src="./storage/${param.oldfile}">
      </TD>
    </TR>
    <TR>
      <TH>파일</TH>
      <TD>
       <input type="file" name="fnameMF" required="required">
      </TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>