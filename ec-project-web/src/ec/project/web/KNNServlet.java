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
    	
    	String date = request.getParameter("date");
    	String phu = request.getParameter("phu");
    	String age = request.getParameter("age");
        //Which model are we using? TODO
    	
    	//Set up the input
        String predictionData = onedose.parsePredictionData(date, phu, age);
        //String predictionData = twodose.parsePredictionData(date, phu, age);
        
        //Make prediction
        String result = onedose.predict(predictionData);
        //String result = twodose.predict(predictionData);
        
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
