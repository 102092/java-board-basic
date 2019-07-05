package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;

@WebServlet("/update.do")
public class BbsUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BbsUpdateAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		BbsDAO dao = new BbsDAO();
		BbsVO form = null;	
		PrintWriter out = response.getWriter(); 
		
		if(session.getAttribute("user")==null) {
			//System.out.println("여기로 들어왔음");
			out.print("<script>");
			out.print("alert(\"로그인 후 글 수정할 수 있습니다.\");");
			out.print("location.href=\"./login.do\"");
			out.print("</script>");			
		}else {
			String page = request.getParameter("page");
			int bid = Integer.parseInt(request.getParameter("bid"));
			form = new BbsVO();
			form.setBid(bid);
			form.setSubject(request.getParameter("subject"));
			form.setContents(request.getParameter("contents"));
			
			if(dao.updateBbs(form) > 0) {
				response.sendRedirect("./view.do?bid="+bid+"&page="+page);
			}

			
		}
	}

}
