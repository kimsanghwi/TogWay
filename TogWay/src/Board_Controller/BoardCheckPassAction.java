package Board_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		String passwd = request.getParameter("passwd");
		String div_num = request.getParameter("div_num");
		request.setAttribute("div_num", div_num);
		
		TogWayBoardDataBean vo = TogWayDBBean.getinstance().selectOneBoardBynum(num);
		String url="";
		if(vo.getPasswd().equals(passwd)) {
			
			url = "checkSuccess.jsp";
		}else {
			url="boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
