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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	PrintWriter pw = response.getWriter();

    	List<Log> allLogs = logsbean.getAllLogs();
        
        for (Log log : allLogs) {
        	//Send log info to JSP TODO
        }
        
        //Logs by User
//        HttpSession session = request.getSession(false);
//        int userid = (int) session.getAttribute("userid");
//        List<Log> userLogs = logsbean.findLogbyUser(userid);
//        for (Log log : allLogs) {
//        	//Send log info to JSP TODO
//        }

        pw.close();
    }
}
