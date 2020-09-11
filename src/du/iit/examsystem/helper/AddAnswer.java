package du.iit.examsystem.helper;

import java.io.*;
import org.json.*;
import du.iit.examsystem.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/add-answer")
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
	    response.setContentType("application/text");
        response.setCharacterEncoding("UTF-8");

	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	        JSONObject jsonObject = new JSONObject(sb.toString());

	        int examID = jsonObject.getInt("exam");
			int mcqID = jsonObject.getInt("mcq");
			
			if (mcqID == -1) {
				Exam exam = CommonUtits.getExam(examID);
				User student = CommonUtits.getUser((Integer)userID);
				CommonUtits.finisExam(exam, exam.getExamSheet(student));
				out.print("Successfully finised exam!");
				return;
			}
			
			String choice = jsonObject.getString("choice");
			
			if (CommonUtits.isFinisedExam((Integer)userID, examID)) {
				out.print("Exam already finised!");
				return;
			}
			
			if (choice != null && !choice.isEmpty()) {
				CommonUtits.addAnswer((Integer)userID, mcqID, Integer.parseInt(choice));
			}
			
	        out.print("Successfully added Answer.");
	        return;
	    }catch (Exception e) {
	    	e.printStackTrace();
		} 	
	    out.print("Failed to add.");
	}
}
