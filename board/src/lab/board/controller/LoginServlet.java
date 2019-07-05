package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.board.model.UserMgrDAO;
import lab.board.model.UserVO;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String uid = request.getParameter("userid");
		String upwd = request.getParameter("userpwd");
		String page = request.getParameter("page");
		PrintWriter out = response.getWriter();
		UserMgrDAO dao = new UserMgrDAO();
		UserVO user = null;
		HttpSession session = null;
		
		user = dao.loginProc(uid, upwd);	
		
		if(user.getUserid() !=null) {
			session = request.getSession();
			session.setAttribute("user", user);
			if(page!=null) {
				//System.out.println("page!=null");
				response.sendRedirect("./list.do?page="+page);
			}else {
				//System.out.println("page=null");
				response.sendRedirect("./list.do");
			}
		}else {
			System.out.println("user.getUserid() !=null");
			out.println("<script>");
			out.println("alert(\'로그인 실패\')");
			out.println("location.href=\"./login.jsp\"");
			out.println("</script>");
		}	


	}

}
