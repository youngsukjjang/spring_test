<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String id = (String)request.getAttribute("id");

if(id!=null){
	out.print("찾으시는 id는 "+id+" 입니다.");
}else{
	out.print("잘못된 정보를 입력하였습니다.");
}

 
%>    
