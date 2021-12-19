package ec.project.web;

import java.io.*;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.project.db.PredictionModel;
import ec.project.ejb.PredictionModelStatefulLocal;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    PredictionModelStatefulLocal modelsbean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	List<PredictionModel> dbmodels = modelsbean.getAllModels();
    	
    	HttpSession session = request.getSession(true);
    	
    	//Send model list to JSP
    	request.setAttribute("allModels", dbmodels);
    	
    	for (PredictionModel model : dbmodels)
    	{
    		switch(model.getModelname()) {
	    		case "KNN_One_Dose": 
	    			session.setAttribute("KNN_One_Dose_Is_On", model.getIsviewable());
	    			break;
	    		case "KNN_Fully_Vaccinated": 
	    			session.setAttribute("KNN_Fully_Vaccinated_Is_On", model.getIsviewable());
	    			break;
	    		case "LR_Deaths": 
	    			session.setAttribute("LR_Deaths_Is_On", model.getIsviewable());
	    			break;
	    		case "LR_Resolved": 
	    			session.setAttribute("LR_Resolved_Is_On", model.getIsviewable());
	    			break;
	    		case "RF_PHU": 
	    			session.setAttribute("RF_PHU_Is_On", model.getIsviewable());
	    			break;
    		}
    	}
    	
    	request.getRequestDispatcher("AdminSettings.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Get model info
    	boolean KNN_One_Dose = request.getParameter("KNN_One_Dose") != null;
    	boolean KNN_Fully_Vaccinated = request.getParameter("KNN_Fully_Vaccinated") != null;
    	boolean LR_Deaths = request.getParameter("LR_Deaths") != null;
    	boolean LR_Resolved = request.getParameter("LR_Resolved") != null;
    	boolean RF_PHU = request.getParameter("RF_PHU") != null;

        List<PredictionModel> dbmodels = modelsbean.getAllModels();
        
        //Set the toggle info for each model
        for (PredictionModel model: dbmodels) {
        	switch(model.getModelname()) {
        		case "KNN_One_Dose": 
        			model.setIsviewable(KNN_One_Dose);
        			break;
        		case "KNN_Fully_Vaccinated": 
        			model.setIsviewable(KNN_Fully_Vaccinated);
        			break;
        		case "LR_Deaths": 
        			model.setIsviewable(LR_Deaths);
        			break;
        		case "LR_Resolved": 
        			model.setIsviewable(LR_Resolved);
        			break;
        		case "RF_PHU": 
        			model.setIsviewable(RF_PHU);
        			break;
        	}
        	//Save the model
        	modelsbean.saveModel(model);       		
        }
        response.sendRedirect("AdminServlet");
    }
}
