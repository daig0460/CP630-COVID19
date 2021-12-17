<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
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
<title>LR Prediction For [Blank]</title>
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
				<h2 class="jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">Linear Regression: [Death Cases Prediction OR Resolved Cases Prediction]</h2>
			</div>
			
			<form method="post" action="/[ejb-project]/[servlet]">
				<div class="container ui-grid-solo center shadow p-3 mb-3 bg-white rounded">
					<div class="ui-block-a">
						<div class="ui-field-contain">
							<label for="Date">Date:</label>
							<input name="datepicker" id="datepicker" data-role="date" type="text" />
						</div>
					</div>
					<div class="ui-block-a">
						<div class="ui-field-contain">
							<label for="phu">Public Heath Unit (PHU):</label>
							<select name="phu" id="phu">
								<option value="ALGOMA DISTRICT">ALGOMA DISTRICT</option>
								<option value="BRANT COUNTY">BRANT COUNTY</option>
								<option value="CHATHAM-KENT">CHATHAM-KENT</option>
								<option value="CITY OF HAMILTON">CITY OF HAMILTON</option>
								<option value="CITY OF OTTAWA">CITY OF OTTAWA</option>
								<option value="DURHAM REGION">DURHAM REGION</option>
								<option value="EASTERN ONTARIO">EASTERN ONTARIO</option>
								<option value="GREY BRUCE">GREY BRUCE</option>
								<option value="HALDIMAND-NORFOLK">HALDIMAND-NORFOLK</option>
								<option value="HALIBURTON, KAWARTHA, PINE RIDGE">HALIBURTON, KAWARTHA, PINE RIDGE</option>
								<option value="HALTON REGION">HALTON REGION</option>
								<option value="HASTINGS & PRINCE EDWARD COUNTIES">HASTINGS & PRINCE EDWARD COUNTIES</option>
								<option value="HURON PERTH">HURON PERTH</option>
								<option value="KINGSTON, FRONTENAC, LENNOX & ADDINGTON">KINGSTON, FRONTENAC, LENNOX & ADDINGTON</option>
								<option value="LAMBTON COUNTY">LAMBTON COUNTY</option>
								<option value="LEEDS, GRENVILLE AND LANARK DISTRICT">LEEDS, GRENVILLE AND LANARK DISTRICT</option>
								<option value="MIDDLESEX-LONDON">MIDDLESEX-LONDON</option>
								<option value="NIAGARA REGION">NIAGARA REGION</option>
								<option value="NORTH BAY PARRY SOUND DISTRICT">NORTH BAY PARRY SOUND DISTRICT</option>
								<option value="NORTHWESTERN">NORTHWESTERN</option>
								<option value="OXFORD ELGIN-ST.THOMAS">OXFORD ELGIN-ST.THOMAS</option>
								<option value="PEEL REGION">PEEL REGION</option>
								<option value="PETERBOROUGH COUNTY-CITY">PETERBOROUGH COUNTY-CITY</option>
								<option value="PORCUPINE">PORCUPINE</option>
								<option value="RENFREW COUNTY AND DISTRICT">RENFREW COUNTY AND DISTRICT</option>
								<option value="SIMCOE MUSKOKA DISTRICT">SIMCOE MUSKOKA DISTRICT</option>
								<option value="SOUTHWESTERN">SOUTHWESTERN</option>
								<option value="SUDBURY AND DISTRICT">SUDBURY AND DISTRICT</option>
								<option value="THUNDER BAY DISTRICT">THUNDER BAY DISTRICT</option>
								<option value="TIMISKAMING">TIMISKAMING</option>
								<option value="TORONTO">TORONTO</option>
								<option value="UNKNOWN">UNKNOWN</option>
								<option value="WATERLOO REGION">WATERLOO REGION</option>
								<option value="WELLINGTON-DUFFERIN-GUELPH">WELLINGTON-DUFFERIN-GUELPH</option>
								<option value="WINDSOR-ESSEX COUNTY">WINDSOR-ESSEX COUNTY</option>
								<option value="YORK REGION">YORK REGION</option>
							</select>
						</div>
					</div>
					
					<div class="ui-block-a">
						<div class="ui-field-contain">
							<label for="active-cases">Active Cases:</label>
							<input type="number" id="active-cases" name="active-cases" />
						</div>
					</div>
					
					<div class="ui-block-a">
						<div class="ui-field-contain">
							<label for="resolved-cases">Resolved Cases:</label>
							<input type="number" id="resolved-cases" name="resolved-cases" />
						</div>
					</div>
					
					
					<div class="ui-block-a">
						<div class="ui-field-contain">
							<label for="death-cases">Death Cases:</label>
							<input type="number" id="death-cases" name="death-cases" />
						</div>
					</div>
					
					<div>
						<input type="submit" value="Predict">
					</div>
				</div>
			</form>
			
			<div class="container ui-grid-solo center shadow p-3 mb-5 bg-white rounded">
				<h2 class="text-center">Prediction Results</h2>
				<div class="bg-secondary text-white rounded shadow p-3">
					<h6 name="predictionResult" id="predictionResult">[Results go here?]</h6>
				</div>
				
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		$(function() {
		    $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
		});
	</script>
</html>