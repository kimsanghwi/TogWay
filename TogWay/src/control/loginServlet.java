package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVo;


@WebServlet("/login.do")	// ������̼� ���
							// jsp�� form action=""�� �̸��� ���ƾ���
public class loginServlet extends HttpServlet {		// ����Ŭ���� ��ӹ޴� �ڹ�
	private static final long serialVersionUID = 1L;
	
	// #2] index.jsp에서 왔다. 로그인 데이터 입력을 위해
	//		뷰 페이지를 보여줘야 한다. 그리고 이동시키자
	// #36] 회원정보를 수정하고 왔다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="login.jsp";

		// #] 만약 로그인이 된 상태라면 세션에 해당 id등의 정보를 올려놓거나
		//		통행권을 확인한다음 있으면 로그인으로 가지 못하게 하자
		//		main.jsp로 가자
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			url = "TogWay_Servlet?command=main_list"; // main.jsp로 이동
		}
		// #3] 뷰 페이지로 가자 -> login.jsp로 가자
		// #37] session이 그대로 남아있기 때문에 main.jsp로 이동한다
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	
	// #5] login.jsp에서 이동 -> userid, pwd를 request에 싣고 왔다
	// #21] 회원가입 후 login.jsp에서 로그인하여 userid와 pwd를 가지고 왔음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "login.jsp";
		// db에서 id 로그인 체크가 안되면 다시 login.jsp
		// id로그인 체크가 되면 main.jsp로 가자
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		// 유저 체크를 db에서 하는데, 전달 클래스(객체)의 메서드가 있다.
		//						DAO의
		MemberDAO dao = MemberDAO.getInstance();
		
		// #6] dao 로직 메서드 수행하러 가자
		// #22] 로그인 통과
		int result = dao.userCheck(userid, passwd);	
			// MemberDAO.java/userCheck(String userID, String passwd)�޼��� �̿�
			// �޼��� ������� result�� ����
		// 사이트에서는 이 부분부터 로그인 성공까지의 대한 내용은 없는데.. 뭐지..
		String message = "";
		
		// #] �������� alert���â ����ֱ� ���� PrintWriter�� ����Ͽ� HTML �������� ����Ѵ�.
			// ���� ���ڵ� ����(�ѱ� ������ �ʵ���)
		response.setCharacterEncoding("utf-8");	
			// ���� ������ ���� : UTF-8 ���ڿ��� �����Ұž�. ��� �˷��ְ� �����Ѵ�
		response.setContentType("text/html;charset=utf-8");
			// �����ϱ�
		PrintWriter out = response.getWriter();
		
		if(result == 1) {	// 로그인 성공
			//url = "main.jsp";	// main.jsp
			
			// #23] 통과된 회원의 정보를 session에 등록하자
			MemberVo vo = dao.getMember(userid);	// 6개의 항목을 가져올 거다
				// login.jsp���� �Ѱ��� ���̵�� �н����尡 DB�� ������ ��ġ�Ѵٸ� "session"�� "loginUser"�ν� ��ü�� �����س��´�
			
			// #25] 세션에 통행권을 만들어놓은것. "loginUser" 만 있으면 main.jsp로 갈 수 있다.
			// 세션에 vo를 등록시켜 놓자
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);	// 우리끼리 통행권을 만들었다. loginUser
				// 그래서 doGet메서드에서는 loginUser의 null 여부를 보고 main으로 갈지
				//		login.jsp로 감지 판단하게 된다
			
			// PrinterWriter �̿��Ͽ� ���â����
			out.println("<script>");
			out.println("alert('로그인을 성공하셨습니다.');");
			out.println("location.href='TogWay_Servlet';");
			out.println("</script>");
			out.close();
		} else if (result == 0) {	//���̵�� ������ ��� Ʋ��
			out.println("<script>");
			out.println("alert('비밀번호를 확인해주세요.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
			out.close();
		} else if (result == -1) {	//�������� ����
			out.println("<script>");
			out.println("alert('존재하지않는 회원입니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
			out.close();

			}
		
		// main.jsp�� �̵�
		// #7] login.jsp�� �̵�
		// request.getRequestDispatcher("memberData.jsp").forward(request, response);
	}


}
