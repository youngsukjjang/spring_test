<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
 
                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;
 
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }
 
                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;
 
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
    }
</script>


<style>
* {
	font-family: gulim;
	font-size: 20px;
}
</style> 

<script type="text/javascript">
function inputCheck(f) {
	if (f.id.value == "") {
		alert("아이디를 입력하세요");
		f.id.focus();
		
		return false;
	}
	if (f.passwd.value == "") {
		alert("패스워드를 입력하세요");
		f.passwd.focus();
		
		return false;
	}
	if (f.repasswd.value == "") {
		alert("패스워드를 확인하세요");
		f.repasswd.focus();
		
		return false;
	}
	if (f.passwd.value != f.repasswd.value) {
		alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
		f.passwd.focus();
		
		return false;
	}	
	if (f.mname.value == "") {
		alert("이름을 입력하세요");
		f.mname.focus();
		
		return false;
	}
	if (f.email.value == "") {
		alert("이메일을 입력하세요");
		f.email.focus();
		
		return false;
	}
	if (f.job.value == "0") {
		alert("직업을 선택하세요");
		f.job.focus();
		
		return false;
	}
}

$.ajaxSetup({
    error: function(jqXHR, exception) {
        if (jqXHR.status === 0) {
            alert('Not connect.\n Verify Network.');
        }
        else if (jqXHR.status == 400) {
            alert('Server understood the request, but request content was invalid. [400]');
        }
        else if (jqXHR.status == 401) {
            alert('Unauthorized access. [401]');
        }
        else if (jqXHR.status == 403) {
            alert('Forbidden resource can not be accessed. [403]');
        }
        else if (jqXHR.status == 404) {
            alert('Requested page not found. [404]');
        }
        else if (jqXHR.status == 500) {
            alert('Internal server error. [500]');
        }
        else if (jqXHR.status == 503) {
            alert('Service unavailable. [503]');
        }
        else if (exception === 'parsererror') {
            alert('Requested JSON parse failed. [Failed]');
        }
        else if (exception === 'timeout') {
            alert('Time out error. [Timeout]');
        }
        else if (exception === 'abort') {
            alert('Ajax request aborted. [Aborted]');
        }
        else {
            alert('Uncaught Error.n' + jqXHR.responseText);
        }
    }
});

function idCheck(id) {
	if (id == "") {
		alert("아이디를 입력해주세요");
		document.frm.id.focus();
	}
	else {
		var url = "idCheck";
		
		$.ajax({
			url : url,
			type : "GET",
			dataType : "text",
			data : {"id" : id},
			success : function(data) {
				$("#idcheck").text(data.trim()).css("color","red");
			} 			
		});
	}
}

function emailCheck(email) {
	if (email == "") {
		alert("이메일을 입력해주세요");
		document.frm.email.focus();
	}
	else {
		var url = "emailCheck";
		
		$.ajax({
			url : url,
			type : "GET",
			dataType : "text",
			data : {"email" : email},
			success : function(data) {
				$("#emailcheck").text(data.trim()).css("color","red");
			}
		});
	}		
}
</script>

</head> 
<!-- *********************************************** -->
<body>
 
<div class="container"><span class="glyphicon glyphicon-pencil"></span>
<h2>회원가입</h2>
 
<FORM name='frm' method='POST' action='./createProc'
	  enctype="multipart/form-data" onsubmit="return inputCheck(this)">
  <TABLE class="table table-bordered">
    <TR>
      <TH>사진</TH>
      <TD>
       <input type="file" name="fnameMF" accept=".jpg,.png,.gif">
      </TD>
      <TD>사진은 JPG,PNG,GIF파일만 올려주세요</TD>
    </TR>
    <TR>
      <TH>*아이디</TH>
      <TD>
       <input type="text" name="id">
       <input type="button" value="중복확인" onclick="idCheck(document.frm.id.value)">
       <div id="idcheck"></div>
      </TD>
      <TD>중복 확인을 해 주세요</TD>
    </TR>
    <TR>
      <TH>*패스워드</TH>
      <TD>
       <input type="password" name="passwd">
      </TD>
      <TD>무조건 작성해야 합니다.</TD>
    </TR>
    <TR>
      <TH>*패스워드 확인</TH>
      <TD>
       <input type="password" name="repasswd">
      </TD>
      <TD>패스워드를 다시 한 번 입력해주세요.</TD>
    </TR>
    <TR>
      <TH>*이름</TH>
      <TD>
       <input type="text" name="mname">
      </TD>
      <TD>무조건 작성해야 합니다.</TD>
    </TR>
    <TR>
      <TH>전화번호</TH>
      <TD>
       <input type="text" name="tel">
      </TD>
      <TD></TD>
    </TR>
    <TR>
      <TH>*이메일</TH>
      <TD>
       <input type="text" name="email">
       <input type="button" value="중복확인" onclick="emailCheck(document.frm.email.value)">
       <div id="emailcheck"></div>
      </TD>
      <TD>무조건 작성해야 합니다.</TD>
    </TR>
    <TR>
      <TH>우편번호</TH>
      <TD>
       <input type="text" name="zipcode" size="7" id="sample6_postcode" placeholder="우편번호">
       <button type="button" onclick="sample6_execDaumPostcode()">우편번호 검색</button>
      </TD>
      <TD></TD>
    </TR>
    <TR>
      <TH>주소</TH>
      <TD>
       <input type="text" name="address1" size="45" id="sample6_address" placeholder="주소">
       <br>
       <input type="text" name="address2" size="45" id="sample6_address2" placeholder="상세주소">
      </TD>
      <TD></TD>
    </TR>
    <TR>
      <TH>*직업</TH>
      <TD>
       <select name="job">
        <option value="0">선택하세요</option>
        <option value="A01">학생</option>
        <option value="A02">회사원</option>
        <option value="A03">농부</option>
        <option value="A04">어부</option>
        <option value="A05">군인</option>
        <option value="A06">자영업</option>
        <option value="A07">무직</option>
       </select>
      </TD>
      <TD>무조건 선택해야 합니다.</TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='회원가입'>
    <input type='reset' value='재입력'>
  </DIV>
</FORM>

</div>
 
</body>
<!-- *********************************************** -->
</html>