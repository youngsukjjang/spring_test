package spring.model.bbs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;


@Service
public class BbsService {
	
	
	@Autowired
	private BbsDAO dao;
	
	
	public boolean reply(BbsDTO dto) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		
		try {
			con.setAutoCommit(false);
			
			Map map = new HashMap();
			map.put("grpno", dto.getGrpno());
			map.put("ansnum", dto.getAnsnum());
			
			dao.upAnsnum(map, con);
			flag = dao.replyCreate(dto, con);
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBClose.close(con);
		}
		
		return flag;
	}
}
