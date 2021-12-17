<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	
	<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
<style>
	.center {
		margin:0 auto;
		width:75%;
	}
	
	.colourPreference {
		background-color: #D8D8D8;
	}
</style>
<meta charset="ISO-8859-1">
<title>Logs</title>
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
		                    <a class="nav-link" href="/Login.jsp?type=Login">Log In</a>
		                </li>
		                   <li class="nav-item">
		                    <a class="nav-link" href="/Login.jsp?type=LogOut">Log Out</a>
		                </li>
		            </ul>      
		        </div>
		    </div>
			</nav>
	
	<div>
		<h2 class="jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">Logs for [BLANK]</h2>
	</div>
	
	<div class="container ui-grid-solo center shadow p-3 mb-5 bg-white rounded">
		<table data-role="table" id="table-1" class="ui-responsive table-stripe">
			<thead>
				<tr>
					<th>Log ID</th>
					<th>User ID</th>
					<th>Date Created</th>
					<th>Prediction Results</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>a0</td>
					<td>b0</td>
					<td>c0</td>
					<td>d0</td>
				</tr>
				<tr>
					<td>a1</td>
					<td>b1</td>
					<td>c1</td>
					<td>d1</td>
				</tr>
				<tr>
					<td>a2</td>
					<td>b2</td>
					<td>c2</td>
					<td>d2</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>