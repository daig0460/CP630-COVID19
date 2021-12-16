package ec.project.web;

import java.io.*;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.project.db.AppUsers;
import ec.project.ejb.AppUserStatefulLocal;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    AppUserStatefulLocal appusers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter pw = response.getWriter();

        AppUsers loginUser = appusers.validate(username, password);
        if (loginUser != null) {
        	if (loginUser.getIsadmin()) {
        		response.sendRedirect("admin.html");
        	}
        	else {
        		response.sendRedirect("main.html");
        	}
        } 
        else {
        	System.out.println("No such user exists.");
        	response.sendRedirect("login.html");
        }
        pw.close();
    }
}
