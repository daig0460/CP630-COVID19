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

import ec.project.db.AppUsers;
import ec.project.db.PredictionModel;
import ec.project.ejb.AppUserStatefulLocal;
import ec.project.ejb.PredictionModelStatefulLocal;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    AppUserStatefulLocal appusers;
    
    @EJB
    PredictionModelStatefulLocal modelsbean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	request.getSession().removeAttribute("ColourChoice");
    	request.getSession().removeAttribute("setBgColour");
    	request.getSession().removeAttribute("allLogs");
    	request.getSession().removeAttribute("isadmin");
    	
    	request.getSession().removeAttribute("KNN_One_Dose_Is_On");
    	request.getSession().removeAttribute("KNN_Fully_Vaccinated_Is_On");
    	request.getSession().removeAttribute("LR_Deaths_Is_On");
    	request.getSession().removeAttribute("LR_Resolved_Is_On");
    	request.getSession().removeAttribute("RF_PHU_Is_On");
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter pw = response.getWriter();

        //MD5 hashing
		try {
			password = PasswordHashing.HashPassword(password);
		} catch (Exception e) {
			System.out.println("Unable to login, please try again.");
			response.sendRedirect("Login.jsp");
		}
        
        AppUsers loginUser = appusers.validate(username, password);
        if (loginUser != null) {
        	
        	HttpSession session = request.getSession(true);
        	
        	List<PredictionModel> dbmodels = modelsbean.getAllModels();
        	
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
        	
        	session.setAttribute("userid", loginUser.getId());
        	session.setAttribute("username", loginUser.getUsername());
        	session.setAttribute("isadmin", loginUser.getIsadmin());
        	response.sendRedirect("Index.jsp");
        } 
        else {
        	System.out.println("No such user exists.");
        	response.sendRedirect("Login.jsp");
        }
        pw.close();
    }
}
