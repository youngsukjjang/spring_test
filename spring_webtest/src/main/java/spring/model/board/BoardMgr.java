package spring.model.board;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.db.board.DBClose;
import spring.db.board.DBOpen;


@Service 
public class BoardMgr {
	@Autowired 	
	private  BoardDAO dao ;
	
	
		
		
		
		public boolean reply(BoardDTO dto)  {
			boolean flag = false;
		
			Map map = new HashMap();
			map.put("ref", dto.getRef());
			map.put("ansnum", dto.getAnsnum());
			
			try {
	
				
				dao.upAnsnum(map);
				flag = dao.insertReply(dto); 
				
	
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			
			
			}finally {
				
				
			}
			
		
			return flag;
		}
}
