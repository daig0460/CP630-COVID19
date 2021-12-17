<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<link rel="stylesheet" type="text/css" href="css/site.css">

<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="colourPreference">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="/Index.jsp">Home</a>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="/AdminSettings.jsp">Prediction Preferences</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/ManageUsers.jsp">Manage Users</a></li>
					<li class="nav-item">
						<ul class="navbar-nav">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">Predict</a>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item"
										href="/KNNPredict.jsp?type=FullyVacc">KNN - Fully
											Vacccinated</a></li>
									<li><a class="dropdown-item"
										href="/KNNPredict.jsp?type=SingleDose">KNN - One Dose
											Vaccinated</a></li>
									<li><a class="dropdown-item"
										href="/LRPredict.jsp?type=Death">Linear Regression - Death
											Cases</a></li>
									<li><a class="dropdown-item"
										href="/LRPredict.jsp?type=Resolved">Linear Regression -
											Resolved Cases</a></li>
									<li><a class="dropdown-item" href="/RFPredict.jsp">Random
											Forest - PHU</a></li>
								</ul></li>
						</ul>
					</li>

					<li class="nav-item"><a class="nav-link"
						href="/UserPreferences.jsp">User Preferences</a></li>
					<li class="nav-item"><a class="nav-link" href="/Logs.jsp">Logs</a>
					</li>
				</ul>

				<div class="collapse navbar-collapse justify-content-end"
					id="navbarContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link"
							href="/Login.jsp?type=Login">Log In</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/Login.jsp?type=LogOut">Log Out</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div>
			<h2
				class="jumbotron container ui-grid-solo rounded center-prediction-options shadow p-3 mt-2 mb-3 bg-white text-center">Prediction
				Options</h2>
		</div>

		<div
			class="container ui-grid-solo center-prediction-options shadow p-3 mb-5 bg-white rounded">
			<div class="ui-grid-a">
				<div class="ui-block-a">
					<div class="card w-10 center shadow"
						style="height: 50%; margin: 2%;">
						<img src="images/LR_Death.jpg" style="width: 100%; height: 50%;"
							class="card-img-top"
							alt="Predicting Death Cases - Linear Regression">
						<div class="card-body">
							<h5 class="card-title">Predicting Death Cases - Linear
								Regression</h5>
							<p class="card-text">Predict the number of death cases by
								providing: a given date, public health unit (PHU), active cases,
								and resolved cases.</p>
							<a href="/LRPredict.jsp?type=Death"
								class="btn btn-danger text-white">Predict</a>
						</div>
					</div>
				</div>
				<div class="ui-block-b">
					<div class="card w-10 center shadow"
						style="height: 50%; margin: 2%;">
						<img src="images/LR_Resolved.jpg"
							style="width: 100%; height: 50%;" class="card-img-top"
							alt="Predicting Resolved Cases - Linear Regression">
						<div class="card-body">
							<h5 class="card-title">Predicting Resolved Cases - Linear
								Regression</h5>
							<p class="card-text">Predict the number of resolved cases by
								providing: a given date, public health unit (PHU), active cases,
								and death cases.</p>
							<a href="/LRPredict.jsp?type=Resolved"
								class="btn btn-info text-white">Predict</a>
						</div>
					</div>
				</div>
				<div class="ui-block-a">
					<div class="card w-10 center shadow"
						style="height: 50%; margin: 2%;">
						<img src="images/KNN_Single_Dose.jpg"
							style="width: 100%; height: 50%;" class="card-img-top"
							alt="Predicting Single Dosage Rate - K-Nearest Neighbor">
						<div class="card-body">
							<h5 class="card-title">Predicting Single Dosage Rate -
								K-Nearest Neighbor</h5>
							<p class="card-text">Predict the vaccination rate of single
								dose populations by providing: a given date, public health unit
								(PHU), and an age group.</p>
							<a href="/KNNPredict.jsp?type=SingleDose"
								class="btn btn-primary text-white">Predict</a>
						</div>
					</div>
				</div>
				<div class="ui-block-b">
					<div class="card w-10 center shadow"
						style="height: 50%; margin: 2%;">
						<img src="images/KNN_Fully_Vacc.jpg"
							style="width: 100%; height: 50%;" class="card-img-top"
							alt="Predicting Full Vaccination Rate - K-Nearest Neighbor">
						<div class="card-body">
							<h5 class="card-title">Predicting Full Vaccination Rate -
								K-Nearest Neighbor</h5>
							<p class="card-text">Predict the vaccination rate of fully
								vaccinated populations by providing: a given date, public health
								unit (PHU), and an age group.</p>
							<a href="/KNNPredict.jsp?type=FullyVacc"
								class="btn btn-success text-white">Predict</a>
						</div>
					</div>
				</div>
				<div class="ui-block-a">
					<div class="card w-10 center shadow"
						style="height: 50%; margin: 2%;">
						<img src="images/RF_PHU.jpg" style="width: 100%; height: 50%;"
							class="card-img-top"
							alt="Predicting Public Health Unit (PHU) - Random Forest">
						<div class="card-body">
							<h5 class="card-title">Predicting Public Health Unit (PHU) -
								Random Forest</h5>
							<p class="card-text">Predict the public health unit (PHU) by
								providing: a given date, active cases, resolved cases, and death
								cases.</p>
							<a href="/RFPredict.jsp" class="btn btn-warning text-white">Predict</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>