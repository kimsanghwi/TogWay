package togwayDB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Comment_Controller
 */
@WebServlet("/Comment_Controller")
public class Comment_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int exe = Integer.parseInt(request.getParameter("exe"));
		int num = Integer.parseInt(request.getParameter("num"));
		
		String writer = request.getParameter("writer");
		String passwd = request.getParameter("passwd");
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("num"));
		
		
		Comment vo = new Comment();
		vo.setNum(num);
		
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setPasswd(passwd);
		vo.setRef(ref);
		
		CommentService comment = CommentService.getInstance();
		
		out.print("<script>");
		if(exe == 1) {
			comment.insert(vo);
			out.print("alert('댓글 등록 완료');");
		}else if(exe == 2) {
			boolean res = comment.update(vo);
			if(res) {
				out.print("alert('댓글 수정 완료');");
			}else {
				out.print("alert('글 비밀번호 오류');");
			}
		}else {
			boolean res = comment.delete(num, passwd);
				if(res) {
					out.print("alert('댓글 삭제 완료');");
				}else {
					out.print("alert('글 비밀번호 오류');");
				}
		}
		out.print("location.href='Board_Controller?command=boardview&num="+num + "'");
		out.print("</script>");
	}

}
