package lab.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;


@WebServlet("/search.do")
public class ListSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int numPerPage = 10;
       

    public ListSearchAction() {
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
		BbsDAO dao = new BbsDAO();		
		ArrayList<BbsVO> headers = null;
		String page =null;
		String search = null; 
		String word = null;
		
		page = request.getParameter("page");
		search = request.getParameter("searchKey");
		word = request.getParameter("searchWord");
//		System.out.println(page);
//		System.out.println(search);
//		System.out.println(word);
		
		int pageNo = 1;
		
		if(page==null) {
			pageNo = dao.getPageCount(numPerPage);
			headers = dao.getBbsList(pageNo, numPerPage);
			
		}else {
			pageNo = Integer.parseInt(page);
			headers = dao.getBbsList(search, word, pageNo, numPerPage);
		}
		Integer totalPage = new Integer(dao.getPageCount(numPerPage));
		request.setAttribute("headers", headers);
		request.setAttribute("pageNo", new Integer(pageNo));
		request.setAttribute("totalPage", totalPage);
		rd = sc.getRequestDispatcher("/bbs_list.jsp");
		rd.forward(request, response);
	}


}
