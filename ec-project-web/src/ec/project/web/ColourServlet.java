package ec.project.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	@WebServlet("/ColourServlet")
	public class ColourServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	       
	    	String colourChoice = request.getParameter("ColourChoice");
	    	HttpSession session = request.getSession(true);
	    	session.setAttribute("ColourChoice", colourChoice);
	        response.sendRedirect("UserPreferences.jsp");
	    }
	}
