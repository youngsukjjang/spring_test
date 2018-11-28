package spring.model.team;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TeamTest {

public static void main(String[] args) {

	Resource resource = new ClassPathResource("daoTest.xml");
	
	BeanFactory factory = new XmlBeanFactory(resource);
	
	TeamDAO dao = (TeamDAO)factory.getBean("team");
	

	
	//create(dao);
	//read(dao);
	//update(dao);
//delete(dao);
list(dao); 
	//total(dao); 


	//upAnsnum(dao);
////replyCreate(dao);
 //upAnsnum(dao)과 replyCreate(dao)는 동시 실행
//replyRead(dao);
//checkRef(dao);
	//reply(dao);
	//updateViewcnt(dao);

}	




private static void updateViewcnt(TeamDAO dao) {
	
	
}




private static void reply(TeamDAO dao) {
	TeamDTO dto = dao.replyRead(1);

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




private static void create(TeamDAO dao) {
TeamDTO dto = new TeamDTO();

dto.setName("한영석");
dto.setPhone("010-5656-8887");
dto.setHobby("게임");
dto.setSkills("JAVA");
dto.setGender("남자");
dto.setZipcode("12345");
dto.setAddress("서울시 종로구 관철동");
dto.setAddress2("젊음의거리");

if (dao.create(dto)) {
p("등록 성공");
}
else {
p("등록 실패");
}	

}

private static void read(TeamDAO dao) {


private static void update(TeamDAO dao) {
TeamDTO dto = new TeamDTO();
dto.setNo(3);
dto.setPhone("010-5656-8887");
dto.setHobby("게임");
dto.setSkills("JAVA");
dto.setZipcode("12345");
dto.setAddress("서울시 종로구 관철동");
dto.setAddress2("젊음의거리");
if (dao.update(dto)) {
p("변경 성공");
}
else {
p("변경 실패");
}	
}

private static void delete(TeamDAO dao) {
int num = 10;

if (dao.delete(num)) {
p("삭제 성공");
}
else {
p("삭제 실패");
}	
}

private static void list(TeamDAO dao) {
Map map = new HashMap();
map.put("col", "skill");
map.put("word", "JAVA");
map.put("sno", 1);
map.put("eno", 10);

List<TeamDTO> list = dao.list(map);

Iterator<TeamDTO> iter = list.iterator();

while(iter.hasNext()) {
	TeamDTO dto = iter.next();
	p(dto);
	p("-------------------------");
}	
}

private static void total(TeamDAO dao) {
Map map = new HashMap();
map.put("col", "skill");
map.put("word", "JAVA");



System.out.println(dao.total(map));	
}



private static void checkRef(TeamDAO dao) {

if (dao.checkRef(1204)) {
p("해당 글의 부모글이 존재합니다. 삭제 가능합니다.");
}
else {
p("해당 글이 부모글입니다. 삭제 불가능합니다.");
}	
}

private static void replyCreate(TeamDAO dao) {
TeamDTO dto = dao.replyRead(1);
dto.setTitle("메모제목3");
dto.setContent("메모내용3");



if (dao.replyCreate(dto)) {
p("답변 등록 성공");
}
else {
p("답변 등록 실패");
}

}

private static void replyRead(TeamDAO dao) {

TeamDTO dto = dao.replyRead(1);

p("번호:" +dto.getno());	
p("제목:" +dto.getTitle());	
p("indent:" +dto.getIndent());	
p("ansnum:" +dto.getAnsnum());	
p("grpno:" +dto.getGrpno());	
}

private static void upAnsnum(TeamDAO dao) {
Map map = new HashMap();
map.put("grpno", "");
map.put("ansnum", "");
 
//dao.upAnsnum(map);	
}



private static void p(TeamDTO dto) {
p("번호 : " + dto.getNo());
p("이름 : " + dto.getName());
p("성별 : " + dto.getGender());
p("취미 : " + dto.getHobby());
p("직업 : " + dto.getSkills());
p("폰번호 : " + dto.getPhone());
p("숫자 : " + dto.getZipcode());
p("주소 : " + dto.getAddress());
p("상세주소 : " + dto.getAddress2());

}

private static void p(String string) {
System.out.println(string);	
}

}
