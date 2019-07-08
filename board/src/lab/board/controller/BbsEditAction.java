package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;


@WebServlet("/modify.do")
public class BbsEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BbsEditAction() {
        super();
       
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		PrintWriter out = response.getWriter();
		
		BbsDAO dao = new BbsDAO();
		int bid = Integer.parseInt(request.getParameter("bid"));
		String page =  request.getParameter("page");
		String pwd = request.getParameter("password");
		
		BbsVO articles = null;
		articles = dao.getArticle(bid);
//		System.out.println(pwd);
//		System.out.println(articles.getPassword());
		
		
		if(articles!=null && articles.getPassword().equals(pwd) ) {
			request.setAttribute("article", articles);
			request.setAttribute("bid", new Integer(bid));
			request.setAttribute("page", new Integer(page));
			rd = sc.getRequestDispatcher("/bbs_edit.jsp");
			rd.forward(request, response);
		}else {
			//System.out.println("패스워드 오류문으로 들어옴");
			out.println("<script>");
			out.println("alert(\'패스워드 오류\')");
			out.println("location.href=\"./view.do?bid="+bid+"&page="+page+"\"");
			out.println("</script>");
		}
		
		
		
		

	}

}
