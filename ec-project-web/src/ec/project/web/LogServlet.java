package ec.project.web;

import java.io.*;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	//Send LogList to JSP
    	request.setAttribute("allLogs", allLogs);
    	request.getRequestDispatcher("Logs.jsp").forward(request, response);
        
        //Logs by User
//        HttpSession session = request.getSession(false);
//        int userid = (int) session.getAttribute("userid");
//        List<Log> userLogs = logsbean.findLogbyUser(userid);
//    	request.setAttribute("userLogs", userLogs);
//    	request.getRequestDispatcher("Logs.jsp").forward(request, response);
    }
}
