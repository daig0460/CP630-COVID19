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

import ec.project.db.Log;
import ec.project.ejb.LogStatefulLocal;

@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    LogStatefulLocal logsbean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	List<Log> allLogs = logsbean.getAllLogs();   
    	
    	for (Log log : allLogs)
    	{
        	System.out.println("Log ID:" + log.getId()+  "User ID:" + log.getUserid() + "Info:" + log.getLoginfo());
    	}
    	
    	HttpSession session = request.getSession(true);
    	
    	//Send Log List to JSP
    	session.setAttribute("allLogs", allLogs);
    	response.sendRedirect("Logs.jsp");
    	//request.getRequestDispatcher("Logs.jsp").forward(request, response);
        
        //Logs by User
//        HttpSession session = request.getSession(false);
//        int userid = (int) session.getAttribute("userid");
//        List<Log> userLogs = logsbean.findLogbyUser(userid);
//    	request.setAttribute("userLogs", userLogs);
//    	request.getRequestDispatcher("Logs.jsp").forward(request, response);
    }
}
