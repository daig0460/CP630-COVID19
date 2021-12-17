package ec.project.web;

import java.io.*;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.project.db.AppUsers;
import ec.project.ejb.AppUserStatefulLocal;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    AppUserStatefulLocal appusers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter pw = response.getWriter();

        //MD5 hashing
		try {
			password = PasswordHashing.HashPassword(password);
		} catch (Exception e) {
			System.out.println("Unable to login, please try again.");
			response.sendRedirect("Login.jsp");
		}
        
        AppUsers loginUser = appusers.validate(username, password);
        if (loginUser != null) {
//        	if (loginUser.getIsadmin()) {
//        		response.sendRedirect("index.jsp");
//        	}
        	HttpSession session = request.getSession(false);
        	session.setAttribute("userid", loginUser.getId());
        	session.setAttribute("username", loginUser.getUsername());
        	session.setAttribute("isadmin", loginUser.getIsadmin());
        	response.sendRedirect("Index.jsp");
        } 
        else {
        	System.out.println("No such user exists.");
        	response.sendRedirect("Login.jsp");
        }
        pw.close();
    }
}
