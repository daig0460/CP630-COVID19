<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>User Login</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
		
		<style>
			body, html {
	  			height: 100%;
			}		
			
			.bg {
			  /* The image used */
			  background-image: url('images/covid19_login_background.jpg');
			
			  /* Full height */
			  height: 100%;
			
			  /* Center and scale the image nicely */
			  background-position: center;
			  background-repeat: no-repeat;
			  background-size: cover;
			}
			
			.popup {
			    position: fixed;
			    top: 50%;
			    left: 50%;
			    -webkit-transform: translate(-50%, -50%);
			    transform: translate(-50%, -50%);
			}
		</style>
	</head>
	<body class="bg">
		<div>
		<form action="/ec-project-web/LoginServlet" method="post">
				<div class="popup">					
					<div class="p-3 mb-2 bg-light rounded shadow">
						<h3 class="text-center">COVID-19 Login Form</h3>
						
						<div class="form-group row">
							<label class="form-label">Username</label>
							<input class="form-control" type="text" name="username" required>
						</div>
						
						<div class="form-group row">
					    	<label class="form-label">Password</label> 
					    	<input class="form-control" type="password" name="password" required>
					    </div>
					    
					    <div class="mb-2 mt-2 row form-inline">
					    	<input class="btn btn-danger btn-block mt-1" type="submit" value="Login" />
					    </div>
					</div>		     
				</div>
			</form>
		</div>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>