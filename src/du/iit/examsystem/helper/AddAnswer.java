package du.iit.examsystem.helper;

import java.io.*;
import org.json.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/add-answer")
@MultipartConfig
public class AddAnswer  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 810712465735103326L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Object userID = request.getSession().getAttribute(CommonUtits.SESSION_KEY_USERID);
		if(userID == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    PrintWriter out = response.getWriter();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	        JSONObject jsonObject = new JSONObject(sb.toString());

	        int examID = jsonObject.getInt("exam");
			int mcqID = jsonObject.getInt("mcq");
			String choice = jsonObject.getString("choice");
			
			System.out.println(examID + " " + mcqID + " " + choice);
			
			if (choice != null && !choice.isEmpty()) {
				CommonUtits.addAnswer((Integer)userID, mcqID, Integer.parseInt(choice));
			}
			
			response.setContentType("application/text");
	        response.setCharacterEncoding("UTF-8");
			
	        out.print("Successfully added Answer.");
	        return;
	    }catch (Exception e) {
	    	e.printStackTrace();
		} 	
	    out.print("Failed to add.");
	}
}
