package ec.project.web;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.project.db.Log;
import ec.project.ejb.LRDeathStatelessLocal;
import ec.project.ejb.LRResolvedStatelessLocal;
import ec.project.ejb.LogStatefulLocal;

@WebServlet("/LRServlet")
public class LRServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    LogStatefulLocal logsbean;
    
    @EJB
    LRDeathStatelessLocal deathbean;
    
    @EJB
    LRResolvedStatelessLocal resolvedbean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ServletContext sc = this.getServletContext();
    	File predictionArff = null;
    	String arffDataSet;
    	String result;
    	
    	//Retrieve user inputs
    	String phu = request.getParameter("phu");
    	String activecases = request.getParameter("activecases");
    	
        //Determine model type
    	String modelType = request.getParameter("modelType");
    	
    	//Deaths Prediction
    	if (modelType.equals("Death")) {
    		//Set specific input
    		
    		String resolvedcases = request.getParameter("resolvedcases");
    		arffDataSet = "Deaths_Prediction.arff";
    		
    		//Set up the input
            String predictionData = deathbean.parsePredictionData(phu, activecases, resolvedcases);
            
            //Create prediction .arff file
            try {
    			predictionArff = copyDataSet(sc, arffDataSet, predictionData);
    		} catch (URISyntaxException e) {
    			System.out.println("Unable to create prediction arff.");
    			response.sendRedirect("LRPredict.jsp?modelType=Death");
    		}
            
            //Make prediction
    		result = deathbean.predict(predictionArff);
    		
    		if (result.contains("-"))
            {
    			result = "0";
            }
    		
    		//Create Log entry for user
	        HttpSession session = request.getSession(false);
	        int userid = (int) session.getAttribute("userid");
	        Log newLog = new Log(userid, new Date(), result);
	        //Persist log to DB
	        logsbean.createLog(newLog);
	        
	        //Format output
	        System.out.println(result);
			response.sendRedirect("LRPredict.jsp?modelType=Death&result=PHU: " + phu + " / Active Cases: " + activecases + " / Resolved Cases: " + resolvedcases + " / Death count prediction: " + result);
    	}
    	
    	//Resolved Cases Prediction (Resolved)
    	else {
    		//Set specific input
    		String deaths = request.getParameter("deaths");
    		arffDataSet = "Resolved_Cases_Prediction.arff";
    		
    		//Set up the input
    		String predictionData = resolvedbean.parsePredictionData(phu, activecases, deaths);
    		
            //Create prediction .arff file
    		
            try {
    			predictionArff = copyDataSet(sc, arffDataSet, predictionData);
    		} catch (URISyntaxException e) {
    			System.out.println("Unable to create prediction arff.");
    			response.sendRedirect("LRPredict.jsp?modelType=Death");
    		}
            
            //Make prediction
            result = resolvedbean.predict(predictionArff);
            
            if (result.contains("-"))
            {
            	result = "0";
            }
            
            //Create Log entry for user
	        HttpSession session = request.getSession(false);
	        int userid = (int) session.getAttribute("userid");
	        Log newLog = new Log(userid, new Date(), result);
	        //Persist log to DB
	        logsbean.createLog(newLog);
	        
	        //Format output
	        System.out.println(result);
			response.sendRedirect("LRPredict.jsp?modelType=Resolved&result=PHU: " + phu + " / Active Cases: " + activecases + " / Death Cases: " + deaths + " / Resolved count prediction: " + result);
    	}      
    }
    
    private File copyDataSet(ServletContext sc, String arffDataSet, String parsedData) throws IOException, URISyntaxException {
    	String initialPath = sc.getRealPath("/");
    	String data_dir = "/WEB-INF/resources/prediction/";
    	String newFileName = arffDataSet.substring(0, arffDataSet.lastIndexOf("."));
    	
    	File tempArff = File.createTempFile(newFileName + "_temp", ".arff", new File(initialPath + "/WEB-INF/resources"));
		//System.out.println("success!");
		tempArff.deleteOnExit();
		
		//System.out.println("File created.");
		URL in = sc.getResource(data_dir + arffDataSet);
		File mainArff = new File(in.toURI());
		Files.copy(mainArff.toPath(), tempArff.toPath(), REPLACE_EXISTING);

		FileWriter writer = new FileWriter(tempArff, true);
		writer.write(parsedData);
		writer.flush();
		writer.close();

		return tempArff;
    }
}
