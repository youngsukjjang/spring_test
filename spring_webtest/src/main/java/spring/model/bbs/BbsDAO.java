package spring.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;

@Repository
public class BbsDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	public boolean checkRefnum(int bbsno) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(refnum) ");
		sql.append(" from bbs ");
		sql.append(" where refnum = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			if (rs.getInt(1) > 0) {
				flag = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}	
		
		return flag;
	}
	
	public boolean replyCreate(BbsDTO dto, Connection con) throws SQLException {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, passwd, ");
		sql.append(" wdate, grpno, indent, ansnum, refnum, filename, filesize) ");
		sql.append(" values( ");
		sql.append(" (select nvl(max(bbsno), 0) + 1 from bbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, dto.getGrpno());
				// 부모의 grpno 그대로
			pstmt.setInt(6, dto.getIndent()+1);
				// 부모의 indent + 1
			pstmt.setInt(7, dto.getAnsnum()+1);
				// 부모의 ansnum + 1
			pstmt.setInt(8, dto.getBbsno());
			pstmt.setString(9, dto.getFilename());
			pstmt.setInt(10, dto.getFilesize());
			
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} finally {
			DBClose.close(pstmt);
		}
		
		return flag;		
	}
	
	public void upAnsnum(Map map, Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		
		int grpno = (Integer) map.get("grpno");
		int ansnum = (Integer) map.get("ansnum");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update bbs ");
		sql.append(" set ansnum = ansnum + 1 ");
		sql.append(" where grpno = ? ");
		sql.append(" and ansnum > ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grpno);
			pstmt.setInt(2, ansnum);
			
			pstmt.executeUpdate();
		} finally {
			DBClose.close(pstmt);
		}
	}
	
	public BbsDTO replyRead(int bbsno) {
		BbsDTO dto = null;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select bbsno, title, grpno, indent, ansnum ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new BbsDTO();
				
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setTitle(rs.getString("title"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return dto;
	}
	
	public boolean create(BbsDTO dto) {
		boolean flag = false;
		int cnt = mybatis.insert("bbs.create", dto);
		if(cnt>0)flag = true;
		
		return flag;
	}
	
	
	public BbsDTO read(int bbsno) {
		
		
		return mybatis.selectOne("bbs.read", bbsno);
	}
	
	
	public boolean update(BbsDTO dto) {
		boolean flag = false;
		
		int cnt = mybatis.update("bbs.update", dto);
		
		if(cnt>0)flag = true;
		
		return flag;
	}
	
	public boolean delete(int bbsno) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from bbs ");
		sql.append(" where bbsno = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	
	public int total(Map map) {
		
		
		 return mybatis.selectOne("bbs.total", map);	
	}
	
	
	
	public List<BbsDTO> list(Map map) {
		
		
		return mybatis.selectList("bbs.list", map);	
	}
	
	
	
	public void upviewcnt(int bbsno) {
		mybatis.update("bbs.upViewcnt", bbsno);
	}
	
	public boolean passwdCheck(Map map) {
		boolean flag = false;
		int cnt = mybatis.selectOne("bbs.passwdCheck", map);
		if(cnt>0)flag=true;
		
		
		
		return flag;
	}
	
}
