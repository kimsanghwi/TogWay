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

@WebServlet("/memberdata.do")
public class memberDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public memberDataServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");

		
		// 데이터베이스에서 존재하는 개인정보를 검색 후 가져온다 
		MemberDAO dao = MemberDAO.getInstance();
		MemberVo member = dao.getMember(userid);
		
		request.setAttribute("vo", member);

		RequestDispatcher dis = request.getRequestDispatcher("memberData.jsp");
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("memberUpdate.jsp");
		dis.forward(request, response);
	}

}
