package du.iit.examsystem.helper;

import java.io.*;
import java.util.List;

import org.json.*;

import du.iit.examsystem.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = "/login")
public class Login  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 810712465735103326L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
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

	        String email = jsonObject.getString("email");
	        String password = jsonObject.getString("password");
			
	        List<Object> objects = DatabaseUtils.getList(User.class);
			for (Object object : objects) {
				User mObject = (User) object;
				if (mObject.check(email, password)) {
					request.getSession().invalidate();
					request.getSession().setAttribute(CommonUtits.SESSION_KEY_USERID, mObject.getID());
			        out.print("Successfully Login.");
			        return;
				}
			}
	    }catch (Exception e) {
	    	e.printStackTrace();
		} 	
	    out.print("Failed to login.");
	}
}
