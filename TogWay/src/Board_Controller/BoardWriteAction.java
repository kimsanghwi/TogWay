package Board_Controller;




import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		TogWayBoardDataBean vo = new TogWayBoardDataBean();
		String vid = request.getParameter("div_num");
		int div_num = Integer.parseInt(vid);
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		vo.setDiv_num(div_num);
		vo.setWriter(userid);
		vo.setPasswd(request.getParameter("passwd"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		
		
		
		System.out.println("내용값은 들어옴"+request.getParameter("content"));
		
		TogWayDBBean togbean = TogWayDBBean.getinstance();
		
		togbean.insertArticle(vo);
		
		new BoardlistAction().execute(request, response);
	}

}
