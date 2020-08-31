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
	String examID = request.getParameter("exam");
	String mcqID = request.getParameter("mcq");
	
	if (examID == null) {
		response.sendRedirect("home.jsp");
		return;
	}
	
	boolean canGiveExam = CommonUtits.startExam(Integer.parseInt(userID.toString()), Integer.parseInt(examID));
	
	if(!canGiveExam) {
		response.sendRedirect("home.jsp");
		return;
	}
	MCQ mcq;
	if (mcqID == null) {
		mcq = CommonUtits.getMCQ(CommonUtits.getMCQs(Integer.parseInt(examID))[0]);
	} else {
		mcq = CommonUtits.getMCQ(Integer.parseInt(mcqID));
	}
	int mcqIDs[] = CommonUtits.getMCQs(Integer.parseInt(examID));
%>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="./resources/download.png">
    <link href="https://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" />
	<title>Online Exam System</title>

    <!-- Bootstrap core CSS -->
    <link href="./resources/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./resources/form-validation.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    
    <style>
    	button {
	    	background-color: Transparent;
	    	border: none; 
	    	outline: none; 
	    	cursor: pointer;
    	}
    	button:focus {outline:0;}
    	tbody:empty { display: none;}
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
      <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-hidden="true">
	  	<div class="modal-dialog modal-sm" role="document">
	    	<div class="modal-content">
	      		<div class="modal-body">
	      			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
	  				<h5 align="center">Successfully Finished Exam</h5>
	  				<div class="swal2-icon swal2-success swal2-animate-success-icon" style="display: flex;">
			   			<span class="swal2-success-line-tip"></span>
			   			<span class="swal2-success-line-long"></span>
			  	 	</div>  	 
			  	 	<button type="button" data-dismiss="modal" class="btn btn-success btn-md btn-block">OK</button>
	      		</div>
	    	</div>
	  	</div>
	  </div>
    
      <div class="modal fade" id="failModal" tabindex="-1" role="dialog" aria-hidden="true">
	  	<div class="modal-dialog modal-sm" role="document">
	    	<div class="modal-content">
	      		<div class="modal-body">
	      			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
	  				<h5 align="center">Failed to submit answer!</h5>
	  				<div class="swal2-icon swal2-error swal2-animate-error-icon" style="display: flex;">
			   			<span class="swal2-x-mark">
			   			<span class="swal2-x-mark-line-left"></span>
			   			<span class="swal2-x-mark-line-right"></span></span>
			  	 	</div>  	 
			  	 	<button type="button" data-dismiss="modal" class="btn btn-danger btn-md btn-block">OK</button>
	      		</div>
	    	</div>
	  	</div>
	  </div>
    
      <div class="py-4 text-center">
        <img class="d-block mx-auto mb-1" src="./resources/download.png" alt="" width="100" height="60">
      </div>
      
      <div class="row">
		<div class="col-md-3 mb-3 mr-auto">
			<label><b>Question <%= (Arrays.binarySearch(mcqIDs, mcq.getID()) +1) + " of " + mcqIDs.length%></b></label>
		</div>
		<div class="col-md-3 mb-3 ml-auto">
			<label style="display:block;text-align:right;"><b>Remaining : </b><span id="time"></span> minutes!</label>
		</div>
      </div>
      
      <div class="row">
        <div class="col-md-12 order-md-1">
          <form id= "mainForm" class="needs-validation"  novalidate="" method="post">
		  	<div class="row">  
			  <div class="col-md-12 mb-3">
				<label for="question"><b>Question : </b><%=mcq.getQuestion() %></label>
              </div>
				
			  <div class="col-md-6 mb-2">
			    <label for="question"><b>Option 1</b></label>
				<div class="input-group">
					<input type="text" class="form-control" value=<%=mcq.getOption1() %> disabled>
				</div>
			  </div>
			  
			  <div class="col-md-6 mb-2">
			    <label for="question"><b>Option 2</b></label>
				<div class="input-group">
					<input type="text" class="form-control" value=<%=mcq.getOption2() %> disabled>
				</div>
			  </div>
            </div>
			<p>&nbsp;</p>
		    <div class="row">
		      <div class="col-md-6 mb-2">
			    <label for="question"><b>Option 3</b></label>
				<div class="input-group">
					<input type="text" class="form-control" value=<%=mcq.getOption3() %> disabled>
				</div>
			  </div>
              
              <div class="col-md-6 mb-2">
			    <label for="question"><b>Option 4</b></label>
				<div class="input-group">
					<input type="text" class="form-control" value=<%=mcq.getOption4() %> disabled>
				</div>
			  </div> 
            </div>
          
            <div class="row">
              <div class="col-md-3 mb-2 mr-auto ml-auto">
			    <label for="question"><b>Answer : </b></label>
			    <div class="input-group">
			    	<input type="number" class="form-control" id="answer" max=4 min=1 placeholder="Answer Option #" value=<%=CommonUtits.getAnswer(Integer.parseInt(userID.toString()), mcq.getID()) %>>	
				</div>
			  </div>
            </div>
          </form>
		  <div class="row">
            <div class="col-md-3 mb-3 mr-auto">
            	<% 
            		int prev = CommonUtits.getPrevMCQ(Integer.parseInt(examID), mcq.getID());
            		if (prev != -1) {
           				%>
           					<button onclick="document.location='answer.jsp?exam=<%=examID%>&mcq=<%=prev %>'" class="btn btn-primary btn-block">Previous</button>
           				<%			
            		}
            		
            	%>
            </div>
            <div class="col-md-3 mb-3 ml-auto">
            	<% 
            		int next = CommonUtits.getNextMCQ(Integer.parseInt(examID), mcq.getID());
        			if (next != -1) {
            			%>
           					<button id= "nextbtn" class="btn btn-primary btn-block" type="submit">Next</button>
           				<%			
            		} else {
            			%>
       						<button id= "nextbtn" class="btn btn-primary btn-block" type="submit">Finish</button>
       					<%			
            		}
            	%>	
            </div>
          </div>
		  
        </div>
      </div>
      
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
        
    <script src="./resources/bootstrap.min.js.download"></script>
    <script>	
    window.onload = function () {
        var seconds = <%= CommonUtits.getRemainTime(user.getID(), Integer.parseInt(examID))%>, 
        display = document.querySelector('#time');
    	startTimer(seconds, display);
		
    };
    
    function startTimer(duration, display) {
        var timer = duration, minutes, seconds;
        setInterval(function () {
        	if (--timer <= 0) {
                timer = 0;
            }
        	
        	minutes = parseInt(timer / 60, 10)
            seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent =minutes + ":" + seconds;

            
        }, 1000);
    }
    
    function finishExam() {
    	var answer = Array();
		answer = {  "exam" : <%=examID%>
				, "mcq" : -1
		}

		$.ajax({
			type : "post",
			url : "add-answer",
			data: JSON.stringify(answer),
			success : function(msg) {
				if ( msg.indexOf("Successfully") >= 0) {
					$('#successModal').modal('show');
				} else {
					$('#failModal').modal('show');
				}
			},
			failure: function(msg) {
				$('#failModal').modal('show');
			}
		});
   	}
    
    function addAnswer() {
    	var answer = Array();
		answer = {  "exam" : <%=examID%>
		        , "mcq" : <%=mcq.getID()%>
		        , "choice" : document.getElementById("answer").value
	    }
		
		$.ajax({
			type : "post",
			url : "add-answer",
			data: JSON.stringify(answer),
		    success : function(msg) {
		    	if ( msg.indexOf("Successfully") >= 0) {
			    	var nextID = <%=next%>;
			    	if (nextID == -1) {
			    		finishExam();
			    	} else {
			    		document.location.href = "answer.jsp?exam=" + <%=examID%> + "&mcq=" + nextID;	
			    	}
		    	} else {
		    		$('#failModal').modal('show');
		    	}
			},
			failure: function(msg) {
				$('#failModal').modal('show');
		    }
		});
    }
    
    (function() {
        'use strict';
		window.addEventListener('load', function() {
						
			var forms = document.getElementsByClassName('needs-validation');
			
			$('#successModal').on('click', function(event) {
				document.location.href = "home.jsp";
			});
			
			document.getElementById('nextbtn').addEventListener('click', function(event) {
				var form = document.getElementById("mainForm");
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
					form.classList.add('was-validated');
				} else {
					addAnswer();
				}

            }, false);
			
        }, false);
    })();
   
    </script>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="./resources/bootstrap.min.js.download"></script>
   	</body>
</html>