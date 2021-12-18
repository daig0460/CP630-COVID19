<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/site.css" rel="stylesheet">
	
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
		<h2 class="jumbotron container rounded shadow p-3 mt-2 mb-3 bg-white text-center">Logs for [BLANK]</h2>
	</div>
	
	<div class="container shadow p-3 mb-5 bg-white rounded">
		<table class="table table-striped table-hover">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>