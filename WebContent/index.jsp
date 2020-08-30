<!DOCTYPE html>

<html lang="en"><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
    <link href="https://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" />
	<script src="https://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <title>Online Exam System</title>

    <!-- Bootstrap core CSS -->
    <link href="./resources/bootstrap.min.css" rel="stylesheet">

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
  <body class="bg-light">

    <div class="container">

      <div class="modal fade" id="failModal" tabindex="-1" role="dialog" aria-hidden="true">
	  	<div class="modal-dialog modal-sm" role="document">
	    	<div class="modal-content">
	      		<div class="modal-body">
	      			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
	  				<h5 align="center">Login Failed!</h5>
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
      </div>
	  <br><br>	
	  <div class="row">
	    <div class="col-md-6 mr-auto ml-auto">
	      <div class="card">
		    <div class="card-header text-center">
		       <h5>LOGIN</h5>
		    </div>
		  <div class="card-body">       
	        <form id= "mainForm" method="post" class="needs-validation"  novalidate="" >
				<div class="row">
				  <div class="col-md-8 mb-3 p-0  mr-auto ml-auto">
	                <label for="email">EMAIL</label>
					<div class="input-group">
						<input type="email" class="form-control" name="email" id="email" placeholder="Email" value="" required="">
						<div class="invalid-feedback">
						  Valid email is required.
						</div>
					</div>
	              </div>
				 </div>
				 <div class="row">
				  <div class="col-md-8 mb-3 p-0 mr-auto ml-auto">
	                <label for="password">PASSWORD</label>
					<div class="input-group">
						<input type="password" class="form-control" name="password" id="password" placeholder="password" value="" required="">
						<div class="invalid-feedback">
						  Valid password is required.
						</div>
					</div>
	              </div>
				 </div>
				 
				<div class="row">
				  <div class="col-md-4 mb-3 mr-auto ml-auto">
					<button id= "submitbtn" class="btn btn-primary btn-mm btn-block" type="button">SUBMIT</button>
				  </div>
	            </div>
	            
	          </form>
	        </div>
	      </div>
		 </div>
	   </div>

	  <script>
	  
	  (function() {
	        'use strict';
			window.addEventListener('load', function() {
				
				// Fetch all the forms we want to apply custom Bootstrap validation styles to
				var form = document.getElementById('mainForm');
				var button = document.getElementById('submitbtn');
				// Loop over them and prevent submission
				button.addEventListener('click', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
						form.classList.add('was-validated');
					} else{
						var data = Array();
						data = { "email" : document.getElementById("email").value
						        , "password" : document.getElementById("password").value
					    }
						
						$.ajax({
							type : "post",
							url : "login",
							data: JSON.stringify(data),
						    success : function(msg) {
						    	if ( msg.indexOf("Successfully")>=0) {
						    		document.location.href = "home.jsp";	
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
	  	  
      
    </div>

  	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="./resources/bootstrap.min.js.download"></script>
    </body>
</html>