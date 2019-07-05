package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;


@WebServlet("/delete_comment.do")
public class CommRemoveAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommRemoveAction() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		BbsDAO dao = new BbsDAO();
		String page = null;
		String bid = null;
		PrintWriter out = response.getWriter();
		page = request.getParameter("page");
		bid = request.getParameter("read_num");
		int cmid = Integer.parseInt(request.getParameter("num"));
		String pwd = request.getParameter("password");
		
		System.out.println(pwd);
		System.out.println(dao.getCommPassword(cmid));
		
		
		if(pwd.equals(dao.getCommPassword(cmid))){
			if(dao.deleteComm(cmid) > 0) {
				response.sendRedirect("./view.do?bid="+bid+"&page="+page);
			}
		}else {
			//System.out.println("여기까지왔음");
			out.print("<script>");
			out.print("alert(\"패스워드 오류\");");
			out.print("location.href=\"./view.do?bid="+bid+"&page="+page+"\";");
			out.print("</script>");
			}

		}
		
	}


