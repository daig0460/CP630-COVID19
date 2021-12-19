<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/site.css" rel="stylesheet">
	
	<style>
		.center {
			margin:0 auto;
			width:50%;
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
<title>Admin - Settings Page</title>
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
				<h2 class="container jumbotron rounded center shadow p-3 mt-2 mb-3 bg-white text-center">Admin - Settings Page</h2>
			</div>
			
			<form method="post" action="/ec-project-web/AdminServlet">
				<div class="container center shadow p-3 mb-5 bg-white rounded">
						
						<div class="row">
							<div class="col-8">
								<h5>KNN Fully Vaccinated Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="KNN_Fully_Vaccinated" id="KNN_Fully_Vaccinated" class="form-check-input" style="width:80%;height:70%;" type="checkbox" 
									<% if (session.getAttribute("KNN_Fully_Vaccinated_Is_On").equals(true)) { out.print("checked=\"checked\""); } %>  />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>KNN One Dose Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="KNN_One_Dose" id="KNN_One_Dose" class="form-check-input" style="width:80%;height:70%;" type="checkbox"
									<% if (session.getAttribute("KNN_One_Dose_Is_On").equals(true)) { out.print("checked=\"checked\""); } %>  />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>Linear Regression Death Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="LR_Deaths" id="LR_Deaths" class="form-check-input" style="width:80%;height:70%;" type="checkbox"
									<% if (session.getAttribute("LR_Deaths_Is_On").equals(true)) { out.print("checked=\"checked\""); } %>  />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>Linear Regression Resolved Cases Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="LR_Resolved" id="LR_Resolved" class="form-check-input" style="width:80%;height:70%;" type="checkbox"
									<% if (session.getAttribute("LR_Resolved_Is_On").equals(true)) { out.print("checked=\"checked\""); } %>  />
							</div>
							<div class="col-2"></div>
						</div>
						
						<div class="row">
							<div class="col-8">
								<h5>Random Forest PHU Prediction:</h5>
							</div>
							<div class="col-2 form-check form-switch align-left">
								<input name="RF_PHU" id="RF_PHU" class="form-check-input" style="width:80%;height:70%;" type="checkbox"
									<% if (session.getAttribute("RF_PHU_Is_On").equals(true)) { out.print("checked=\"checked\""); } %>  />
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