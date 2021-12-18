<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	
	<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- Bootstrap Date-Picker Plugin -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/site.css" rel="stylesheet">
	
	<style>
		.center {
			margin:0 auto;
			width:65%;
		}
		
		.colourPreference {
			background-color: #D8D8D8;
		}
	</style>
	<meta charset="ISO-8859-1">
<title>RF Prediction For PHU</title>
</head>
	<body class="colourPreference">
		<div>	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="/ec-project-web/Index.jsp">Home</a>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="/ec-project-web/AdminSettings.jsp">Prediction Preferences</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/ec-project-web/ManageUsers.jsp">Manage Users</a></li>
					<li class="nav-item">
						<ul class="navbar-nav">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">Predict</a>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item"
										href="/ec-project-web/KNNPredict.jsp?type=FullyVacc">KNN - Fully
											Vacccinated</a></li>
									<li><a class="dropdown-item"
										href="/ec-project-web/KNNPredict.jsp?type=SingleDose">KNN - One Dose
											Vaccinated</a></li>
									<li><a class="dropdown-item"
										href="/ec-project-web/LRPredict.jsp?type=Death">Linear Regression - Death
											Cases</a></li>
									<li><a class="dropdown-item"
										href="/ec-project-web/LRPredict.jsp?type=Resolved">Linear Regression -
											Resolved Cases</a></li>
									<li><a class="dropdown-item" href="/ec-project-web/RFPredict.jsp">Random
											Forest - PHU</a></li>
								</ul></li>
						</ul>
					</li>

					<li class="nav-item"><a class="nav-link"
						href="/ec-project-web/UserPreferences.jsp">User Preferences</a></li>
					<li class="nav-item"><a class="nav-link" href="/ec-project-web/Logs.jsp">Logs</a>
					</li>
				</ul>

				<div class="nav navbar-nav navbar-right" id="navbarContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link"
							href="/ec-project-web/Login.jsp">Log Out</a></li>
					</ul>
				</div>
			</div>
		</nav>
			
			<div>
				<h2 class="container jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">Random Forest: Public Health Unit (PHU) Prediction</h2>
			</div>
			
			<form method="post" action="/ec-project-web/RFServlet">
				<div class="container center shadow p-3 mb-3 bg-white rounded">
						<div>
							<label for="date">Date:</label>
							<input name="date" id="date" class="form-control" type="text" />
						</div>
					
						<div class="mt-2">
							<label for="activecases">Active Cases:</label>
							<input type="number" id="activecases" class="form-control" name="activecases" />
						</div>
					
						<div class="mt-2">
							<label for="resolvedcases">Resolved Cases:</label>
							<input type="number" id="resolvedcases" class="form-control" name="resolvedcases" />
						</div>
					
					
						<div class="mt-2">
							<label for="deaths">Death Cases:</label>
							<input type="number" id="deaths" name="deaths" class="form-control" />
						</div>
					
					<div class="mt-2">
						<input type="submit" class="btn btn-primary" value="Predict">
					</div>
				</div>
			</form>
			
			<div class="container center shadow p-3 mb-5 bg-white rounded">
				<h2 class="text-center">Prediction Results</h2>
				<div class="bg-secondary text-white rounded shadow p-3">
					<h6 name="predictionResult" id="predictionResult">${param.result}</h6>
				</div>
				
			</div>
		</div>
	</body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<script type="text/javascript">
		$(function() {
		    $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
		});
	</script>
</html>