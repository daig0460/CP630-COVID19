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
    	
    	String phu = request.getParameter("phu");
    	String activecases = request.getParameter("active");
    	
    	//Which model are we using? TODO
    	
    	//Retrive correct input based on model
    	//if death:
    	String resolvedcases = request.getParameter("resolved");
    	//else:
    	String deaths = request.getParameter("deaths");
    	
    	//Set up the input
        String predictionData = deathbean.parsePredictionData(phu, activecases, resolvedcases);
        //String predictionData = resolvedbean.parsePredictionData(phu, activecases, deaths);
        		
        //Make prediction
        String result = deathbean.predict(predictionData);
        //String result = resolvedbean.predict(predictionData);
        
        //Create Log entry for user
        HttpSession session = request.getSession(false);
        int userid = (int) session.getAttribute("userid");
        Log newLog = new Log(userid, new Date(), result);
        
        //Persist log to DB
        logsbean.createLog(newLog);
        
        //Format output
        PrintWriter pw = response.getWriter();
    	pw.close();
       
    }
}
