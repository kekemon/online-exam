<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="du.iit.examsystem.*"%>
<%@page import="du.iit.examsystem.helper.*"%>


<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" />
	<script src="https://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<title>Online Exam System | Exam Add</title>

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
      <a class="nav-item nav-link" href="index.jsp">Home</a>
      <a class="nav-item nav-link" href="exam-add.jsp">Exam Add</a>
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
	  				<h5 align="center">Success!</h5>
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
	  				<h5 align="center">Fail!</h5>
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
        <img class="d-block mx-auto mb-1" src="./resources/bootstrap-solid.svg" alt="" width="36" height="36">
       </div>

      <div class="row">
        <div class="col-md-12 order-md-1">
          <form id= "mainForm" class="needs-validation"  novalidate="" method="post">
            <div class="row">
              <div class="col-md-6 mb-3">
				<label for="subject">Subject</label>
				<div class="input-group">
					<input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" value="" required="">
					<div class="invalid-feedback">
					  Valid Subject is required.
					</div>
				</div>
              </div>
              <div class="col-md-6 mb-3">
                <label for="duration">Duration</label>
				<div class="input-group">
					<input type="number" class="form-control" name="duration" min=10 id="duration" placeholder="Duartion" value="" required="">
					<div class="invalid-feedback">
					  Valid duration is required.
					</div>
				</div>
              </div>
            </div>
            
			<hr class="mb-4">
          </form>
		 
		  <form id= "addForm" class="needs-validation" novalidate="">
		  	<div class="row">  
			  <div class="col-md-12 mb-3">
				<label for="question">Question</label>
				<div class="input-group">
					<input type="text" class="form-control" id="question" name="question" placeholder="Question" value="" required="">
					<div class="invalid-feedback">
					  Valid question is required.
					</div>
				</div>
              </div>
				
			  <div class="col-md-4 mb-2">
				<input type="text" class="form-control" id="option1" name="option1" placeholder="Option 1" value="" required="">
				<div class="invalid-feedback">
				  Valid Option is required.
				</div>
              </div>
				
			  <div class="col-md-4 mb-2">
				<input type="text" class="form-control" id="option2" name="option2" placeholder="Option 2" value="" required="">
				<div class="invalid-feedback">
				  Valid Option is required.
				</div>
              </div>
             
              <div class="col-md-4 mb-2">
				<input type="text" class="form-control" id="option3" name="option3" placeholder="Option 3" value="" required="">
				<div class="invalid-feedback">
				  Valid Option is required.
				</div>
              </div>
                     
            </div>
		    <div class="row">
              <div class="col-md-4 mb-3">
                <input type="text" class="form-control" id="option4" placeholder="Option 4" value="" required="">
				<div class="invalid-feedback">
				  Valid Option is required.
				</div>
              </div>
              
              <div class="col-md-4 mb-3">
                <input type="number" class="form-control" id="answer" max=4 min=1 placeholder="Option #" value="" required="">
				<div class="invalid-feedback">
				  Valid Answer is required.
				</div>
              </div>
              
			  <div class="col-md-4 mb-3">
				<button id="addbtn" class="btn-primary form-control" type="button">ADD</button>
				
              </div>
            </div>
          </form>
		  <p>&nbsp;</p>
		  <table id="myTable" style="text-align:center" class="table table-sm table-hover table-bordered">
			<thead>
			<tr>
			  <th scope="col">Question</th>
			  <th scope="col">Option 1</th>
			  <th scope="col">Option 2</th>
			  <th scope="col">Option 3</th>
			  <th scope="col">Option 4</th>
			  <th scope="col">Answer</th>
			</tr>
		  </thead>
		  <tbody id="mtbody">
			
		  </tbody>
		</table>
		<p>&nbsp;</p>
		<div class="row">
            <div class="col-md-3 mb-3 mr-auto ml-auto">
            	<button id= "submitbtn" class="btn btn-primary btn-block" type="submit">SUBMIT</button>
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

    function checkTable() {
    	var tbody = $("#mytable tbody");

    	if (tbody.children().length == 0) {
    		$("#myTable").hide();
    	}
    }
    
    function addtoTable(question, option1, option2, option3, option4, answer) {
    	$("#myTable").show();
    	var markup = "<tr><td>" + question + "</td><td>" + option1 + "</td><td>" + option2 + "</td><td>" + option3 + "</td><td>" + option4 + "</td><td>" + answer + "</td></tr>";
    	$("table tbody").append(markup);
    }
    
    (function() {
        'use strict';
		window.addEventListener('load', function() {
			
			$('#successModal').on('click', function(event) {
				document.location.href = "index.jsp";
			});
			
			if ($('#myTable tbody').children().length == 0) {
	    		$("#myTable").hide();
	    	}
			
			var forms = document.getElementsByClassName('needs-validation');

			
			document.getElementById('addbtn').addEventListener('click', function(event) {
				var form = document.getElementById("addForm");
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
					form.classList.add('was-validated');
				} else {
					$("#myTable").show();
					var question = $("#question").val();
					var option1 = $("#option1").val();
					var option2 = $("#option2").val();
					var option3 = $("#option3").val();
					var option4 = $("#option4").val();
					var answer = $("#answer").val();
					
					var comment = $("#comment").val();
					addtoTable(question, option1, option2, option3, option4, answer);
					$('#question').val("");
					$('#option1').val("");
					$('#option2').val("");
					$('#option3').val("");
					$('#option4').val("");
					$('#answer').val("");
					document.getElementById("question").focus();
				}
            }, false);
			
			document.getElementById('submitbtn').addEventListener('click', function(event) {
				var form = document.getElementById("mainForm");
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
					form.classList.add('was-validated');
				} else {
					var mcq = Array();

					$("table #mtbody tr").each(function(i, v){
						mcq[i] = Array();

						mcq[i] = { "question" : $(this).children('td:eq(0)').text()
						        , "option1" : $(this).children('td:eq(1)').text()
						        , "option2" : $(this).children('td:eq(1)').text()
						        , "option3" : $(this).children('td:eq(1)').text()
						        , "option4" : $(this).children('td:eq(1)').text()
						        , "answer" : $(this).children('td:eq(2)').text()
					    }
					})

					var exam = Array();
					exam = {  "subject" : document.getElementById("subject").value
					        , "duration" : document.getElementById("duration").value
					        , "mcq" : mcq
				    }
					
					$.ajax({
						type : "post",
						url : "AddExam",
						data: JSON.stringify(exam),
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

            }, false);
			
        }, false);
    })();
   
    </script>
   	</body>
</html>