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

@WebServlet("/memberUpdate.do")
public class memberUpdateServlet extends HttpServlet {
	
	// #28] ȸ�� ���� ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVo member = dao.getMember(userid);
		
		request.setAttribute("vo", member);
		
		RequestDispatcher dis = request.getRequestDispatcher("memberUpdate.jsp");
		dis.forward(request, response);
	}

	// #32] MemberUpdate���� ������ �����͸� ��� �Դ�. db�� ����ϹǷ� vo�� ������Ѵ�.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		MemberVo vo = new MemberVo();	// memberUpdate.jsp �������κ��� �Ѿ�� �����͵� "vo"��ü�� ����
		vo.setName(name);
		vo.setUserid(userid);
		vo.setPasswd(passwd);
		vo.setTel(tel);
		vo.setEmail(email);
		
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession session = request.getSession();
		int x = dao.updateMember(vo);
		
		
		// #33] vo�� ��� �����Ϸ� ����
		dao.updateMember(vo);	// DB�� ������ �����͵� �����Ų��
		
		// #35] �����Ϸ�! login.do(loginServlet.java/doGet) �� �̵���Ű��
			// ���ǿ� �α�������(loginUser)�� �����ֱ⶧���� main.jsp �� �Ѿ��
		response.sendRedirect("memberUpdate.do?result="+x);
	}
}
