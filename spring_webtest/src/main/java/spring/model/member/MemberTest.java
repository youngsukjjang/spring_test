package spring.model.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemberTest {

public static void main(String[] args) {

	Resource resource = new ClassPathResource("daoTest.xml");
	
	BeanFactory factory = new XmlBeanFactory(resource);
	
	MemberDAO dao = (MemberDAO)factory.getBean("member");
	

	
	//create(dao);
	//read(dao);
	//update(dao);
//delete(dao);
//list(dao); 
	//total(dao); 
	//duplicateID(dao);
	//duplicateEmail(dao);
	//getFname(dao);
	//loginCheck(dao);
	//getGrade(dao);
	//updateFile(dao);
	//updatePasswd(dao);
	//checkPasswd(dao);
	//getIdFind(dao);
	// getPwFind(dao);




	//upAnsnum(dao);
////replyCreate(dao);
 //upAnsnum(dao)과 replyCreate(dao)는 동시 실행
//replyRead(dao);
//checkRef(dao);
	//reply(dao);
	//updateViewcnt(dao);

}	







private static void //duplicateEmail(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //getFname(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //loginCheck(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //getGrade(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //updateFile(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //updatePasswd(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //checkPasswd(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void //getIdFind(MemberDAO dao) {
	// TODO Auto-generated method stub
	
private static void // getPwFind(MemberDAO dao) {
	// TODO Auto-generated method stub
	
}







}







}







}







}







}







}







}







}




private static void duplicateID(MemberDAO dao) {
	
	
	
}




private static void reply(MemberDAO dao) {
	MemberDTO dto = dao.replyRead(1);

	dto.setContent("내용1");
	
	
	Map map = new HashMap();
	map.put("grpno", dto.getGrpno());
	map.put("ansnum", dto.getAnsnum());
	
	dao.upAnsnum(map);
	if(dao.replyCreate(dto)) {
		p("성공");
	}else {
		p("실패");
	}
	
}




private static void create(MemberDAO dao) {
MemberDTO dto = new MemberDTO();

dto.setId("아이디");
dto.setEmail("이메일");
dto.setPasswd("패스워드");
dto.setFname("사진");
dto.setTel("전화번호");
dto.setZipcode("12345");
dto.setAddress1("주소");
dto.setAddress2("상세주소");
dto.setJob("직업");
dto.setMname("이름");
 
if (dao.create(dto)) {
p("등록 성공");
}
else {
p("등록 실패");
}	

}

private static void read(MemberDAO dao) {
String id = "admin";

MemberDTO dto = dao.read(id);

p(dto);	
}

private static void update(MemberDAO dao) {
MemberDTO dto = dao.read("admin");

dto.setId("아이디");
dto.setMname("이름");
dto.setPasswd("패스워드");
dto.setEmail("이메일");
dto.setZipcode("12345");
dto.setAddress1("주소");
dto.setAddress2("상세주소");

if (dao.update(dto)) {
p("변경 성공");
}
else {
p("변경 실패");
}	
}

private static void delete(MemberDAO dao) {
String id = "user3";

if (dao.delete(id)) {
p("삭제 성공");
}
else {
p("삭제 실패");
}	
}

private static void list(MemberDAO dao) {
Map map = new HashMap();
map.put("col", "");
map.put("word", "");
map.put("sno", 1);
map.put("eno", 10);

List<MemberDTO> list = dao.list(map);

Iterator<MemberDTO> iter = list.iterator();

while(iter.hasNext()) {
	MemberDTO dto = iter.next();
	p(dto);
	p("-------------------------");
}	
}

private static void total(MemberDAO dao) {
Map map = new HashMap();
map.put("col", "");
map.put("word", "");
map.put("sno", 1);
map.put("eno", 10);

int total = dao.total(map);

p("전체레코드수:"+total);
}



private static void checkRef(MemberDAO dao) {

if (dao.checkRef(1204)) {
p("해당 글의 부모글이 존재합니다. 삭제 가능합니다.");
}
else {
p("해당 글이 부모글입니다. 삭제 불가능합니다.");
}	
}

private static void replyCreate(MemberDAO dao) {
MemberDTO dto = dao.replyRead(1);
dto.setTitle("메모제목3");
dto.setContent("메모내용3");



if (dao.replyCreate(dto)) {
p("답변 등록 성공");
}
else {
p("답변 등록 실패");
}

}

private static void replyRead(MemberDAO dao) {

MemberDTO dto = dao.replyRead(1);

p("번호:" +dto.getMemberno());	
p("제목:" +dto.getTitle());	
p("indent:" +dto.getIndent());	
p("ansnum:" +dto.getAnsnum());	
p("grpno:" +dto.getGrpno());	
}

private static void upAnsnum(MemberDAO dao) {
Map map = new HashMap();
map.put("grpno", "");
map.put("ansnum", "");
 
//dao.upAnsnum(map);	
}



private static void p(MemberDTO dto) {
p("이름 : " + dto.getMname());
p("날짜 : " + dto.getMdate());
p("전화번호 : " + dto.getTel());
p("주소 : " + dto.getAddress1());
p("상세주소 : " + dto.getAddress2());
p("우편번호 : " + dto.getZipcode());
p("직업 : " + dto.getJob());
p("이메일 : " + dto.getEmail());

}

private static void p(String string) {
System.out.println(string);	
}

}
