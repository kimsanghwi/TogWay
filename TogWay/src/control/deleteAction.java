package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVo;

/**
 * Servlet implementation class deleteAction
 */
@WebServlet("/delete.do")
public class deleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public deleteAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		request.setAttribute("userid", userid);
		
		RequestDispatcher dis = request.getRequestDispatcher("deleteForm.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		String passwd = request.getParameter("passwd");
		
		MemberVo vo = new MemberVo();
		vo.setUserid(userid);
		vo.setPasswd(passwd);
		
		MemberDAO manager = MemberDAO.getInstance();
		int result = manager.deleteMember(vo);
		String returnPage = null;
		
		response.setCharacterEncoding("utf-8");	
		// ���� ������ ���� : UTF-8 ���ڿ��� �����Ұž�. ��� �˷��ְ� �����Ѵ�
		response.setContentType("text/html;charset=utf-8");
		// �����ϱ�
		PrintWriter out = response.getWriter();
		
		if(result == 1) {	// 1 : 성공, 0 : 비밀번호 불일치, -1 : 시스템 에러 발생
			out.println("<script>");
	         out.println("alert('회원탈퇴 성공했습니다.');");
	         out.println("location.href='login.jsp';");	// 여기는 메인게시판 이동으로 바꿔주면 될듯
	         out.println("</script>");
		} else if(result == 0) {
			out.println("<script>");
	         out.println("alert('비밀번호가 맞지 않습니다.');");
	         out.println("location.href='delete.do';");
	         out.println("</script>");
		} else {
			out.println("<script>");
	         out.println("alert('시스템 에러 발생!');");
	         out.println("location.href='login.jsp';");
	         out.println("</script>");
		}
		/*RequestDispatcher dis = request.getRequestDispatcher(returnPage);	// 결과창 보여줄 페이지 입력 했음
		dis.forward(request, response);*/
	}

}
