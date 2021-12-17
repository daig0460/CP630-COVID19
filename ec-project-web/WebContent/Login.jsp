<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>User Login</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	</head>
	<body style="background-image: url('images/covid19_login_background.jpg'); background-repeat: no-repeat; background-size: cover;">
		<div>
		<form action="/ec-project-web/LoginServlet" method="post">
				<div class="container d-flex justify-content-center" style="top:20%;left:10%;position:absolute;">					
					<div class="jumbotron rounded shadow p-6">
						<h3 class="text-center">COVID-19 Login Form</h3>
						
						<div class="form-group row">
							<label class="form-label">Username</label>
							<input class="form-control" type="text" name="username" required>
						</div>
						
						<div class="form-group row">
					    	<label class="form-label">Password</label> 
					    	<input class="form-control" type="password" name="password" required>
					    </div>
					    
					    <div class="mb-2">
					    	<input class="btn btn-danger btn-block" type="submit" value="Login" />
					    </div>
					</div>		     
				</div>
			</form>
		</div>
	</body>
</html>