package test;
 
public class Terminal {
  private String id;
  private String pw;
  private String msg;
 
  public void netConnect(){
 System.out.println("터미널 접속");
  }
  public void netDisConnect(){
 System.out.println("터미널 접속 해제");
  }
  public void logon(String id, String pw){
 this.id = id;
 this.pw = pw;
 System.out.println("로그인");
 
  }
  
  public void logoff(){
 System.out.println("로그오프");
  }
  
  public boolean isLogon(){
 if(id!=null && pw!=null){
 return true;
 }else{
 return false;
 }
  }
public void sendMessage(String msg) {
this.msg = msg;
 
}
public Object getReturnMsg() {
return msg;
}
  
 
}