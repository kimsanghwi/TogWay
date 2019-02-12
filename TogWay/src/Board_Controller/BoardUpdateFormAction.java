package Board_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
	
		
		TogWayDBBean dao = TogWayDBBean.getinstance();
		dao.updateReadCount(num);
		
		TogWayBoardDataBean vo = dao.selectOneBoardBynum(num);
		
		request.setAttribute("togbean", vo);
		request.getRequestDispatcher("boardUpdate.jsp").forward(request, response);
	}

}
