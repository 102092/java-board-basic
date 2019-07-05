package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;


@WebServlet("/delete.do")
public class BbsRemoveAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BbsRemoveAction() {
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
		PrintWriter out = response.getWriter();
		page = request.getParameter("page");
		int bid = Integer.parseInt(request.getParameter("bid"));
		String pwd = request.getParameter("password");
		
		if(pwd.equals(dao.getBbsPassword(bid))){
			if(dao.deleteBbs(bid) > 0) {
				response.sendRedirect("./list.do?page="+page);
			}
			else {
				out.print("<script>");
				out.print("alert(\"패스워드 오류\");");
				out.print("locaton.href=\"./view.do?bid="+bid+"&page="+page+"\";");
				out.print("</script>");
			}

		}
		
	}

}
