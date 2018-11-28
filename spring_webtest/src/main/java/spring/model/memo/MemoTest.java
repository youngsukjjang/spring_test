package spring.model.memo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemoTest {

public static void main(String[] args) {

	Resource resource = new ClassPathResource("daoTest.xml");
	
	BeanFactory factory = new XmlBeanFactory(resource);
	
	MemoDAO dao = (MemoDAO)factory.getBean("memo");
	

	
	//create(dao);
	//read(dao);
	//update(dao);
//delete(dao);
//ist(dao); 
	//total(dao); 


	//upAnsnum(dao);
////replyCreate(dao);
 //upAnsnum(dao)과 replyCreate(dao)는 동시 실행
//replyRead(dao);
//checkRef(dao);
	//reply(dao);
	//updateViewcnt(dao);

}	




private static void updateViewcnt(MemoDAO dao) {
	
	
}




private static void reply(MemoDAO dao) {
	MemoDTO dto = dao.replyRead(1);

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




private static void create(MemoDAO dao) {
MemoDTO dto = new MemoDTO();

dto.setTitle("메모제목1");
dto.setContent("메모내용1");

if (dao.create(dto)) {
p("등록 성공");
}
else {
p("등록 실패");
}	

}

private static void read(MemoDAO dao) {
int num = 1;

MemoDTO dto = dao.read(num);

p(dto);	
}

private static void update(MemoDAO dao) {
MemoDTO dto = dao.read(2);

dto.setTitle("제목변경");
dto.setContent("내용변경");

if (dao.update(dto)) {
p("변경 성공");
}
else {
p("변경 실패");
}	
}

private static void delete(MemoDAO dao) {
int num = 2;

if (dao.delete(num)) {
p("삭제 성공");
}
else {
p("삭제 실패");
}	
}

private static void list(MemoDAO dao) {
Map map = new HashMap();
map.put("col", "title");
map.put("word", "");
map.put("sno", 1);
map.put("eno", 10);

List<MemoDTO> list = dao.list(map);

Iterator<MemoDTO> iter = list.iterator();

while(iter.hasNext()) {
	MemoDTO dto = iter.next();
	p(dto);
	p("-------------------------");
}	
}

private static void total(MemoDAO dao) {
Map map = new HashMap();
map.put("col", "title");
map.put("word", "");
map.put("sno", 1);
map.put("eno", 10);

int total = dao.total(map);

p("전체레코드수:"+total);
}



private static void checkRef(MemoDAO dao) {

if (dao.checkRef(1204)) {
p("해당 글의 부모글이 존재합니다. 삭제 가능합니다.");
}
else {
p("해당 글이 부모글입니다. 삭제 불가능합니다.");
}	
}

private static void replyCreate(MemoDAO dao) {
MemoDTO dto = dao.replyRead(1);
dto.setTitle("메모제목3");
dto.setContent("메모내용3");



if (dao.replyCreate(dto)) {
p("답변 등록 성공");
}
else {
p("답변 등록 실패");
}

}

private static void replyRead(MemoDAO dao) {

MemoDTO dto = dao.replyRead(1);

p("번호:" +dto.getMemono());	
p("제목:" +dto.getTitle());	
p("indent:" +dto.getIndent());	
p("ansnum:" +dto.getAnsnum());	
p("grpno:" +dto.getGrpno());	
}

private static void upAnsnum(MemoDAO dao) {
Map map = new HashMap();
map.put("grpno", "");
map.put("ansnum", "");
 
//dao.upAnsnum(map);	
}



private static void p(MemoDTO dto) {
p("번호 : " + dto.getMemono());
p("내용 : " + dto.getContent());
p("제목 : " + dto.getTitle());
p("조회수 : " + dto.getViewcnt());
p("날짜 : " + dto.getWdate());

}

private static void p(String string) {
System.out.println(string);	
}

}
