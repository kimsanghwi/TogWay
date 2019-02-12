package togwayDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {
	private static CommentDAO dao = new CommentDAO();
	private CommentDAO() {}
	public static CommentDAO getInstance() {
		return dao;
	}
	private Connection conn;
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public Comment getOne(int ref) throws SQLException{
		Comment vo = new Comment();
		String sql = "select * from comment where ref = ? order by num";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ref);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		vo.setNum(rs.getInt("num"));
		vo.setWriter(rs.getString("writer"));
		vo.setPasswd(rs.getString("passwd"));
		vo.setContent(rs.getString("content"));
		vo.setReg_date(rs.getTimestamp("reg_date"));
		vo.setRef(rs.getInt("ref"));
		
		pstmt.close();
		return vo;
	}
	public void update(Comment vo) throws SQLException{
		String sql="update comment set writer=?,content=? where ref=? order by num";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getWriter());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(3, vo.getRef());
		pstmt.execute();
		pstmt.close();
	}
	public void delete(int ref) throws SQLException{
		String sql = "delete from comment where ref=? order by num";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ref);
		pstmt.execute();
		pstmt.close();
	}
	public void insert(Comment vo) throws SQLException{
		String sql="insert into comment(writer,passwd,content,ref) values(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getWriter());
		pstmt.setString(2, vo.getPasswd());
		pstmt.setString(3, vo.getContent());
		pstmt.setInt(4, vo.getRef());
		pstmt.execute();
		pstmt.close();
	}
	public int getCount(int num) throws SQLException{
		String sql="select count(*) from comment where ref=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,num);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		pstmt.close();
		return cnt;
	}
	public ArrayList<Comment> getList(int ref) throws SQLException{
		ArrayList<Comment> list = new ArrayList<>();
		
		String sql="select * from comment where ref = ? order by num desc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ref);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			do {
				Comment vo = new Comment();
				vo.setNum(rs.getInt("num"));
				vo.setRef(rs.getInt("ref"));
				vo.setWriter(rs.getString("writer"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setContent(rs.getString("content"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				list.add(vo);
			}while(rs.next());
		}
		pstmt.close();
		return list;
	}
}
