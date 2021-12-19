<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">

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
	</style>
	
	<%  
	     if (session.getAttribute("ColourChoice") != null) {  
	        session.setAttribute("setBgColour", session.getAttribute("ColourChoice"));
	     }
	     else {
	    	 session.setAttribute("setBgColour", "#D8D8D8");
	     }
  	 %>
<meta charset="ISO-8859-1">
<title>KNN Prediction For [Blank]</title>
</head>
	<body style="background-color:${setBgColour}">
		<div>	
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
               <a class="navbar-brand" href="/ec-project-web/Index.jsp">Home</a>
               <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <c:if test="${isadmin == true}">
                     <li class="nav-item">
                        <a class="nav-link" href="/ec-project-web/AdminServlet">Prediction Preferences</a>
                     </li>
                  </c:if>
                  <c:if test="${isadmin == true}">
                     <li class="nav-item">
                        <a class="nav-link" href="/ec-project-web/ManageServlet">Manage Users</a>
                     </li>
                  </c:if>
                  <li class="nav-item">
                     <a class="nav-link" href="/ec-project-web/UserPreferences.jsp">User Preferences</a>
                  </li>
                  <li class="nav-item">
                     <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                           <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Predict</a>
                           <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                              <c:if test="${isadmin == true || KNN_Fully_Vaccinated_Is_On == true}">
                                 <li>
                                    <a class="dropdown-item"href="/ec-project-web/KNNPredict.jsp?modelType=FullyVacc">KNN - Fully Vacccinated</a>
                                 </li>
                              </c:if>
                              <c:if test="${isadmin == true || KNN_One_Dose_Is_On == true}">
                                 <li>
                                    <a class="dropdown-item" href="/ec-project-web/KNNPredict.jsp?modelType=SingleDose">KNN - One Dose Vaccinated</a>
                                 </li>
                              </c:if>
                              <c:if test="${isadmin == true || LR_Deaths_Is_On == true}">
                                 <li>
                                    <a class="dropdown-item" href="/ec-project-web/LRPredict.jsp?modelType=Death">Linear Regression - Death Cases</a>
                                 </li>
                              </c:if>
                              <c:if test="${isadmin == true || LR_Resolved_Is_On == true}">
                                 <li>
                                    <a class="dropdown-item" href="/ec-project-web/LRPredict.jsp?modelType=Resolved">Linear Regression - Resolved Cases</a>
                                 </li>
                              </c:if>
                              <c:if test="${isadmin == true || RF_PHU_Is_On == true}">
                                 <li>
                                    <a class="dropdown-item" href="/ec-project-web/RFPredict.jsp">Random Forest - PHU</a>
                                 </li>
                              </c:if>
                           </ul>
                        </li>
                     </ul>
                  </li>
                  <c:if test="${isadmin == true}">
                     <li class="nav-item">
                        <a class="nav-link" href="/ec-project-web/LogServlet">Logs</a>
                     </li>
                  </c:if>
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
				<h2 class="jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">
					<c:if test="${param.modelType == 'SingleDose'}">
					K-Nearest Neighbor: Single Dose Percentage Prediction
					</c:if>
					<c:if test="${param.modelType == 'FullyVacc'}">
					 K-Nearest Neighbor: Fully Vaccinated Population Prediction
					</c:if>
				</h2>
			</div>
			
			<form method="post" action="/ec-project-web/KNNServlet">
				<div class="container center shadow p-3 mb-3 bg-white rounded">
						<div>
							<label for="date">Date:</label>
							<input name="date" id="date" class="form-control" type="text" />
						</div>
						<div class="mt-2">
							<label for="phu">Public Heath Unit (PHU):</label>
							<select class="form-select" name="phu" id="phu">
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
						<div class="mt-2">
							<label for="age">Age Group:</label>
							<select class="form-select" name="age" id="age">
								<option value="05-11yrs">5 - 11 years</option>
								<option value="12-17yrs">12 - 17 years</option>
								<option value="18-29yrs">18 - 29 years</option>
								<option value="30-39yrs">30 - 39 years</option>
								<option value="40-49yrs">40 - 49 years</option>
								<option value="50-59yrs">50 - 59 years</option>
								<option value="60-69yrs">60 - 69 years</option>
								<option value="70-79yrs">70 - 79 years</option>
								<option value="80+">80 and over</option>
								<option value="Ontario_12plus">12 and over</option>
								<option value="Ontario_5plus">5 and over</option>
								<option value="Undisclosed_or_missing">Undisclosed or missing</option>
							</select>
						</div>
					
					<input type="hidden" name="modelType" id="modelType" value="${param.modelType}" />

					<div class="mt-2">
						<input type="submit" class="btn btn-primary" value="Predict">
					</div>
				</div>
			</form>
			
			<div class="container center shadow p-3 mb-5 bg-white rounded">
				<h2 class="text-center">Prediction Results</h2>
				<div class="bg-secondary text-white rounded shadow p-3">
					<h6 id="predictionResult">${param.result}</h6>
				</div>
			</div>
		</div>
	</body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<script type="text/javascript">
		$(function() {
		    $("#date").datepicker({ format: 'yyyy-mm-dd' });
		});
	</script>
</html>