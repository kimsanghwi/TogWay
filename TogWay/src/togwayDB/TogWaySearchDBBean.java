package togwayDB;

import java.sql.Connection;





import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.connector.Request;







public class TogWaySearchDBBean {
	
	private static TogWaySearchDBBean instance = new TogWaySearchDBBean();
	// 데이터 처리빈의 객체를 싱글톤으로 선언하여 1개의 객체만 사용(생성) 할 수 있도록 한다.
	public static TogWaySearchDBBean getinstance() {
		//싱글톤으로 선언된 객체를 사용하기 위해서 getinstance를 선언하여 객체에 접근 할 수 있게 한다.
		return instance;
		// 위에 선언된 instance객체를 리턴하여 사용한다.
	}
	
	private TogWaySearchDBBean() {}
	// (객체)자기자신을 사용 한다. 
	
	private Connection getConnection() throws Exception
	{
		Context initCtx= new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
		
		return ds.getConnection();
		
	}
	//커넥션풀로부터 Connection객체를 얻어냄 : DB연동빈의 쿼리문을 수행하는 메소드에서 사용
	
	
	// 게시글의 개수를 리턴 하는 메소드 
	
	public int SearchgetAllCount(String search) 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		int count = 0;
		
try {	
		conn = getConnection();
		System.out.println(search);
		String sql ="select count(*) from board where subject like '%?%'";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "search");
		
		System.out.println("개수" + count);
	
	
		//쿼리 실행을 준비한다 
		
		rs = pstmt.executeQuery();
		// 쿼리실형결과를 리턴 한다 
		
		
		if(rs.next()) //데이터가 있다면 
		{
			count = rs.getInt(1);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
        if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
        if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
	}
		
		return count;
		
	}
	
		 
	///////////////////////////////////////////////// 모든 게시글을 리턴해주는 메소드 ////////////////////////////////////////////////////////////////////////////////
	
	public List<TogWayBoardDataBean> selectAllSearchBoards(String search, int startRow, int endRow ) {
		
	
		List<TogWayBoardDataBean> searchlist = new ArrayList<TogWayBoardDataBean>();
		String sql = "select * from board where subject like ? order by ref desc, re_step asc limit ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         String searchVal = "%"+search+"%";
	         pstmt.setString(1, searchVal);
	         pstmt.setInt(2, startRow);
	         pstmt.setInt(3, endRow);
		
			
			rs = pstmt.executeQuery();
			
			
		
		while(rs.next()) {
		TogWayBoardDataBean vo = new TogWayBoardDataBean();
			vo.setNum(rs.getInt("num"));
			vo.setWriter(rs.getString("Writer"));
			vo.setSubject(rs.getString("subject"));
			vo.setContent(rs.getString("content"));
			vo.setPasswd(rs.getString("passwd"));
			vo.setReadcount(rs.getInt("readcount"));
			vo.setReg_date(rs.getTimestamp("reg_date"));
            vo.setRef(rs.getInt("ref"));
            vo.setRe_step(rs.getInt("re_step"));
			vo.setRe_level(rs.getInt("re_level"));
            vo.setContent(rs.getString("content"));
            vo.setDiv_num(rs.getInt("div_num"));
			
            searchlist.add(vo);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
	        if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
	        if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return searchlist;
	}
}

				
	



	   
