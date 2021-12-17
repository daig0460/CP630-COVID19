<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<style>
		.center {
			margin:0 auto;
			width:50%;
		}
		
		.colourPreference {
			background-color: #D8D8D8;
		}
	</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="colourPreference">	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		    <div class="container-fluid">
		        <a class="navbar-brand" href="/Index.jsp">Home</a>
		        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        	<li class="nav-item">
		            	<a class="nav-link" href="/AdminSettings.jsp">Prediction Preferences</a>
		  			</li>
		    		<li class="nav-item">
		    			 <a class="nav-link" href="/ManageUsers.jsp">Manage Users</a>
		    		</li>
		    		<li class="nav-item">
			    		<ul class="navbar-nav">
			                <li class="nav-item dropdown">
			                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Predict</a>
			                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
			                        <li><a class="dropdown-item" href="/KNNPredict.jsp?type=FullyVacc">KNN - Fully Vacccinated</a></li>
			                        <li><a class="dropdown-item" href="/KNNPredict.jsp?type=SingleDose">KNN - One Dose Vaccinated</a></li>
			                        <li><a class="dropdown-item" href="/LRPredict.jsp?type=Death">Linear Regression - Death Cases</a></li>
			                        <li><a class="dropdown-item" href="/LRPredict.jsp?type=Resolved">Linear Regression - Resolved Cases</a></li>
			                        <li><a class="dropdown-item" href="/RFPredict.jsp">Random Forest - PHU</a></li>
			                    </ul>
			                </li>
			            </ul>
		    		</li>
		    		
		    		<li class="nav-item">
		    			 <a class="nav-link" href="/UserPreferences.jsp">User Preferences</a>
		    		</li>
		    		<li class="nav-item">
		    			 <a class="nav-link" href="/Logs.jsp">Logs</a>
		    		</li>
				</ul>
		
		        <div class="collapse navbar-collapse justify-content-end" id="navbarContent">
		            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		            	<li class="nav-item">
		                    <a class="nav-link" href="/Login.jsp">Log Out</a>
		                </li>
		            </ul>      
		        </div>
		    </div>
			</nav>
	
	<div>
		<h2 class="jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">User Preferences Page</h2>
	</div>
	
	<form method="post" action="/[ejb-project]/[servlet]">
		<div class="container ui-grid-solo center shadow p-3 mb-5 bg-white rounded">
			<div class="ui-block-a">
				<div class="ui-field-contain">
					<label for="exampleColorInput" class="form-label">Color picker</label>
					<input type="color" class="form-control form-control-color" id="exampleColorInput" value="#563d7c" title="Choose your color">
				</div>
			</div>
			
			<div>
				<input type="submit" value="Update User Preferences">
			</div>
		</div>
	</form>
</div>
</body>
</html>