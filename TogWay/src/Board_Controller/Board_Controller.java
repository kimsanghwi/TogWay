package Board_Controller;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/Board_Controller")
public class Board_Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");
		
		System.out.println("프론트에서 command :" + command);
		Action_Factory af = Action_Factory.getInstance();
		
		Action action = af.getAction(command);
		
		if(action != null)
		{
			action.execute(request, response);
		}
		
		
		
	}


	
		
		
		
		
		
		
		
		
	}


