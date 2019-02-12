package Board_Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class BoardlistAction implements Action {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String userid =null;
		 userid = (String)session.getAttribute("userid");//세션에서 얻어낸 사용자 아이디
		int pageSize = 10;
		// 게스글을 페이지에 몇게 보여 줄 것 인가.
	
		
		String pageNum = request.getParameter("pageNum");
		String div_num = request.getParameter("div_num");
		System.out.println(div_num);
		
		if (pageNum == null) //페이지번호가 없으면 1페이지의 내용이 화면에 표시
		    {
			pageNum = "1";
		    }
		
		int count = 0;//전체글의 수
			
	
		int number = 0; // jsp페에지 내에 보여질 넘버링 숫자를 저장
		
		int currentPage = Integer.parseInt(pageNum);
		// 현재 페이지의 수 
		System.out.println("프론트에서  currentPage  :" +  currentPage);	
		int vid = Integer.parseInt(div_num);
		
		System.out.println("vid" + vid);
		
	
		TogWayDBBean togbean = TogWayDBBean.getinstance();	
		
		count = togbean.getAllCount(vid);
		
		//데이터베이스에 저장된 board테이블의 레코드 개수를 가지고 옴,
		
		int startRow = (currentPage - 1) * pageSize;
		int endRow = pageSize;
		// 보여줄 게시글의 수를 구할 때 사용 = 아레 list에 적용
				
		List<TogWayBoardDataBean> list = togbean.selectAllBoards(startRow, endRow, vid);
		
		number = count - (currentPage - 1) * pageSize;
		int maxnumber = count;
		System.out.println("프론트에서  maxnumber  :" +  maxnumber);	
		
		//게사판의 게시글 번호를 얻을 수 있음
		
		
		// 페지징 처리 
		
		int Pageconut = count / pageSize;
		
		if(count % pageSize > 0)
		{
			Pageconut ++;
		}
		if(Pageconut < currentPage )
		{
			Pageconut = currentPage;
		}
		int pageBlock = 5;
		int startPage = 1;
		
		if(currentPage % pageBlock !=0) 
		{
		startPage = (int)((currentPage)/pageBlock) * pageBlock+1;
		}
		else
		{
		startPage = ((int)(currentPage/pageBlock)-1) * pageBlock+1;	
		}
		

		
		System.out.println("프론트에서  startPage  :" +  startPage);	
		
		
		
	
			
		int EndPage = startPage + pageBlock  - 1;
		
		if(EndPage > Pageconut) 
		{
			EndPage = Pageconut;
		}
		if(EndPage < 0)
		{
			EndPage = 1;
		}
		
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("EndPage", EndPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("Pageconut", Pageconut);
		request.setAttribute("div_num", div_num);		
		request.setAttribute("togbean", list);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		
	
		
				
		switch (div_num) {
		case "1":
			request.getRequestDispatcher("mypet_board.jsp").forward(request, response);
			break;
			
		case "2":
			request.getRequestDispatcher("review_board.jsp").forward(request, response);
			break;
			
		case "3":

			request.getRequestDispatcher("fleaMarKet_board.jsp").forward(request, response);
			break;	

		case "4":
			request.getRequestDispatcher("hospital_board.jsp").forward(request, response);
			break;
		default:
			break;
		}
		
		
		
				
			}

		

		



	

}
