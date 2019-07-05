package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;
import lab.board.model.CommentVO;


@WebServlet("/comment.do")
public class CommAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommAddAction() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request,response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		CommentVO comm = null;
		BbsDAO dao = null;
		comm = new CommentVO();
		dao = new BbsDAO();
		String page = null;
		String bid = null;
		page = request.getParameter("page");
		bid = request.getParameter("bid");
		
		PrintWriter out = response.getWriter();
		
		comm.setRbid(Integer.parseInt(request.getParameter("bid")));
		comm.setWriter(request.getParameter("writer"));
		comm.setContents(request.getParameter("contents"));
		comm.setPassword(request.getParameter("password"));
		comm.setIp(request.getRemoteAddr());
		
		if(dao.insertComment(comm) > 0) {
//			System.out.println("bid :"+ bid);
//			System.out.println("page :"+ page);
			response.sendRedirect("./view.do?bid="+bid+"&page="+page);
		}else {
			out.print("<script>");
			out.print("alert(\"댓글 추가 오류\");");
			out.print("locaton.href=\"./view.do?bid="+bid+"&page="+page+"\";");
			out.print("</script>");

		}
		
		
		

	}

}
