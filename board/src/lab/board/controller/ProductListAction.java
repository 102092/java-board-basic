package lab.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.ProductDAO;
import lab.board.model.ProductVO;

@WebServlet("/productlist.do")
public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    public ProductListAction() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request,response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		ProductDAO dao = new ProductDAO();
		
		ArrayList<ProductVO> headers = null;
		headers = dao.getProductList();
		
		request.setAttribute("headers", headers);
		rd = sc.getRequestDispatcher("/products.jsp");
		rd.forward(request, response);
	}

}
