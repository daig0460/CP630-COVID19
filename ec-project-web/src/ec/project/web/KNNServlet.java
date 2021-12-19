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
import ec.project.ejb.KNNFullyStatelessLocal;
import ec.project.ejb.KNNOneDoseStatelessLocal;
import ec.project.ejb.LogStatefulLocal;

@WebServlet("/KNNServlet")
public class KNNServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    LogStatefulLocal logsbean;
    
    @EJB
    KNNOneDoseStatelessLocal onedose;
    
    @EJB
    KNNFullyStatelessLocal twodose;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ServletContext sc = this.getServletContext();
    	File predictionArff = null;
    	String arffDataSet;
    	String result;
    	
    	//Retrieve user inputs
    	String date = request.getParameter("date");
    	String phu = request.getParameter("phu");
    	String age = request.getParameter("age");
    	
        //Determine model type
    	String modelType = request.getParameter("modelType");
    	
    	//Single Dose Prediction
    	if (modelType.equals("SingleDose")) {
    		arffDataSet = "KNN_One_Dose_Vaccinated_Prediction.arff";
    		
    		//Set up the input
    		String predictionData = onedose.parsePredictionData(date, phu, age);
    		
    		//Create prediction .arff file
            try {
    			predictionArff = copyDataSet(sc, arffDataSet, predictionData);
    		} catch (URISyntaxException e) {
    			System.out.println("Unable to create prediction arff.");
    			response.sendRedirect("KNNPredict.jsp?modelType=SingleDose");
    		}
            
            //Make prediction
    		result = onedose.predict(predictionArff);
    		
	        //Create Log entry for user
	        HttpSession session = request.getSession(false);
	        int userid = (int) session.getAttribute("userid");
	        Log newLog = new Log(userid, new Date(), result);
	        //Persist log to DB
	        logsbean.createLog(newLog);
	        
	        //Format output
	        System.out.println(result);
			response.sendRedirect("KNNPredict.jsp?modelType=SingleDose&result=Date: " + date + " / PHU: " + phu + " / Age group: " + age + " / Percentage prediction: " + result);
    	}
    	
    	//Fully Vaccinated Prediction (FullyVacc)
    	else {
    		arffDataSet = "KNN_Fully_Vaccinated_Prediction.arff";
    		
	    	//Set up the input
	        String predictionData = twodose.parsePredictionData(date, phu, age);
	        
	        //Create prediction .arff file
	        try {
				predictionArff = copyDataSet(sc, arffDataSet, predictionData);
			} catch (URISyntaxException e) {
				System.out.println("Unable to create prediction arff.");
				response.sendRedirect("KNNPredict.jsp?modelType=FullyVacc");
			}
	        
			//Make prediction
	        result = twodose.predict(predictionArff);
	        
	        //Create Log entry for user
	        HttpSession session = request.getSession(false);
	        int userid = (int) session.getAttribute("userid");
	        Log newLog = new Log(userid, new Date(), result);
	        //Persist log to DB
	        logsbean.createLog(newLog);
	        
	        //Format output
	        System.out.println(result);
			response.sendRedirect("KNNPredict.jsp?modelType=FullyVacc&result=Date: " + date + " / PHU: " + phu + " / Age group: " + age + " / Percentage prediction: " +  result);
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
