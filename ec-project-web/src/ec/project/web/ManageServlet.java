package ec.project.web;

import java.io.*;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.project.db.AppUsers;
import ec.project.ejb.AppUserStatefulLocal;

@WebServlet("/ManageServlet")
public class ManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    AppUserStatefulLocal appusers;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	List<AppUsers> allUsers = appusers.getAllUsers();
    	//Send userlist to JSP
    	request.setAttribute("appUsers", allUsers);
    	request.getRequestDispatcher("ManageUsers.jsp").forward(request, response);
    }
    
    //Add Users
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	
    	//Adding new user
    	if (action.equals("add")) {
    		//Get new user details
        	String username = request.getParameter("username");
            String password = request.getParameter("password");     
            boolean isAdmin = request.getParameter("isAdmin") != null;

            //Hash the password
            try {
    			password = PasswordHashing.HashPassword(password);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			System.out.println("Unable to hash password, please try again");
    			response.sendRedirect("ManageServlet");
    		}
            
            //Create new User and persist to database
            AppUsers newUser = new AppUsers(username, password, isAdmin);
            appusers.createUser(newUser);
            
            System.out.println("Successfully added new user: " + newUser.getUsername());
    	}
    	else if (action.equals("delete"))
    	{
    		//Delete user
    		String username = request.getParameter("userToDelete");
    		AppUsers deletedUser = appusers.deleteUser(username);
    		System.out.println("Successfully deleted user: " + deletedUser.getUsername());        		
    	}
    	
    	response.sendRedirect("ManageServlet");
    }
}
