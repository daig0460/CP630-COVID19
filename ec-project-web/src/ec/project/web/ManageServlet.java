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

import ec.project.db.AppUsers;
import ec.project.ejb.AppUserStatefulLocal;

@WebServlet("/ManageServlet")
public class ManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    AppUserStatefulLocal appusers;

    //Delete users
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	List<AppUsers> allUsers = appusers.getAllUsers();
    	//Display all users TODO
    	
    	//Delete user
    	String username = request.getParameter("username");
    	AppUsers loginUser = appusers.deleteUser(username);
    }
    
    //Add Users
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//Get new user details
    	String username = request.getParameter("username");
        String password = request.getParameter("password");     
        boolean isAdmin = request.getParameter("isAdmin") != null;
        int isadmin = isAdmin ? 1 : 0;
        //Hash the password
        try {
			password = PasswordHashing.HashPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to hash password, please try again");
			response.sendRedirect("ManageUsers.jsp");
		}
        
        //Create new User and persist to database
        AppUsers newUser = new AppUsers(username, password, isAdmin);
        System.out.println(newUser.toString());
        appusers.createUser(newUser);
        
        System.out.println("Successfully added new user: " + newUser.getUsername());
		response.sendRedirect("ManageUsers.jsp");
		
        PrintWriter pw = response.getWriter();
        pw.close();
    }
}
