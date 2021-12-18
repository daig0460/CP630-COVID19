<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<link rel="stylesheet" type="text/css" href="css/site.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">  -->

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/site.css" rel="stylesheet">
	
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
<title>Admin - Settings Page</title>
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
				<h2 class="container jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">Admin - Settings Page</h2>
			</div>
			
			<form method="post" action="/ec-project-web/AdminServlet">
				<div class="container center shadow p-3 mb-5 bg-white rounded">
						
						<div class="row">
							<div class="col-8">
								<h5>KNN Fully Vaccinated Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="KNN_Fully_Vaccinated" id="KNN_Fully_Vaccinated" class="form-check-input" style="width:80%;height:70%;" type="checkbox" />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>KNN One Dose Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="KNN_One_Dose" id="KNN_One_Dose" class="form-check-input" style="width:80%;height:70%;" type="checkbox" />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>Linear Regression Death Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="LR_Deaths" id="LR_Deaths" class="form-check-input" style="width:80%;height:70%;" type="checkbox" />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>Linear Regression Resolved Cases Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="LR_Resolved" id="LR_Resolved" class="form-check-input" style="width:80%;height:70%;" type="checkbox" />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>Random Forest PHU Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="RF_PHU" id="RF_PHU" class="form-check-input" style="width:80%;height:70%;" type="checkbox" />
							</div>
							<div class="col-2"></div>
						</div>

					<div class="row form-inline mt-3">
						<input class="btn btn-primary" type="submit" value="Update Prediction Preferences">
					</div>
				</div>
			</form>
		</div>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>