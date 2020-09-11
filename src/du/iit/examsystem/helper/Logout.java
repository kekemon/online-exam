package du.iit.examsystem.helper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/logout")
public class Logout  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 810712465735103326L;
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
}
