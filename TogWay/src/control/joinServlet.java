package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVo;

@WebServlet("/join.do")
public class joinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 버튼 클릭 해서 넘어온다
		request.getRequestDispatcher("join.jsp").forward(request, response);
		// join.jsp로 이동
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setUserid(userid);
		vo.setPasswd(passwd);
		vo.setEmail(email);
		vo.setTel(tel);
		
		// dao의 insert를 부르자
		MemberDAO dao = MemberDAO.getInstance();
		
		int result = dao.insertMember(vo);
		
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");	
		// ���� ������ ���� : UTF-8 ���ڿ��� �����Ұž�. ��� �˷��ְ� �����Ѵ�
		response.setContentType("text/html;charset=utf-8");
		// �����ϱ�
		PrintWriter out = response.getWriter();
		if(result == 1) {
	         // 아까 만들어 놨던 id를 세션에 저장해 놓기
	         session.setAttribute("userid", vo.getUserid());
	         out.println("<script>");
	         out.println("alert('회원가입에 성공했습니다.');");
	         out.println("location.href='login.jsp';");
	         out.println("</script>");
	         out.close();
	      } else {
	         out.println("<script>");
	         out.println("alert('회원가입에 실패했습니다.');");
	         out.println("location.href='join.jsp';");
	         out.println("</script>");
	         out.close();
	      }
		// 로그인 jsp로 다시 가자
		// request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
