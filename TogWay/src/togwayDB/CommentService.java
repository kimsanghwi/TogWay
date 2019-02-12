package togwayDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService {
	private static CommentService service = new CommentService();
	private CommentService() {}
	public static CommentService getInstance() {
		return service;
	}
	public boolean update(Comment vo) {
		Connection conn = DBConn.getConnection();
		CommentDAO dao = CommentDAO.getInstance();
		dao.setConnection(conn);
		
		boolean flag = false;
		try {
			Comment res = dao.getOne(vo.getNum());
			if(res.getPasswd().equals(vo.getPasswd())) {
			dao.update(vo);
			flag = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBConn.close(conn);
		return flag;
	}
	public boolean delete(int num, String passwd) {
		Connection conn = DBConn.getConnection();
		CommentDAO dao = CommentDAO.getInstance();
		dao.setConnection(conn);
		
		boolean flag = false;
		try {
			Comment res = dao.getOne(num);
			if(res.getPasswd().equals(passwd)) {
				dao.delete(num);
				flag = true;
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBConn.close(conn);
		return flag;
	}
	
	public void insert(Comment vo) {
		Connection conn = DBConn.getConnection();
		CommentDAO dao = CommentDAO.getInstance();
		dao.setConnection(conn);
		
		try {
			dao.insert(vo);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBConn.close(conn);
	}
	
	
	public int getCount(int num) {
		Connection conn = DBConn.getConnection();
		CommentDAO dao = CommentDAO.getInstance();
		dao.setConnection(conn);
		
		int cnt = 0;
		try {
			cnt = dao.getCount(num);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBConn.close(conn);
		return cnt;
	}
	public ArrayList<Comment> getList(int ref){
		ArrayList<Comment> list = null;
		Connection conn = DBConn.getConnection();
		CommentDAO dao = CommentDAO.getInstance();
		dao.setConnection(conn);
		
		try {
			list = dao.getList(ref);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBConn.close(conn);
		return list;
	}
	

}
