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
import ec.project.ejb.LogStatefulLocal;
import ec.project.ejb.RFPHUStatelessLocal;

@WebServlet("/RFServlet")
public class RFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    LogStatefulLocal logsbean;
    
    @EJB
    RFPHUStatelessLocal rfphu;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ServletContext sc = this.getServletContext();
    	String arffDataSet = "Random_Forest_PHU_Prediction.arff";
    	File predictionArff = null;
    	
    	//Retrieve user inputs
    	String date = request.getParameter("date");
    	String activecases = request.getParameter("activecases");
    	String resolvedcases = request.getParameter("resolvedcases");
    	String deaths = request.getParameter("deaths");
    	
    	//Set up the input
        String predictionData = rfphu.parsePredictionData(date, activecases, resolvedcases, deaths);
        
        try {
			predictionArff = copyDataSet(sc, arffDataSet, predictionData);
		} catch (URISyntaxException e) {
			System.out.println("Unable to create prediction arff.");
			response.sendRedirect("RFPredict.jsp");
		}

		//Make prediction
        String result = rfphu.predict(predictionArff);
        
        //Create Log entry for user
        HttpSession session = request.getSession(false);
        int userid = (int) session.getAttribute("userid");
        Log newLog = new Log(userid, new Date(), result);      
        // Persist log to DB
        logsbean.createLog(newLog);
        
        //Format output
        System.out.println(result);
    	response.sendRedirect("RFPredict.jsp?result=Date: " + date + " / Active Cases: " + activecases + " / Resolved Cases: " + resolvedcases + " / Death Cases: " + deaths + " / Predicted PHU: " + result);
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
