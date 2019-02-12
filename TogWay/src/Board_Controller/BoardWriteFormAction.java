package Board_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String div_num = request.getParameter("div_num");
		request.setAttribute("div_num", div_num);
		
		request.getRequestDispatcher("boardWrite.jsp").forward(request, response);
		
	}

}
