package du.iit.examsystem.helper;

import java.io.*;
import java.util.*;
import org.json.*;
import javax.servlet.*;
import javax.servlet.http.*;
import du.iit.examsystem.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/add-exam")
public class AddExam  extends HttpServlet{
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
			String subject = jsonObject.getString("subject");
			int duration = jsonObject.getInt("duration");
			System.out.println(jsonObject);
			System.out.println(duration + " " + subject);
			Set<MCQ> mcqs = new HashSet<>();
			JSONArray jsonArray = jsonObject.getJSONArray("mcqs");
			System.out.println(jsonArray.length());
			for (int index = 0; index < jsonArray.length(); index++ ) {
				JSONObject mjJsonObject = jsonArray.getJSONObject(index);
				String question = mjJsonObject.getString("question");
				String option1 = mjJsonObject.getString("option1");
				String option2 = mjJsonObject.getString("option2");
				String option3 = mjJsonObject.getString("option3");
				String option4 = mjJsonObject.getString("option4");
				int answer = mjJsonObject.getInt("answer");
				mcqs.add(new MCQ(question, option1, option2, option3, option4, answer));
			}
			
			CommonUtits.addExam((Integer)userID, subject, duration, mcqs);
			
			response.setContentType("application/text");
	        response.setCharacterEncoding("UTF-8");
			
	        out.print("Successfully added Exam.");
	    }catch (Exception e) {
	    	out.print("Failed to add.");
		} finally {
	        reader.close();
	        out.flush();
	    }			   
	}
}
