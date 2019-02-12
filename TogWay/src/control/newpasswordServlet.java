package control;

import java.io.IOException;

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
 * Servlet implementation class newpasswordServlet
 */
@WebServlet("/newpasswd.do")
public class newpasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public newpasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("newpassword.jsp");
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("userid");
		String passwd = request.getParameter("passwd");
		String newpasswd = request.getParameter("newpasswd");
		String newrepasswd = request.getParameter("newrepasswd");
		
		MemberVo vo = new MemberVo();
		vo.setUserid(userid);
		vo.setPasswd(passwd);
		vo.setNewpasswd(newpasswd);
		vo.setNewrepasswd(newrepasswd);
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.newpasswdUpdate(vo);
		
		
		// #33] vo�� ��� �����Ϸ� ����
		dao.newpasswdUpdate(vo);	// DB�� ������ �����͵� �����Ų��
		
		// #35] �����Ϸ�! login.do(loginServlet.java/doGet) �� �̵���Ű��
			// ���ǿ� �α�������(loginUser)�� �����ֱ⶧���� main.jsp �� �Ѿ��
		response.sendRedirect("newpasswd.do?x="+ result);
	}

}
