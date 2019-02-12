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







public class TogWayDBBean {
	
	private static TogWayDBBean instance = new TogWayDBBean();
	// 데이터 처리빈의 객체를 싱글톤으로 선언하여 1개의 객체만 사용(생성) 할 수 있도록 한다.
	public static TogWayDBBean getinstance() {
		//싱글톤으로 선언된 객체를 사용하기 위해서 getinstance를 선언하여 객체에 접근 할 수 있게 한다.
		return instance;
		// 위에 선언된 instance객체를 리턴하여 사용한다.
	}
	
	private TogWayDBBean() {}
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
	
	public int getAllCount(int div_num) 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		int count = 0;
		
try {	
		conn = getConnection();
		
		String sql ="select count(*) from board where div_num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, div_num);
		System.out.println(div_num);
		System.out.println(count);
	
	
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
	
	public List<TogWayBoardDataBean> selectAllBoards(int startRow, int endRow, int div_num) {
		
	
		List<TogWayBoardDataBean> list = new ArrayList<TogWayBoardDataBean>();
		String sql = "select * from board where div_num = ? order by ref desc, re_step asc limit ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, div_num);
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
			
			list.add(vo);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
	        if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
	        if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		return list;
	}
				
	public List<Comment> selectBoardCommentList(String ref) {
		List<Comment> result = new ArrayList<Comment>();
		String sql = "select * from comment where ref=? ORDER BY num";

		Comment vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ref);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new Comment();
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setContent(rs.getString("content"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setRef(rs.getInt("ref"));
				result.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return result;
	}

/////////////////////////////////////////검색//////////////////////////////////////////////////////////////////////////////////
	
	
	
	
				
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
			//게시글 추가 
	
	
	@SuppressWarnings("resource")
	public int insertArticle(TogWayBoardDataBean vo){
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		int number=0;//board테이블에 들어갈 글번호
        String sql="";
        
        //43~46라인은 댓글이 가진 정보
		int num=vo.getNum();//제목글의 글번호
		int ref=vo.getRef();//제목글의 그룹화 아이디
		int re_step=vo.getRe_step();//그룹내의 글의순서
		int re_level=vo.getRe_level();//글제목의 들여쓰기
		int div_num=vo.getDiv_num();
        try {
            conn = getConnection();
            //51~57라인은 현재 board테이블에 레코드의 유무 판단과 글번호 결정
            pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if (rs.next())//기존에 레코드가 존재(데이터베이스에 )
		      number=rs.getInt(1)+1;//다음글 번호는 가장큰 글번호+1
		    else//첫번째 글
		      number=1;
			
		   //60~72라인은 제목글과 댓글간의 순서를 결정
		    if (num!=0){//댓글 - 제목글의 글번호 가짐
		      sql="update board set re_step = re_step+1 where ref= ? and re_step> ?";
              pstmt = conn.prepareStatement(sql);
              pstmt.setInt(1, ref);
			  pstmt.setInt(2, re_step);
			  pstmt.executeUpdate();
			  re_step=re_step+1;
			  re_level=re_level+1;
		    }else{//제목글 - 글번호 없음
		  	  ref=number;
			  re_step=0;
			  re_level=0;
		    }	 
            // 쿼리를 작성 :board테이블에 새로운 레코드 추가
            sql = "insert into board(writer,subject,content,passwd,reg_date,";
		    sql+="ref,re_step,re_level,div_num) values(?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getWriter());
            pstmt.setString(2, vo.getSubject());
            pstmt.setString(3, vo.getContent());
            pstmt.setString(4, vo.getPasswd());
			pstmt.setTimestamp(5, vo.getReg_date());
            pstmt.setInt(6, ref);
            pstmt.setInt(7, re_step);
            pstmt.setInt(8, re_level);
            pstmt.setInt(9, div_num);
            pstmt.executeUpdate();
            x = 1; //레코드 추가 성공
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
        return x;
    }

	
	
	
	
	
	
	
	
	
	public void updateReadCount(String num) {
	      String sql="update board set readcount=readcount+1 where num =?";
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	        
	         pstmt.setString(1, num);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	    	  	if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
	            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
	            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
	      }
	   }

	
	public TogWayBoardDataBean selectOneBoardBynum(String num) {
	      String sql = "select * from board where num=?";
	      
	      TogWayBoardDataBean vo = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, num);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            vo = new TogWayBoardDataBean();
	            vo.setDiv_num(rs.getInt("div_num"));
	            vo.setNum(rs.getInt("num"));
	            vo.setWriter(rs.getString("writer"));
	            vo.setPasswd(rs.getString("passwd"));
	            vo.setSubject(rs.getString("subject"));
	            vo.setContent(rs.getString("content"));
	            vo.setReadcount(rs.getInt("readcount"));
	            vo.setReg_date(rs.getTimestamp("reg_date"));
	   
	         }
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	    	  	if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
	            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
	            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
	      }
		return vo;
	      
	}

	
	
	public void updateBoard(TogWayBoardDataBean vo) {
		String sql = "update board set writer=?, passwd=?, subject=?, content=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, vo.getNum());
			pstmt.executeUpdate();
			

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
	}
	
	public void deleteBoard(String num) {
		
		String sql="delete from board where num =?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
		}
		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	} 



	



	   
