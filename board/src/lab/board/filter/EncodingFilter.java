package lab.board.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//필터를 통해, 매번 인코딩해야하는 일을 줄여준다.
			//System.out.println("utf-8로 인코딩 적용함");
			request.setCharacterEncoding("utf-8");
			chain.doFilter(request, response);
	}
}
