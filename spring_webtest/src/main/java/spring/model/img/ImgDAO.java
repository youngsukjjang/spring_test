package spring.model.img;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ImgDAO {
	
	
	@Autowired
	private SqlSessionTemplate mybatis;
	

	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	
	
	
	
	
	
	public boolean checkRefnum(int no){
		boolean flag = false;
		int cnt = mybatis.selectOne("img.checkRefnum", no);
		if(cnt>0)flag=true;
		return flag;
	}
	
	public ImgDTO replyRead(int no){
		
		return mybatis.selectOne("img.replyRead", no);
	}
	
	
	
	public boolean replyCreate(ImgDTO dto){
		boolean flag = false;
		int cnt = mybatis.insert("img.replyCreate", dto);
		if(cnt>0)flag=true;
		return flag;
	}
	
	
	
	public void upAnsnum(Map map) {
		
		mybatis.update("img.upAnsnum", map);
	}
	
	
	
	public void upViewcnt(int no){
		mybatis.update("img.upViewcnt", no);
	}
	

	
	public boolean pwChek(int no, String passwd) {
		boolean flag=false;
		int cnt = mybatis.selectOne("img.pwChek", no);
		if(cnt>0)flag=true;
		return flag;
	}
	
	public boolean passCheck(Map map){

		boolean flag = false;
		int cnt = mybatis.selectOne("img.passCheck", map);
		if(cnt>0)flag=true;
		return flag;

	}
	
	
	
	public int total(Map map){
		
		return mybatis.selectOne("img.total",map);
	}
	
	

public List<ImgDTO> list(Map map){
		
		
		return mybatis.selectList("img.list", map);
	}
	
public boolean delete(int no){

	boolean flag = false;
	int cnt = mybatis.delete("img.delete", no);
	if(cnt>0)flag=true;
	return flag;

}

public boolean passCheck2(Map map) {
	boolean flag= false;
	int cnt = mybatis.selectOne("img.passCheck2", map);
	if(cnt>0)flag=true;
	return flag;
}



public boolean upup(ImgDTO dto) {
	boolean flag = false;
	int cnt = mybatis.update("img.upup", dto);
	if(cnt>0)flag=true;
	
	return flag;
}
	
public boolean updateFile(Map map) {
	boolean flag = false;
	int cnt = mybatis.update("img.updateFile", map);
	if(cnt>0)flag=true;
	
	return flag;
}

//update
public boolean update(ImgDTO dto){
	boolean flag = false;
	int cnt = mybatis.update("img.update", dto);
	if(cnt>0)flag=true;
	return flag;
}
	
	public ImgDTO read (int no){
		
		return mybatis.selectOne("img.read", no);
	}
	
	
	
	
	public List readImg(int no) {
		List list = new ArrayList();
		Map map = mybatis.selectOne("img.readImg", no);

		int[] No = {
				((BigDecimal)map.get("PRE_NO2")).intValue(),
				((BigDecimal)map.get("PRE_NO1")).intValue(),
				((BigDecimal)map.get("NO")).intValue(),
				((BigDecimal)map.get("NEX_NO1")).intValue(),
				((BigDecimal)map.get("NEX_NO2")).intValue(),
				};

		String[] Fname = { 
		(String)map.get("PRE_FNAME2"),
		(String)map.get("PRE_FNAME1"),
		(String)map.get("FNAME"),
		(String)map.get("NEX_FNAME1"),
		(String)map.get("NEX_FNAME2"),
		};

		list.add(No);
		list.add(Fname);

		return list;
		}
	
	public boolean create(ImgDTO dto){
		boolean flag = false;
		int cnt = mybatis.insert("img.create", dto);
		if(cnt>0)flag=true;

		return flag;
	}
	}
