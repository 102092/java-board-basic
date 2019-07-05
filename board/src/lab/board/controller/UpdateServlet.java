package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.UserMgrDAO;
import lab.board.model.UserVO;

@WebServlet("/userupdate.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");		
		PrintWriter out = response.getWriter();	
		
		HashMap<String, String> jobs = new HashMap<String, String>();
		jobs.put("39", "학생");
		jobs.put("40", "컴퓨터/인터넷");
		jobs.put("41", "언론");
		jobs.put("42", "공무원");
		jobs.put("43", "군인");
		jobs.put("44", "서비스업");
		jobs.put("45", "교육");
		jobs.put("46", "금융/증권/보험업");
		jobs.put("47", "유통업");
		jobs.put("48", "예술");
		jobs.put("49", "의료");
		
		
		String uid = request.getParameter("userid");
		String uname = request.getParameter("username");
		String upwd = request.getParameter("userpwd");
		String phone = request.getParameter("phone");
		
		
		String email = request.getParameter("email");
		
		email+="@";
		
		if(request.getParameter("emailaddr").length()>2) {
			email +=request.getParameter("emailaddr");
			
		}
		else {
			email += request.getParameter("email_dns");
		}
		
		String job = jobs.get(request.getParameter("job"));		
		String address = request.getParameter("address");
		
		UserVO user = new UserVO();
		user.setUserid(uid);
		user.setUsername(uname);
		user.setUserpwd(upwd);
		user.setPhone(phone);
		user.setEmail(email);
		user.setJob(job);
		user.setAddress(address);
		
		UserMgrDAO dao = new UserMgrDAO();
		
		
		if(dao.updateProc(user) > 0) {
			out.println("<script>");
			out.println("alert(\'회원정보 수정 성공.\')");
			out.println("location.href=\"./login.jsp\"");
			out.println("</script>");			
			
			

		}else {
			out.println("<script>");
			out.println("alert(\'회원정보 수정 실패.\')");
			out.println("location.href=\"./member.jsp\"");
			out.println("</script>");
		}


	}

}
