package spring.model.bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BbsTest {

public static void main(String[] args) {

	Resource resource = new ClassPathResource("daoTest.xml");
	
	BeanFactory factory = new XmlBeanFactory(resource);
	
	BbsDAO dao = (BbsDAO)factory.getBean("dao");
	

	
	create(dao);
	read(dao);
	update(dao);
//delete(dao);
list(dao); 
	total(dao); 
//	upCount(dao);
//checkRefnum(dao);
	//upAnsnum(dao);
//	replyCreate(dao);
// upAnsnum(dao)과 replyCreate(dao)는 동시 실행
//replyRead(dao);
passwdCheck(dao);
	//reply(dao);
	upViewcnt(dao);
}	




private static void upViewcnt(BbsDAO dao) {
		
		
	
}




private static void reply(BbsDAO dao) {
	BbsDTO dto = dao.replyRead(1);
	dto.setName("가길동");
	dto.setContent("내용1");
	dto.setPasswd("123");
	dto.setIp("127.0.0.1");
	dto.setFilename("파일이름1");
	dto.setFilesize(50);
	
	Map map = new HashMap();
	map.put("ref", dto.getRef());
	map.put("ansnum", dto.getAnsnum());
	
	dao.upAnsnum(map);
	if(dao.insertReply(dto)) {
		p("성공");
	}else {
		p("실패");
	}
	
}




private static void create(BbsDAO dao) {
BbsDTO dto = dao.read(1);
dto.setWname("가길동");
dto.setTitle("제목1");
dto.setContent("내용1");
dto.setPasswd("123");
dto.setGrpno("래래");
dto.setIndent("랴랴");
dto.setFilename("파일이름1");
dto.setFilesize(50);

if (dao.create(dto)) {
p("등록 성공");
}
else {
p("등록 실패");
}	

}

private static void read(BbsDAO dao) {
int num = 1;

BbsDTO dto = dao.read(num);

p(dto);	
}

private static void update(BbsDAO dao) {
BbsDTO dto = dao.read(1);
dto.setWname("홍길순");
dto.setTitle("제목변경");
dto.setContent("내용변경");
dto.setFilename("test2/txt");
dto.setFilesize(50);

if (dao.update(dto)) {
p("변경 성공");
}
else {
p("변경 실패");
}	
}

private static void delete(BbsDAO dao) {
int num = 2;

if (dao.delete(num)) {
p("삭제 성공");
}
else {
p("삭제 실패");
}	
}

private static void list(BbsDAO dao) {
Map map = new HashMap();
map.put("col", "wname");
map.put("word", "");
map.put("sno", 1);
map.put("eno", 10);

List<BbsDTO> list = dao.list(map);

Iterator<BbsDTO> iter = list.iterator();

while(iter.hasNext()) {
	BbsDTO dto = iter.next();
	p(dto);
	p("-------------------------");
}	
}

private static void total(BbsDAO dao) {
Map map = new HashMap();
map.put("col", "wname");
map.put("word", "");

System.out.println(dao.total(map));	
}

private static void upCount(BbsDAO dao) {
int num = 1;
dao.upCount(num);	
}

private static void checkRefnum(BbsDAO dao) {

if (dao.checkRefnum(1)) {
p("해당 글의 부모글이 존재합니다. 삭제 가능합니다.");
}
else {
p("해당 글이 부모글입니다. 삭제 불가능합니다.");
}	
}

private static void replyCreate(BbsDAO dao) {
bbsDTO dto = dao.replyRead(1);
dto.setName("답변");
dto.setSubject("글1의 답1");
dto.setContent("답변내용");
dto.setPasswd("123");
dto.setIp("답변아이피");
dto.setFilename("답변파일이름");
dto.setFilesize(100);

//if (dao.replyCreate(dto)) {
//p("답변 등록 성공");
//}
//else {
//p("답변 등록 실패");
//}
//
}

private static void replyRead(BbsDAO dao) {

bbsDTO dto = dao.replyRead(1);

p("번호:" +dto.getNum());	
p("ref:" +dto.getRef());	
p("indent:" +dto.getIndent());	
p("ansnum:" +dto.getAnsnum());	
p("제목:" +dto.getSubject());	
}

private static void upAnsnum(BbsDAO dao) {
Map map = new HashMap();
map.put("ref", 1);
map.put("ansnum", 0);
 
//dao.upAnsnum(map);	
}

private static void passwdCheck(BbsDAO dao) {
Map map = new HashMap();
map.put("bbsno", "1");
map.put("passwd", "123");

if (dao.passwdCheck(map)) {
p("비밀번호가 일치합니다.");
}
else {
p("비밀번호 일치 안 함");
}

}

private static void p(BbsDTO dto) {
p("일련번호 : " + dto.getAnsnum());	
p("번호 : " + dto.getBbsno());
p("내용 : " + dto.getContent());
p("파일이름 : " + dto.getFilename());
p("파일크기 : " + dto.getFilesize());
p("조회수 : " + dto.getGrpno());
p("-----------read추가-------------");
p("내용 : " + dto.getIndent());
p("패스워드 : " + dto.getPasswd());
p("제목 : " + dto.getTitle());
p("조회수 : " + dto.getViewcnt());
p("날짜 : " + dto.getWdate());
p("개발자 : " + dto.getWname());

}

private static void p(String string) {
System.out.println(string);	
}

}
