package Board_Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import togwayDB.Comment;
import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "boardView.jsp";
		String num = request.getParameter("num");
		
		TogWayDBBean.getinstance().updateReadCount(num);
		
		TogWayBoardDataBean vo = TogWayDBBean.getinstance().selectOneBoardBynum(num);
		List<Comment> commentList = TogWayDBBean.getinstance().selectBoardCommentList(num);
		
		System.out.println("logging for commentList..");
		
		for (int i = 0, length = commentList.size(); i < length; i++) {
			System.out.println(commentList.get(i).getContent());
			
		}
		System.out.println("end logging..");
		
		request.setAttribute("togbean", vo);
		request.setAttribute("clist", commentList);
		
		request.getRequestDispatcher(url).forward(request, response);
		
		

	}

}
