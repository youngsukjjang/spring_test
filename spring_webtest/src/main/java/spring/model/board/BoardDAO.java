package spring.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;



@Repository
public class BoardDAO {
	
		@Autowired
		private SqlSessionTemplate mybatis;
	
		
		
		public void setMybatis(SqlSessionTemplate mybatis) {
			this.mybatis = mybatis;
		}
		public boolean create(BoardDTO dto){
			boolean flag = false;
			int cnt = mybatis.insert("board.create", dto);
			if(cnt>0)flag = true;
				
				return flag;		
		}
		
		
		
		
		
		public BoardDTO read(int num) {
			
		return mybatis.selectOne("board.read", num);
			
			
		}
		public boolean update(BoardDTO dto) {
			boolean flag = false;
			
			int cnt = mybatis.update("board.update", dto);
			
			if(cnt>0) flag = true;
			
			return flag;
			}

			public boolean delete(int num) {
				boolean flag = false;
				int cnt = mybatis.delete("board.delete", num);
				if(cnt>0)flag=true;

			return flag;
			}
			
			public List<BoardDTO> getlist(Map map){
				
			return mybatis.selectList("board.list", map);
				
			}
			
			
			
			

			public List<BoardDTO> list(Map map) {
			List<BoardDTO> list = new ArrayList<BoardDTO>();

			Connection con = DBOpen.open();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardDTO dto = null;

			String col = (String)map.get("col");
			String word = (String)map.get("word");
			int sno = (Integer)map.get("sno");
			int eno = (Integer)map.get("eno");	

			StringBuffer sql = new StringBuffer();
			sql.append(" select num, name, subject, regdate, count, indent, r ");
			sql.append(" from( ");
			sql.append(" 	select num, name, subject, regdate, count, indent, rownum r ");
			sql.append(" 	from( ");
			sql.append(" 	select num, name, subject, count, indent, ");
			sql.append(" 	       to_char(regdate, 'yyyy-mm-dd') regdate ");
			sql.append(" 	from board ");
			if(word.length() > 0 && col.equals("subject_content")) {
				sql.append(" WHERE (subject LIKE '%" + word + "%'");
				sql.append(" OR content LIKE '%" + word + "%') ");
			
			}else if (word.length() > 0) {
			sql.append(" 	where " + col + " like '%" + word + "%' ");
			}
			sql.append(" 	order by ref desc, ansnum ASC ");
			sql.append(" 	)	 ");
			sql.append(" ) ");
			sql.append(" where r>=? and r<=? ");


			try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sno);
			pstmt.setInt(2, eno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			dto = new BoardDTO();
			dto.setNum(rs.getInt("num"));
			dto.setName(rs.getString("name"));
			dto.setSubject(rs.getString("subject"));
			dto.setCount(rs.getInt("count"));
			dto.setRegdate(rs.getString("regdate"));
			dto.setIndent(rs.getInt("indent"));

			list.add(dto);
			}
			} catch (Exception e) {
			e.printStackTrace();
			} finally {
			DBClose.close(rs, pstmt, con);
			}	

			return list;
			}

			public int total(Map map) {
				
				return mybatis.selectOne("board.total",map);
				
			}

			public void upCount(int num) {
				mybatis.update("board.upCount", num);
				
			}

			public boolean replyCreate(BoardDTO dto, Connection con)throws Exception {
			boolean flag = false;

			
			PreparedStatement pstmt = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" insert into board(num, name, subject, content, ");
			sql.append(" 	regdate, passwd, ip, filename, filesize, ");
			sql.append(" 	ref, indent, ansnum, refnum) ");
			sql.append(" values((select nvl(max(num),0) + 1 from board), ?, ?, ?, ");
			sql.append(" 	sysdate, ?, ?, ?, ?, ?, ?, ?, ?) ");

			try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setString(5, dto.getIp());
			pstmt.setString(6, dto.getFilename());
			pstmt.setInt(7, dto.getFilesize());
			pstmt.setInt(8, dto.getRef());
			pstmt.setInt(9, dto.getIndent()+1);
			pstmt.setInt(10, dto.getAnsnum()+1);
			pstmt.setInt(11, dto.getNum());

			if (pstmt.executeUpdate() > 0) {
			flag = true;
			}	
			
			}finally {
				
			DBClose.close(pstmt);
			}	

			return flag;
			}

			// 부모의 num을 가지고 읽어들이고, 제목은 수정할 수 없음
			// num은 replyCreate의 refnum을 부모의 num으로 입력해주기 위해 읽어들이고,
			// ref는 부모글과 같게 하기위해,
			// indent와 ansnum은 부모글보다 1씩 크게 하기 위해 읽어온다
			public BoardDTO replyRead(int num) {
			

			return mybatis.selectOne("board.replyRead", num);
			}

			// 부모글의 ref와 일치하는 것들 중 (=답변글들 중)
			// ansnum이 부모글보다 큰 글이 있으면 1을 더함
			// 왜냐면, 이렇게 해야 이전에 써졌던 답변글의 ansnum이 1 증가
			public void upAnsnum(Map map) {
			
					mybatis.update("board.upAnsnum", map);
				
			}

		
			public boolean passwdCheck(Map map) {
				boolean flag = false;
				int cnt = mybatis.selectOne("board.passwdCheck", map);
				if(cnt>0)flag=true;
				
			
				return flag;
			}
			public boolean checkRefnum(int num) {
					boolean flag = false;
					int cnt = mybatis.selectOne("board.checkRefnum", num);
					if(cnt>0)flag=true;
					return flag;
				
			}

		public boolean insertReply(BoardDTO dto) {
			boolean flag = false;
			int cnt = mybatis.insert("board.insertReply", dto);
			if(cnt>0)flag = true;
			return flag;
		}
		
		}
