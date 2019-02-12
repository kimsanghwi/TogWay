package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class MemberDAO {
	
	private static MemberDAO Instance = new MemberDAO();
	// static 메서드를 통해서만 생성할 수 있도록 만들어 놓자
	public static MemberDAO getInstance() {
		
		return Instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
		
		return ds.getConnection();
	}
	
	public int insertMember(MemberVo vo) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();	// DB와 연결
			pstmt = conn.prepareStatement(sql); // pstmt객체에 대입
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getTel());
			pstmt.setTimestamp(6, new Timestamp((new Date()).getTime()));
			
			result = pstmt.executeUpdate();	// 대입이 성공하면 1, 실패하면 0
			
		} catch(Exception e) {
			
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int confirm(String userid) {		// 아이디의 중복여부 확인
		String sql = "select userid from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		}catch(Exception e){
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		// IdCheck서블릿에 1, -1 둘중에 하나를 들고 가자
		return result;
	}
	
	public int deleteMember(MemberVo vo) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select userid from member where userid=? and passwd=?");
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			rs = pstmt.executeQuery();
			if(rs.next()) {	// 존재하는 사용자
				pstmt.close();
				pstmt = conn.prepareStatement("delete from member where userid=?");
				pstmt.setString(1, vo.getUserid());
				pstmt.executeUpdate();
				result = 1;
			} else {
				// 비밀번호가 불일치
				result = 0;
			}
		} catch(Exception e) {
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// DB에서 회원 유무 확인하는 메서드
	public int userCheck(String userid, String passwd) {
		int result = -1;
		String sql = "select passwd from member where userid=?";
			// membership ���̺��� userID��  ?�� ��� �����͸� �����´�
		Connection conn = null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();	// DB 연결지점 확보
			pstmt = conn.prepareStatement(sql);	// DB 명령 세팅	
												// sql���� ������ �� �ִ� pstmt ��ü�� ����
			pstmt.setString(1, userid);
								// sql�� ���ε� ���� �ϼ�
			
			rs = pstmt.executeQuery();	//rs에 첫행에 첫 갈림에 뭘 갖고 왔을까? password
								// sql�� ������ ����. rs���� ������� ����
			if(rs.next()) {
				if(rs.getString("passwd") != null && rs.getString("passwd").equals(passwd)) {
					result = 1; // 정상로그인
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch(Exception e) {
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public MemberVo getMember(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from member where userid=?";
		ResultSet rs = null;
		MemberVo vo = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberVo();
				vo.setName(rs.getString("name"));
				vo.setUserid(rs.getString("userid"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				
				rs.close();
				pstmt.close();
				conn.close();
			}else {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	// int updateMember(MemberVo vo) : 회원정보 변경
	   // 비밀번호 변경은 하지 않고, 이름, 주소, 전화번호만 변경
	   // 비밀번호 : 새로운 비밀번호를 입력을 받고, 이전비밀번호와 데이터베이스에 저장된 비밀번호를 비교
	   // 새로운 비밀번호를 암호화해서 저장
	   public int updateMember(MemberVo vo) {
	      // 사용자가 존재하는지 먼저 검사 : id와 비밀번호가 같은지를 비교
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = -1;   // 검색 결과를 저장 (아이디가 존재하지 않음)
	    
	      // 데이터베이스에서 암호화된 passwd를 획득
	      try {
	         conn = getConnection();   // 커넥션을 구한다.
	         pstmt = conn.prepareStatement("select passwd from member where userid=?"); // 비밀번호를 검색
	         pstmt.setString(1, vo.getUserid());
	         rs = pstmt.executeQuery();
	         if(rs.next()) {   // id가 존재
	            // 비밀번호를 비교(사용자로부터 입력한 값과 데이터베이스에 저장된 값)
	            String dbPass = rs.getString(1);   // DB에 저장된 비밀번호
	            String orgPass = vo.getPasswd();   // 사용자로부터 입력된 비밀번호
	            // 비밀번호를 비교하는 메소드 : BCrypt.checkpw()
	            if(dbPass.equals(orgPass)) {   // 비밀번호가 같음
	               // 회원정보를 수정 : 이름, 주소, 전화번호
	               pstmt.close();	// pstmt를 새로 시작하려고 작성함 
	               pstmt = conn.prepareStatement("update member set email=?, tel=?  where userid=?");
	               pstmt.setString(1, vo.getEmail());
	               pstmt.setString(2, vo.getTel());
	               pstmt.setString(3, vo.getUserid());
	               result = pstmt.executeUpdate();
	               result = 1;
	            } else {   // 비밀번호가 틀림
	               result = 0;   // 회원정보 수정이 실패
	            }
	         } /* else {   // id가 존재하지 않음
	            x= -1;   // 초기치로 x = -1;을 설정해 주었으므로 생략
	         } */
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	    	  try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					if(rs != null) rs.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
	      }
	      return result;
	   }
	   
	   
	   
	   public int newpasswdUpdate(MemberVo vo) {
		      // 사용자가 존재하는지 먼저 검사 : id와 비밀번호가 같은지를 비교
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      int x = -1;   // 검색 결과를 저장 (아이디가 존재하지 않음)
		      
		      // 데이터베이스에서 암호화된 passwd를 획득
		      try {
		         conn = getConnection();   // 커넥션을 구한다.
		         pstmt = conn.prepareStatement("select passwd from member where userid=?"); // 비밀번호를 검색
		         pstmt.setString(1, vo.getUserid());
		         rs = pstmt.executeQuery();
		         if(rs.next()) {   // id가 존재
		            // 비밀번호를 비교(사용자로부터 입력한 값과 데이터베이스에 저장된 값)
		            String dbPass = rs.getString(1);   // DB에 저장된 비밀번호
		            String orgPass = vo.getPasswd();   // 사용자로부터 입력된 비밀번호
		            // 비밀번호를 비교하는 메소드 : BCrypt.checkpw()
		            if(dbPass.equals(orgPass)) {   // 비밀번호가 같음
		               // 회원정보를 수정 : 이름, 주소, 전화번호
		               pstmt.close();	// pstmt를 새로 시작하려고 작성함 
		               pstmt = conn.prepareStatement("update member set passwd=?  where userid=?");
		               pstmt.setString(1, vo.getNewpasswd());
		               pstmt.setString(2, vo.getUserid());
		               x = pstmt.executeUpdate();
		               x = 1;
		            } else {   // 비밀번호가 틀림
		               x = 0;   // 회원정보 수정이 실패
		            }
		         } /* else {   // id가 존재하지 않음
		            x= -1;   // 초기치로 x = -1;을 설정해 주었으므로 생략
		         } */
		      } catch(Exception e) {
		         e.printStackTrace();
		      } finally {
		    	  try {
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
						if(rs != null) rs.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
		      }
		      return x;
		   }
	   
}