package Board_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TogWayBoardDataBean vo = new TogWayBoardDataBean();
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		vo.setDiv_num(Integer.parseInt(request.getParameter("div_num")));
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		vo.setWriter(request.getParameter("userid"));
		vo.setPasswd(request.getParameter("passwd"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		
		
		TogWayDBBean dao = TogWayDBBean.getinstance();
		dao.updateBoard(vo);
		
		new BoardlistAction().execute(request, response);
	}
	
	

}
