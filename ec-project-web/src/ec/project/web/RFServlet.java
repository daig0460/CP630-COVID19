package ec.project.web;

import java.io.*;
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
    	
    	String date = request.getParameter("date");
    	String activecases = request.getParameter("activecases");
    	String resolvedcases = request.getParameter("resolvedcases");
    	String deaths = request.getParameter("deaths");
    	
    	//Set up the input
        String predictionData = rfphu.parsePredictionData(date, activecases, resolvedcases, deaths);
        
        //Make prediction
        String result = rfphu.predict(predictionData);
        
        System.out.println(result);
        //Create Log entry for user
//        HttpSession session = request.getSession(false);
//        int userid = (int) session.getAttribute("userid");
//        Log newLog = new Log(userid, new Date(), result);
//        
//        //Persist log to DB
//        logsbean.createLog(newLog);
        
        //Format output
        PrintWriter pw = response.getWriter();
    	pw.close();
       
    	response.sendRedirect("RFPredict.jsp");
    }
}
