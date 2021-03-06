<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="du.iit.examsystem.*"%>
<%@page import="du.iit.examsystem.helper.*"%>

<%
	Object userID = request.getSession().getAttribute(CommonUtits.SESSION_KEY_USERID);
	if(userID == null) {
		response.sendRedirect("index.jsp");
		return;
	}
%>

<html lang="en"><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="./resources/download.png">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
    <link href="https://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" />
	<script src="https://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    
    <title>Online Exam System</title>

    <!-- Bootstrap core CSS -->
    <link href="./resources/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./resources/form-validation.css" rel="stylesheet">
    <style>
    	button {
	    	background-color: Transparent;
	    	border: none; 
	    	outline: none; 
	    	cursor: pointer;
    	}
    	button:focus {outline:0;}
    </style>
  </head>
  <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="#">Online Exam System</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-item nav-link" href="home.jsp">Home</a>
      	  <% 
	  	  	 User user = CommonUtits.getUser(Integer.parseInt(userID.toString()));
	  	  	 if (user instanceof Teacher) {
	  	  		 %>
	  	  		 	<a class="nav-item nav-link" href="exam-add.jsp">Add Exam</a>
	  	  		 <%
	  	  	 }
  	  	  %>
		  <a class="nav-item nav-link" href="logout">Logout</a>
	    </div>
	  </div>
	</nav>
  <body class="bg-light">

    <div class="container">
      <div class="py-4 text-center">
        <img class="d-block mx-auto mb-1" src="./resources/download.png" alt="" width="100" height="60">
       </div>

      <div class="row">
        <div class="col-md-12 order-md-1">
          
		  <table id="myTable" style="text-align:center" class="table table-sm table-hover table-bordered">
			<thead>
			<tr>
			  <th scope="col">Subject</th>
			  <th scope="col">Teacher</th>
			  <th scope="col">Duration(min)</th>
			  <th scope="col">Action</th>
			</tr>
		  </thead>
		  <tbody id="mtbody">
			<%
				List<Object> exams = DatabaseUtils.getList(Exam.class);
				for (Object object : exams) {
					Exam exam = (Exam) object;
					%>
					<tr>
					  <td scope="col"><%=exam.getSubject() %></td>
					  <td scope="col"><%=exam.getTeacher().getFullName() %></td>
					  <td scope="col"><%=exam.getDuration() %></td>
					  <% 
					  	if (user instanceof Teacher) {
					  		%>
					  			<td scope="col"><a href="result.jsp?exam=<%=exam.getID()%>">Result</a></td>
					  		<%
					  	} else {
					  		if (CommonUtits.isFinisedExam(user.getID(), exam.getID())) {
					  			%>
					  				<td scope="col"><a href="result.jsp?exam=<%=exam.getID()%>">Result</a></td>
					  			<%
					  		} else {
					  			%>
					  				<td scope="col"><a href="answer.jsp?exam=<%=exam.getID()%>">Start</a></td>
					  			<%
					  		}
					  	}
					  %>
					  
					</tr>
					<%
				}
			%>		
		  </tbody>
		</table>

        </div>
      </div>
	  
     
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="./resources/bootstrap.min.js.download"></script>
    </body>
</html>