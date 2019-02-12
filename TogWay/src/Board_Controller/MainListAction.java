package Board_Controller;

import java.io.IOException;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import togwayDB.TogWayBoardDataBean;
import togwayDB.TogWayDBBean;

public class MainListAction implements Action {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid =null;
		 userid = (String)session.getAttribute("userid");//세션에서 얻어낸 사용자 아이디
		int pageSize = 5;
		// 게스글을 페이지에 몇게 보여 줄 것 인가.
	
		

			int pageNum = 1;
		   
		
		int count = 0;//전체글의 수
			
	
		int number = 0; // jsp페에지 내에 보여질 넘버링 숫자를 저장
		int maxnumber = 0;
		
		// 현재 페이지의 수 
	

		int vid1 = 1; 
		int vid2 = 2; 
		int vid3 = 3; 
		int vid4 = 4; 
		int currentPage = pageNum;
	
		TogWayDBBean togbean = TogWayDBBean.getinstance();	
		
		int count1 = togbean.getAllCount(vid1);
		int count2 = togbean.getAllCount(vid2);
		int count3 = togbean.getAllCount(vid3);
		int count4 = togbean.getAllCount(vid4);
		
		//데이터베이스에 저장된 board테이블의 레코드 개수를 가지고 옴,
	
		
		int startRow = (currentPage - 1) * pageSize;
		int endRow = pageSize;
		
		// 보여줄 게시글의 수를 구할 때 사용 = 아레 list에 적용
				
		List<TogWayBoardDataBean> mypet = togbean.selectAllBoards(startRow, endRow, vid1);
		

		int maxnumber1 = count1;
		System.out.println("프론트에서  maxnumber1  :" +  maxnumber1);	
		
		List<TogWayBoardDataBean> review = togbean.selectAllBoards(startRow, endRow, vid2);
		
		
		int maxnumber2 = count2;
		System.out.println("프론트에서  maxnumber2  :" +  maxnumber2);	
		
		List<TogWayBoardDataBean> fleamarket = togbean.selectAllBoards(startRow, endRow, vid3);
		
	
		int maxnumber3 = count3;
		System.out.println("프론트에서  maxnumber3  :" +  maxnumber3);	
		
		List<TogWayBoardDataBean> hospital = togbean.selectAllBoards(startRow, endRow, vid4);
		
		
		int maxnumber4 = count4;
		System.out.println("프론트에서  maxnumber4  :" +  maxnumber4);	
		
		
		//게사판의 게시글 번호를 얻을 수 있음
		
		
		// 페지징 처리 
		

		
		
		request.setAttribute("pageNum", pageNum);		
		request.setAttribute("mypet", mypet);
		request.setAttribute("review", review);
		request.setAttribute("fleamarket", fleamarket);
		request.setAttribute("hospital", hospital);
		request.setAttribute("maxnumber1", maxnumber1);
		request.setAttribute("maxnumber2", maxnumber2);
		request.setAttribute("maxnumber3", maxnumber3);
		request.setAttribute("maxnumber4", maxnumber4);
		request.setAttribute("vid1", vid1);
		request.setAttribute("vid2", vid2);
		request.setAttribute("vid2", vid3);
		request.setAttribute("vid2", vid4);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("userid", userid);
		
		
		
		
		
		
				
		
		request.getRequestDispatcher("Main_board.jsp").forward(request, response);
			
	
		
		
				
			}

		

		



	

}
