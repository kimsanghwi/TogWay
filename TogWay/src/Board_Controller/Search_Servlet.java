package Board_Controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class TogWay_Servlet
 */
@WebServlet("/Search_Servlet")
public class Search_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Search_Servlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "search_board.jsp";


		
		
		Action_Factory af = Action_Factory.getInstance();
		
		Action action = new Board_Search_List_Action();
	
		action.execute(request, response);

		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
	}	

}
