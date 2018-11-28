<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
 
 
<!DOCTYPE html>   
<html>  
<head> 
<meta charset="UTF-8"> 
<title></title>  
<script type="text/javascript">
function inputCheck(f){
	
	if(f.title.value==""){
		alert("title을 입력해 주세요");
		f.focus();
		return false;
	}
	if(f.content.value==""){
		alert("내용을 입력해 주세요");
		f.focus();
		return false;
	}
	if(f.passwd.value==""){
		alert("비밀번호를 입력해 주세요");
		f.focus();
		return false;
	}
	
}
</script>
<style type="text/css"> 

</style> 
</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">수정</DIV>
 
<FORM name='frm' method='POST' action='./update'
      onsubmit="return inputCheck(this)"
      enctype="multipart/form-data">
      <input type="hidden" name="no" value="${dto.no}">
      <input type="hidden" name="oldfile" value="${dto.fname}">

  <table>
    <tr>
  		<td colspan="2" align="center">
   			<img src="./storage/${dto.fname}" width="200px" height="200px"> 
			
  		</td>
  	</tr>
    <TR>
      <TH>파일</TH>
      <TD><input type="file" name="fnameMF"></TD>
    </TR>
    
    <tr>
    <th>제목</th>
    <TD><input type="text" name="title" value="${dto.title}"></TD>
    </tr>
    <TR>
      <TH>content</TH>
      <TD><textarea rows="8" cols="40" name="content">${dto.content}</textarea></TD>
      
    </TR>
    
    <tr>
    <th>패스워드</th>
    <TD><input type="password" name="passwd"></TD>
    </tr>
 
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='정보수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM> 
 
</body>
<!-- *********************************************** -->
</html> 