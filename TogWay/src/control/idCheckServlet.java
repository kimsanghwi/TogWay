package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

@WebServlet("/idCheck.do")
public class idCheckServlet extends HttpServlet {
	// 자바스크립트 idCheck함수에서 보냈다. userid가 있다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		MemberDAO dao = MemberDAO.getInstance();
		// dao에 가서 userid의 값 검사하고 오자
		int result = dao.confirm(userid);	// 1, -1
		// result의 내용과 userid의 내용을 jsp로 가져가야 한다
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		// idcheck.jsp로 가자
		request.getRequestDispatcher("idCheck.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
