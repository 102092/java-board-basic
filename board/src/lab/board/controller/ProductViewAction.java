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
import javax.servlet.http.HttpSession;

import lab.board.model.ProductDAO;
import lab.board.model.ProductVO;


@WebServlet("/productview.do")
public class ProductViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductViewAction() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter(); 
		//System.out.println(session.getAttribute("user"));
		
		if(session.getAttribute("user")==null) {
			//System.out.println("여기로 들어왔음");
			out.print("<script>");
			out.print("alert(\"로그인 후 글 내용을 볼 수 있습니다. \");");
			out.print("location.href=\"./login.jsp\"");
			out.print("</script>");			
		}
		else {
			doPost(request,response);	
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		PrintWriter out = response.getWriter();
		
		ProductDAO dao = new ProductDAO();
		String proid = request.getParameter("proid");
		
		
		ProductVO article = null;
		article = dao.getProduct(proid);

		
		
		if(article !=null) {
			request.setAttribute("article", article);
			request.setAttribute("proid", proid);
			rd = sc.getRequestDispatcher("/product_view.jsp");
			rd.forward(request, response);
		}else {
			out.print("<script>");
			out.print("alert(\"잘못된 경로입니다.\");");
			out.print("location.href=\"./list.do\"");
			out.print("</script>");
		}
		
		
		
		

	}

}
